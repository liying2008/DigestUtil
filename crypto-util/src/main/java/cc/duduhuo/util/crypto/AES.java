/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.crypto;

import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

public class AES {
    public static final String ALGORITHM = "AES";

    private abstract static class Base<T extends Base<T>> implements CryptoProtocol {
        /**
         * 密钥 padding（密钥长度不足时的填充字节）。默认 byte 0.
         */
        public byte keyPadding = 0;
        /**
         * 密钥长度（字节数，可取值 16,24,32）。默认 -1, 表示根据传入的密钥长度而定，自动填充到最接近的长度。 例如，传入 16 字节密钥，则刚好到 16 字节，不填充；传入 17 字节密钥，则填充到 24 字节。
         * 如果指定长度，当传入的密钥不足长度时，则填充，超出长度时，则截断。
         * 当 keyLength = -1 时，如果传入密钥长度超过 32 字节，则截断。
         */
        public int keyLength = -1;
        /**
         * Padding模式。默认值：ECB/CBC 为 PKCS5Padding，CFB/OFB/CTR/GCM 为 NoPadding.
         */
        public String paddingMode = getDefaultPaddingMode();
        /**
         * IV(Nonce) 长度（字节数）。默认值：ECB 为 0，CBC/CFB/OFB/CTR 为 16，GCM 为 12.
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
         * 设置密钥长度（字节数，可取值 16,24,32）。默认 -1, 表示根据传入的密钥长度而定，自动填充到最接近的长度。
         */
        public T keyLength(int keyLength) {
            this.keyLength = keyLength;
            return (T) this;
        }

        /**
         * 设置 Padding 模式。默认值：ECB/CBC 为 PKCS5Padding，CFB/OFB/CTR/GCM 为 NoPadding.
         */
        public T paddingMode(String paddingMode) {
            this.paddingMode = paddingMode;
            return (T) this;
        }

        /**
         * 设置 IV(Nonce) 长度（字节数）。默认值：ECB 为 0，CBC/CFB/OFB/CTR 为 16，GCM 为 12.
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
            return Utils.createAesKey(key, keyLength, keyPadding);
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
            return 16;
        }
    }

    /**
     * CFB (Cipher Feedback - 密码反馈模式)
     */
    public static class CFB extends Base<CFB> {
        /**
         * 反馈段的长度（feedback segment size，单位：bit），每次反馈和处理多少位数据（如：8,32,64,128）。默认 128
         */
        public int segmentSize = 128;

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
            return 16;
        }
    }

    /**
     * OFB (Output Feedback - 输出反馈模式)
     */
    public static class OFB extends Base<OFB> {
        /**
         * 反馈段的长度（feedback segment size，单位：bit），每次反馈和处理多少位数据（如：8,32,64,128）。默认 128
         */
        public int segmentSize = 128;

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
            return 16;
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
            return 16;
        }
    }

    /**
     * GCM (Galois/Counter Mode - 伽罗瓦/计数器模式)
     */
    public static class GCM extends Base<GCM> {
        /**
         * GCM 认证标签长度，必须是 128, 120, 112, 104, 或 96 位。强烈建议使用 128 位 (16 字节)
         */
        public int tagLengthBit = 128;

        public GCM tagLengthBit(int tagLengthBit) {
            this.tagLengthBit = tagLengthBit;
            return this;
        }

        @Override
        public String getMode() {
            return "GCM";
        }

        @Override
        protected String getDefaultPaddingMode() {
            return "NoPadding";
        }

        @Override
        protected int getDefaultIvLength() {
            return 12;
        }

        @Override
        public ParameterSpecAndIv getParameterSpecAndIv(final byte[] iv) {
            if (iv != null) {
                return new ParameterSpecAndIv(new GCMParameterSpec(tagLengthBit, iv), iv);
            }
            byte[] randomIv = createIv();
            return new ParameterSpecAndIv(new GCMParameterSpec(tagLengthBit, randomIv), randomIv);
        }
    }
}
