/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.sample.d;

import java.security.Provider;
import java.security.Security;
import java.util.Set;

public class CipherName {
    /**
     * OpenJDK 11:
     * <p>
     * [SunJCE] AES_192/CBC/NoPadding
     * [SunJCE] AES_192/OFB/NoPadding
     * [SunJCE] AES_192/CFB/NoPadding
     * [SunJCE] AESWrap_192
     * [SunJCE] PBEWithHmacSHA224AndAES_256
     * [SunJCE] AES_192/ECB/NoPadding
     * [SunJCE] AES_192/GCM/NoPadding
     * [SunJCE] ChaCha20-Poly1305
     * [SunJCE] PBEWithHmacSHA384AndAES_128
     * [SunJCE] AES_128/ECB/NoPadding
     * [SunJCE] AES_128/OFB/NoPadding
     * [SunJCE] AES_128/CBC/NoPadding
     * [SunJCE] AESWrap_128
     * [SunJCE] AES_128/CFB/NoPadding
     * [SunJCE] AES_128/GCM/NoPadding
     * [SunJCE] AES_256/GCM/NoPadding
     * [SunJCE] AES_256/CFB/NoPadding
     * [SunJCE] AESWrap_256
     * [SunJCE] PBEWithMD5AndDES
     * [SunJCE] AES_256/ECB/NoPadding
     * [SunJCE] AES_256/CBC/NoPadding
     * [SunJCE] AES_256/OFB/NoPadding
     * [SunJCE] DESedeWrap
     * [SunJCE] PBEWithHmacSHA224AndAES_128
     * [SunJCE] AES
     * [SunJCE] ChaCha20
     * [SunJCE] DESede
     * [SunJCE] PBEWithHmacSHA512AndAES_128
     * [SunJCE] PBEWithSHA1AndRC2_128
     * [SunJCE] PBEWithSHA1AndRC2_40
     * [SunJCE] PBEWithSHA1AndDESede
     * [SunJCE] PBEWithSHA1AndRC4_128
     * [SunJCE] PBEWithSHA1AndRC4_40
     * [SunJCE] PBEWithHmacSHA512AndAES_256
     * [SunJCE] ARCFOUR
     * [SunJCE] PBEWithHmacSHA256AndAES_256
     * [SunJCE] AESWrap
     * [SunJCE] RSA
     * [SunJCE] RC2
     * [SunJCE] PBEWithHmacSHA256AndAES_128
     * [SunJCE] PBEWithHmacSHA1AndAES_128
     * [SunJCE] DES
     * [SunJCE] PBEWithMD5AndTripleDES
     * [SunJCE] PBEWithHmacSHA1AndAES_256
     * [SunJCE] Blowfish
     * [SunJCE] PBEWithHmacSHA384AndAES_256
     * [SunMSCAPI] RSA/ECB/PKCS1Padding
     * [SunMSCAPI] RSA
     */
    public static void main(String[] args) {
        Provider[] providers = Security.getProviders();
        for (Provider provider : providers) {
            String providerName = provider.getName();
            String version = provider.getVersionStr();
            Set<Provider.Service> services = provider.getServices();
            for (Provider.Service service : services) {
                if (service.getType().equals("Cipher")) {
                    System.out.println("[" + providerName + " " + version + "] " + service.getAlgorithm());
                }
            }
        }
    }
}
