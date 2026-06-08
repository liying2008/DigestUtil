/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.crypto;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;

public final class Utils {
    // 复用 SecureRandom 实例，避免频繁创建造成的性能损耗
    private static final SecureRandom secureRandom = new SecureRandom();

    /**
     * 生成指定长度的随机字节数组
     *
     * @param length 字节数组长度
     * @return 随机字节数组
     */
    public static byte[] randomBytes(final int length) {
        byte[] bytes = new byte[length];
        secureRandom.nextBytes(bytes);
        return bytes;
    }

    /**
     * 选择 AES 密钥长度（字节数）
     *
     * @param key       AES密钥
     * @param keyLength 指定的密钥长度（字节数，可取值 16,24,32）。 -1 表示根据传入的密钥长度而定
     * @return 密钥长度
     */
    public static int chooseAesKeyLength(final byte[] key, final int keyLength) {
        if (keyLength <= 0) {
            if (key.length <= 16) {
                return 16;
            } else if (key.length <= 24) {
                return 24;
            } else {
                return 32;
            }
        } else {
            return keyLength;
        }
    }

    /**
     * 选择 TripleDES 密钥长度（字节数）
     *
     * @param key       TripleDES 密钥
     * @param keyLength 指定的密钥长度（字节数，可取值 16,24）。 -1 表示根据传入的密钥长度而定
     * @return 密钥长度
     */
    public static int chooseTripleDesKeyLength(final byte[] key, final int keyLength) {
        if (keyLength <= 0) {
            if (key.length <= 16) {
                return 16;
            } else {
                return 24;
            }
        } else {
            return keyLength;
        }
    }

    /**
     * 选择 Blowfish 密钥长度（字节数）
     *
     * @param key       Blowfish密钥
     * @param keyLength 指定的密钥长度（字节数，可取值 4-56）。 -1 表示根据传入的密钥长度而定
     * @return 密钥长度
     */
    public static int chooseBlowfishKeyLength(final byte[] key, final int keyLength) {
        if (keyLength <= 0) {
            if (key.length <= 4) {
                return 4;
            } else {
                return Math.min(key.length, 56);
            }
        } else {
            return keyLength;
        }
    }

    /**
     * 选择 RC2 密钥长度（字节数）
     *
     * @param key       RC2密钥
     * @param keyLength 指定的密钥长度（字节数，可取值 5-128）。 -1 表示根据传入的密钥长度而定
     * @return 密钥长度
     */
    public static int chooseRc2KeyLength(final byte[] key, final int keyLength) {
        if (keyLength <= 0) {
            if (key.length <= 5) {
                return 5;
            } else {
                return Math.min(key.length, 128);
            }
        } else {
            return keyLength;
        }
    }

    /**
     * 选择 RC4 密钥长度（字节数）
     *
     * @param key       RC4密钥
     * @param keyLength 指定的密钥长度（字节数，可取值 5-128）。 -1 表示根据传入的密钥长度而定
     * @return 密钥长度
     */
    public static int chooseRc4KeyLength(final byte[] key, final int keyLength) {
        if (keyLength <= 0) {
            if (key.length <= 5) {
                return 5;
            } else {
                return Math.min(key.length, 128);
            }
        } else {
            return keyLength;
        }
    }

    /**
     * Truncate or pad the input to a byte array of the specified length.
     *
     * @param input        The input byte array.
     * @param targetLength The target length of the byte array.
     * @param padding      The padding byte.
     * @return The byte array.
     */
    public static byte[] truncateOrPad(final byte[] input, final int targetLength, final byte padding) {
        if (input.length == targetLength) {
            return input;
        } else if (input.length > targetLength) {
            return Arrays.copyOf(input, targetLength);
        } else {
            byte[] output = new byte[targetLength];
            System.arraycopy(input, 0, output, 0, input.length);
            Arrays.fill(output, input.length, targetLength, padding);
            return output;
        }
    }

    /**
     * Create a secret key for AES.
     *
     * @param key        The key material of the secret key.
     * @param keyLength  The key length (in bytes). If it is -1, the key length will be determined by the length of the key.
     * @param keyPadding The padding byte for the key.
     * @return The secret key.
     */
    public static SecretKey createAesKey(final byte[] key, final int keyLength, final byte keyPadding) {
        int targetKeyLength = chooseAesKeyLength(key, keyLength);
        byte[] keyBytes = truncateOrPad(key, targetKeyLength, keyPadding);
        return new SecretKeySpec(keyBytes, "AES");
    }

    /**
     * Create a secret key for DES.
     *
     * @param key        The key material of the secret key.
     * @param keyPadding The padding byte for the key.
     * @return The secret key.
     */
    public static SecretKey createDesKey(final byte[] key, final byte keyPadding) {
        byte[] keyBytes = truncateOrPad(key, 8, keyPadding);
        return new SecretKeySpec(keyBytes, "DES");
    }

