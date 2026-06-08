/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.crypto;

import javax.crypto.SecretKey;

public class ChaCha20Poly1305 implements CryptoProtocol {
    public static final String ALGORITHM = "ChaCha20-Poly1305";
    /**
     * 密钥长度，固定为 32 字节。
     */
    public static final int KEY_LENGTH = 32;
    /**
     * Java 中 ChaCha20-Poly1305 的 Nonce 必须是 12 字节 (96 位)
     */
    public static final int NONCE_LENGTH = 12;
    /**
     * 密钥 padding（密钥长度不足时的填充字节）。默认 byte 0.
     */
    public byte keyPadding = 0;

    /**
     * 设置密钥 padding（密钥长度不足时的填充字节）。默认 byte 0
     */
    public ChaCha20Poly1305 keyPadding(byte keyPadding) {
        this.keyPadding = keyPadding;
        return this;
    }

    @Override
    public String getAlgorithm() {
        return ALGORITHM;
    }

    @Override
    public int getIvLength() {
        return NONCE_LENGTH;
    }

    @Override
    public SecretKey createKey(byte[] key) {
        return Utils.createChaCha20Key(key, keyPadding);
    }
}
