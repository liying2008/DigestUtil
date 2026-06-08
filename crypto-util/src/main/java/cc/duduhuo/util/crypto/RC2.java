/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.crypto;

import javax.crypto.SecretKey;

public class RC2 {
    public static final String ALGORITHM = "RC2";

    private abstract static class Base<T extends Base<T>> implements CryptoProtocol {
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
         * Padding模式。默认值：ECB/CBC 为 PKCS5Padding，CFB/OFB/CTR 为 NoPadding.
         */
        public String paddingMode = getDefaultPaddingMode();
        /**
         * IV(Nonce) 长度（字节数）。默认值：ECB 为 0，CBC/CFB/OFB/CTR 为 8.
         */
        public int ivLength = getDefaultIvLength();

        /**
         * 设置密钥 padding（密钥长度不足时的填充字节）。默认 byte 0
         */
        public T keyPadding(byte keyPadding) {
            this.keyPadding = keyPadding;
            return (T) this;
        }

        /**
         * 设置密钥长度（字节数，可取值 5-128）。默认 -1, 表示根据传入的密钥长度而定，最小5字节，最大128字节，不足5字节则填充，超过128字节则截断。
         */
        public T keyLength(int keyLength) {
            this.keyLength = keyLength;
            return (T) this;
        }

        /**
         * 设置 Padding 模式。默认值：ECB/CBC 为 PKCS5Padding，CFB/OFB/CTR 为 NoPadding.
         */
        public T paddingMode(String paddingMode) {
            this.paddingMode = paddingMode;
            return (T) this;
        }

        /**
         * 设置 IV(Nonce) 长度（字节数）。默认值：ECB 为 0，CBC/CFB/OFB/CTR 为 8.
         */
        public T ivLength(int ivLength) {
            this.ivLength = ivLength;
            return (T) this;
        }

        @Override
        public String getAlgorithm() {
            return ALGORITHM;
        }

        @Override
        public String getPaddingMode() {
            return paddingMode;
        }

        @Override
        public int getIvLength() {
            return ivLength;
        }

        @Override
        public SecretKey createKey(byte[] key) {
            return Utils.createRc2Key(key, keyLength, keyPadding);
        }

        protected abstract int getDefaultIvLength();

        protected abstract String getDefaultPaddingMode();
    }

    /**
     * ECB (Electronic Codebook - 电子密码本模式)
     */
    public static class ECB extends Base<ECB> {
        @Override
        public String getMode() {
            return "ECB";
        }

        @Override
        protected String getDefaultPaddingMode() {
            return "PKCS5Padding";
        }

        @Override
        protected int getDefaultIvLength() {
            return 0;
        }
    }

    /**
     * CBC (Cipher Block Chaining - 密码分组链接模式)
     */
    public static class CBC extends Base<CBC> {
        @Override
        public String getMode() {
            return "CBC";
        }

        @Override
        protected String getDefaultPaddingMode() {
            return "PKCS5Padding";
        }

        @Override
        protected int getDefaultIvLength() {
            return 8;
        }
    }

    /**
     * CFB (Cipher Feedback - 密码反馈模式)
     */
    public static class CFB extends Base<CFB> {
        /**
         * 反馈段的长度（feedback segment size，单位：bit），每次反馈和处理多少位数据（如：8,32,64）。默认 64
         */
        public int segmentSize = 64;

        public CFB segmentSize(int segmentSize) {
            this.segmentSize = segmentSize;
            return this;
        }

        @Override
        public String getMode() {
            return "CFB" + segmentSize;
        }

        @Override
        protected String getDefaultPaddingMode() {
            return "NoPadding";
        }

        @Override
        protected int getDefaultIvLength() {
            return 8;
        }
    }

    /**
     * OFB (Output Feedback - 输出反馈模式)
     */
    public static class OFB extends Base<OFB> {
        /**
         * 反馈段的长度（feedback segment size，单位：bit），每次反馈和处理多少位数据（如：8,32,64）。默认 64
         */
        public int segmentSize = 64;

        public OFB segmentSize(int segmentSize) {
            this.segmentSize = segmentSize;
            return this;
        }

        @Override
        public String getMode() {
            return "OFB" + segmentSize;
        }

        @Override
        protected String getDefaultPaddingMode() {
            return "NoPadding";
        }

        @Override
        protected int getDefaultIvLength() {
            return 8;
        }
    }

    /**
     * CTR (Counter - 计数器模式)
     */
    public static class CTR extends Base<CTR> {
        @Override
        public String getMode() {
            return "CTR";
        }

        @Override
        protected String getDefaultPaddingMode() {
            return "NoPadding";
        }

        @Override
        protected int getDefaultIvLength() {
            return 8;
        }
    }
}