    /**
     * Create a secret key for TripleDES.
     * <p>
     * TripleDES 密钥长度可以是 16 或 24 字节
     * 16字节（2-Key 3DES）：K1、K2、K1
     * 24字节（3-Key 3DES）：K1、K2、K3
     * 在 Java 中，使用 SecretKeySpec 创建 TripleDES 密钥时，如果密钥长度为 16 字节，则会报“java.security.InvalidKeyException: Wrong key size”错误。
     * 因此需要手动将 16 字节密钥扩展为 24 字节。
     * 具体做法是将 16 字节密钥的前 8 字节作为 K3 填充到 24字节密钥的后 8 个字节。即 K1 K2 -> K1 K2 K1.
     *
     * @param key        The key material of the secret key.
     * @param keyLength  The key length (in bytes). If it is -1, the key length will be determined by the length of the key.
     * @param keyPadding The padding byte for the key.
     * @return The secret key.
     */
    public static SecretKey createTripleDesKey(final byte[] key, final int keyLength, final byte keyPadding) {
        int targetKeyLength = chooseTripleDesKeyLength(key, keyLength);
        byte[] keyBytes = truncateOrPad(key, targetKeyLength, keyPadding);
        if (targetKeyLength == 16) {
            // 2-key 3DES 转换为 3-key 3DES
            // 最终密钥长度是 24 字节，前 16 字节由原始密钥填充，后 8 字节由原始密钥前 8 字节填充
            byte[] keyBytes24 = new byte[24];
            System.arraycopy(keyBytes, 0, keyBytes24, 0, 16);
            System.arraycopy(keyBytes, 0, keyBytes24, 16, 8);
            return new SecretKeySpec(keyBytes24, "DESede");
        } else {
            return new SecretKeySpec(keyBytes, "DESede");
        }
    }

    /**
     * Create a secret key for Blowfish.
     *
     * @param key        The key material of the secret key.
     * @param keyLength  The key length (in bytes). If it is -1, the key length will be determined by the length of the key.
     * @param keyPadding The padding byte for the key.
     * @return The secret key.
     */
    public static SecretKey createBlowfishKey(final byte[] key, final int keyLength, final byte keyPadding) {
        int targetKeyLength = chooseBlowfishKeyLength(key, keyLength);
        byte[] keyBytes = truncateOrPad(key, targetKeyLength, keyPadding);
        return new SecretKeySpec(keyBytes, "Blowfish");
    }

    public static SecretKey createRc2Key(final byte[] key, final int keyLength, final byte keyPadding) {
        int targetKeyLength = chooseRc2KeyLength(key, keyLength);
        byte[] keyBytes = truncateOrPad(key, targetKeyLength, keyPadding);
        return new SecretKeySpec(keyBytes, "RC2");
    }

    public static SecretKey createRc4Key(final byte[] key, final int keyLength, final byte keyPadding) {
        int targetKeyLength = chooseRc4KeyLength(key, keyLength);
        byte[] keyBytes = truncateOrPad(key, targetKeyLength, keyPadding);
        return new SecretKeySpec(keyBytes, "RC4");
    }

    /**
     * Create a secret key for ChaCha20.
     *
     * @param key        The key material of the secret key.
     * @param keyPadding The padding byte for the key.
     * @return The secret key.
     */
    public static SecretKey createChaCha20Key(final byte[] key, final byte keyPadding) {
        byte[] keyBytes = truncateOrPad(key, 32, keyPadding);
        return new SecretKeySpec(keyBytes, "ChaCha20");
    }

    public static Cipher getCipher(final String transformation) {
        try {
            return Cipher.getInstance(transformation);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Encrypt the plaintext using the specified transformation, secret key, and algorithm parameter spec.
     *
     * @param plaintext      The plaintext to encrypt.
     * @param transformation The transformation to use. For example, "AES/CBC/PKCS5Padding".
     * @param secretKey      The secret key to use.
     * @param params         The algorithm parameter spec to use. Can be null.
     * @param aad            The additional authenticated data to use. Can be null. This is only used for AEAD ciphers. For example, GCM.
     * @return The encrypted ciphertext.
     */
    public static byte[] wholeEncrypt(final byte[] plaintext, final String transformation, final SecretKey secretKey, final AlgorithmParameterSpec params, final byte[] aad) {
        Cipher cipher = getCipher(transformation);
        try {
            if (params != null) {
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, params);
            } else {
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            }
        } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
            throw new IllegalArgumentException(e);
        }
        if (aad != null && aad.length > 0) {
            cipher.updateAAD(aad);
        }
        try {
            return cipher.doFinal(plaintext);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Decrypt the ciphertext using the specified transformation, secret key, and algorithm parameter spec.
     *
     * @param ciphertext     The ciphertext to decrypt.
     * @param transformation The transformation to use. For example, "AES/CBC/PKCS5Padding".
     * @param secretKey      The secret key to use.
     * @param params         The algorithm parameter spec to use. Can be null.
     * @param aad            The additional authenticated data to use. Can be null. This is only used for AEAD ciphers. For example, GCM.
     * @return The decrypted plaintext.
     */
    public static byte[] wholeDecrypt(final byte[] ciphertext, final String transformation, final SecretKey secretKey, final AlgorithmParameterSpec params, final byte[] aad) {
        Cipher cipher = getCipher(transformation);
        try {
            if (params != null) {
                cipher.init(Cipher.DECRYPT_MODE, secretKey, params);
            } else {
                cipher.init(Cipher.DECRYPT_MODE, secretKey);
            }
        } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
            throw new IllegalArgumentException(e);
        }
        if (aad != null && aad.length > 0) {
            cipher.updateAAD(aad);
        }
        try {
            return cipher.doFinal(ciphertext);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private Utils() {
        // don't instantiate
    }
}
