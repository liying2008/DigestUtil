/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.crypto;

import cc.duduhuo.util.digest.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public interface CryptoProtocol {
    /**
     * Default charset is {@link StandardCharsets#UTF_8}.
     */
    Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    /**
     * 加密算法。AES / DES / DESede / Blowfish / ChaCha20 等
     */
    String getAlgorithm();

    /**
     * 分组密码（Block Cipher）工作模式。ECB / CBC / GCM 等。
     * 流密码（Stream Cipher）直接返回 null 即可。
     */
    default String getMode() {
        return null;
    }

    /**
     * 填充模式。PKCS5Padding / NoPadding 等。
     * 某些加密算法不涉及填充模式，直接返回 null 即可。
     */
    default String getPaddingMode() {
        return null;
    }

    /**
     * 初始向量长度（字节数）或 Nonce 长度（字节数）。没有则返回0.
     */
    int getIvLength();

    /**
     * 生成加解密密钥
     *
     * @param key 密钥初始字节
     */
    SecretKey createKey(final byte[] key);

    /**
     * 生成随机 iv
     * 如果 getIvLength() 返回 0 , 表明不需要 iv，则返回 null
     *
     * @return 随机iv
     */
    default byte[] createIv() {
        int ivLength = getIvLength();
        if (ivLength <= 0) {
            return null;
        }
        return Utils.randomBytes(ivLength);
    }

    /**
     * 获取 ParameterSpec 和 IV
     *
     * @param iv 当解密时，需要传递 iv 来创建 ParameterSpec；当加密时，传递 null 表示需要生成随机 iv
     * @return ParameterSpec 和 IV
     */
    default ParameterSpecAndIv getParameterSpecAndIv(final byte[] iv) {
        if (iv != null) {
            return new ParameterSpecAndIv(new IvParameterSpec(iv), iv);
        }
        byte[] randomIv = createIv();
        if (randomIv == null) {
            return null;
        }
        return new ParameterSpecAndIv(new IvParameterSpec(randomIv), randomIv);
    }

    default String getTransformation() {
        String algorithm = getAlgorithm();
        String mode = getMode();
        String paddingMode = getPaddingMode();
        if (mode == null && paddingMode == null) {
            return algorithm;
        } else if (paddingMode == null) {
            return algorithm + "/" + mode;
        } else {
            return algorithm + "/" + mode + "/" + paddingMode;
        }
    }

    /**
     * 加密
     *
     * @param plaintext 明文
     * @param key       密钥
     * @param aad       AEAD 加密模式的附加认证数据（AAD，Additional Authenticated Data）
     * @return Base64 编码的密文 (格式: IV + Ciphertext)
     */
    default String encrypt(final byte[] plaintext, final byte[] key, final byte[] aad) {
        SecretKey secretKey = createKey(key);
        int ivLength = getIvLength();
        String transformation = getTransformation();
        if (ivLength > 0) {
            ParameterSpecAndIv specAndIv = getParameterSpecAndIv(null);
            byte[] cipherBytes = Utils.wholeEncrypt(plaintext, transformation, secretKey, specAndIv.parameterSpec, aad);
            // 将 IV 拼接到密文头部 (IV 不需要保密，但解密时需要)
            byte[] combined = new byte[ivLength + cipherBytes.length];
            System.arraycopy(specAndIv.iv, 0, combined, 0, ivLength);
            System.arraycopy(cipherBytes, 0, combined, ivLength, cipherBytes.length);
            return Base64.encodeToString(combined);
        } else {
            byte[] cipherBytes = Utils.wholeEncrypt(plaintext, transformation, secretKey, null, aad);
            return Base64.encodeToString(cipherBytes);
        }
    }

    /**
     * 加密
     *
     * @param plaintext 明文
     * @param key       密钥
     * @return Base64 编码的密文 (格式: IV + Ciphertext)
     */
    default String encrypt(final byte[] plaintext, final byte[] key) {
        return encrypt(plaintext, key, null);
    }

    /**
     * 加密
     *
     * @param plaintext 明文
     * @param key       密钥
     * @param aad       AEAD 加密模式的附加认证数据（AAD，Additional Authenticated Data）
     * @return Base64 编码的密文 (格式: IV + Ciphertext)
     */
    default String encrypt(final String plaintext, final String key, final String aad) {
        if (aad == null) {
            return encrypt(plaintext.getBytes(DEFAULT_CHARSET), key.getBytes(DEFAULT_CHARSET));
        }
        return encrypt(plaintext.getBytes(DEFAULT_CHARSET), key.getBytes(DEFAULT_CHARSET), aad.getBytes(DEFAULT_CHARSET));
    }

    /**
     * 加密
     *
     * @param plaintext 明文
     * @param key       密钥
     * @return Base64 编码的密文 (格式: IV + Ciphertext)
     */
    default String encrypt(final String plaintext, final String key) {
        return encrypt(plaintext, key, null);
    }

    /**
     * 解密
     *
     * @param ciphertext Base64 编码的密文 (格式: IV + Ciphertext)
     * @param key        密钥
     * @param aad        AEAD 加密模式的附加认证数据（AAD，Additional Authenticated Data）
     * @return 明文
     */
    default String decrypt(final byte[] ciphertext, final byte[] key, final byte[] aad) {
        SecretKey secretKey = createKey(key);
        byte[] combined = Base64.decode(ciphertext);
        int ivLength = getIvLength();
        String transformation = getTransformation();
        if (ivLength > 0) {
            // 校验密文长度 (至少包含一个 IV 块)
            if (combined.length < ivLength) {
                throw new IllegalArgumentException("Invalid ciphertext length");
            }
            // 提取 IV 和实际密文
            byte[] iv = Arrays.copyOfRange(combined, 0, ivLength);
            byte[] pureCiphertext = Arrays.copyOfRange(combined, ivLength, combined.length);
            ParameterSpecAndIv specAndIv = getParameterSpecAndIv(iv);
            byte[] result = Utils.wholeDecrypt(pureCiphertext, transformation, secretKey, specAndIv.parameterSpec, aad);
            return new String(result, DEFAULT_CHARSET);
        } else {
            byte[] result = Utils.wholeDecrypt(Base64.decode(ciphertext), transformation, secretKey, null, aad);
            return new String(result, DEFAULT_CHARSET);
        }
    }

    /**
     * 解密
     *
     * @param ciphertext Base64 编码的密文 (格式: IV + Ciphertext)
     * @param key        密钥
     * @return 明文
     */
    default String decrypt(final byte[] ciphertext, final byte[] key) {
        return decrypt(ciphertext, key, null);
    }

    /**
     * 解密
     *
     * @param ciphertext Base64 编码的密文 (格式: IV + Ciphertext)
     * @param key        密钥
     * @param aad        AEAD 加密模式的附加认证数据（AAD，Additional Authenticated Data）
     * @return 明文
     */
    default String decrypt(final String ciphertext, final String key, final String aad) {
        if (aad == null) {
            return decrypt(ciphertext.getBytes(DEFAULT_CHARSET), key.getBytes(DEFAULT_CHARSET));
        }
        return decrypt(ciphertext.getBytes(DEFAULT_CHARSET), key.getBytes(DEFAULT_CHARSET), aad.getBytes(DEFAULT_CHARSET));
    }

    /**
     * 解密
     *
     * @param ciphertext Base64 编码的密文 (格式: IV + Ciphertext)
     * @param key        密钥
     * @return 明文
     */
    default String decrypt(final String ciphertext, final String key) {
        return decrypt(ciphertext, key, null);
    }
}
