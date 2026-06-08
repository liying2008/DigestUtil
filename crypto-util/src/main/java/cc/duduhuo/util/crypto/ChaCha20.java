/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.crypto;

import javax.crypto.SecretKey;
import javax.crypto.spec.ChaCha20ParameterSpec;

public class ChaCha20 implements CryptoProtocol {
    public static final String ALGORITHM = "ChaCha20";
    /**
     * 密钥长度，固定为 32 字节。
     */
    public static final int KEY_LENGTH = 32;
    /**
     * Java 中 ChaCha20 的 Nonce 必须是 12 字节 (96 位)
     */
    public static final int NONCE_LENGTH = 12;
    /**
     * 密钥 padding（密钥长度不足时的填充字节）。默认 byte 0.
     */
    public byte keyPadding = 0;
    /**
     * 初始计数器值。
     * 根据 RFC 7539，通常 block counter 从 1 开始 (0 留给 Poly1305 生成密钥用)
     */
    public int counter = 1;

    /**
     * 设置密钥 padding（密钥长度不足时的填充字节）。默认 byte 0
     */
    public ChaCha20 keyPadding(byte keyPadding) {
        this.keyPadding = keyPadding;
        return this;
    }

    /**
     * 设置初始计数器值。
     * 根据 RFC 7539，通常 block counter 从 1 开始 (0 留给 Poly1305 生成密钥用)
     */
    public ChaCha20 counter(int counter) {
        this.counter = counter;
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

    @Override
    public ParameterSpecAndIv getParameterSpecAndIv(byte[] iv) {
        if (iv != null) {
            return new ParameterSpecAndIv(new ChaCha20ParameterSpec(iv, counter), iv);
        }
        byte[] nonce = createIv();
        // 注意：纯 ChaCha20 必须使用 ChaCha20ParameterSpec 指定 Nonce 和 Counter
        return new ParameterSpecAndIv(new ChaCha20ParameterSpec(nonce, counter), nonce);
    }
}
