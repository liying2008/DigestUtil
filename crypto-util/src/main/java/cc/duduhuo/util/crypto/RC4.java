/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.crypto;

import javax.crypto.SecretKey;

public class RC4 implements CryptoProtocol {
    public static final String ALGORITHM = "RC4";

    /**
     * 密钥 padding（密钥长度不足时的填充字节）。默认 byte 0.
     */
    public byte keyPadding = 0;
    /**
     * 密钥长度（字节数，可取值 5-128）。默认 -1, 表示根据传入的密钥长度而定，最小5字节，最大128字节，不足5字节则填充，超过128字节则截断。
     * 如果指定长度，当传入的密钥不足长度时，则填充，超出长度时，则截断。
     */
    public int keyLength = -1;

    /**
     * 设置密钥 padding（密钥长度不足时的填充字节）。默认 byte 0
     */
    public RC4 keyPadding(byte keyPadding) {
        this.keyPadding = keyPadding;
        return this;
    }

    /**
     * 设置密钥长度（字节数，可取值 5-128）。默认 -1, 表示根据传入的密钥长度而定，最小5字节，最大128字节，不足5字节则填充，超过128字节则截断。
     */
    public RC4 keyLength(int keyLength) {
        this.keyLength = keyLength;
        return this;
    }

    @Override
    public String getAlgorithm() {
        return ALGORITHM;
    }

    @Override
    public int getIvLength() {
        return 0;
    }

    @Override
    public SecretKey createKey(byte[] key) {
        return Utils.createRc4Key(key, keyLength, keyPadding);
    }
}
