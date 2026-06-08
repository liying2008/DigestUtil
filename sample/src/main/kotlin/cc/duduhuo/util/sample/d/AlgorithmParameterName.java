/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.sample.d;

import java.security.Provider;
import java.security.Security;
import java.util.Set;

public class AlgorithmParameterName {
    /**
     * OpenJDK 11:
     * <p>
     * [SUN] DSA
     * [SunRsaSign] RSASSA-PSS
     * [SunEC] EC
     * [SunJSSE] RSASSA-PSS
     * [SunJCE] PBEWithMD5AndDES
     * [SunJCE] DESede
     * [SunJCE] PBES2
     * [SunJCE] AES
     * [SunJCE] DiffieHellman
     * [SunJCE] PBEWithSHA1AndRC2_128
     * [SunJCE] PBEWithSHA1AndRC4_40
     * [SunJCE] ChaCha20-Poly1305
     * [SunJCE] OAEP
     * [SunJCE] DES
     * [SunJCE] PBEWithHmacSHA224AndAES_256
     * [SunJCE] PBEWithHmacSHA224AndAES_128
     * [SunJCE] PBEWithSHA1AndDESede
     * [SunJCE] RC2
     * [SunJCE] PBEWithSHA1AndRC4_128
     * [SunJCE] PBEWithSHA1AndRC2_40
     * [SunJCE] PBEWithHmacSHA256AndAES_128
     * [SunJCE] PBEWithHmacSHA256AndAES_256
     * [SunJCE] PBEWithHmacSHA512AndAES_128
     * [SunJCE] PBEWithHmacSHA1AndAES_128
     * [SunJCE] PBEWithHmacSHA512AndAES_256
     * [SunJCE] PBEWithHmacSHA1AndAES_256
     * [SunJCE] GCM
     * [SunJCE] Blowfish
     * [SunJCE] PBEWithHmacSHA384AndAES_256
     * [SunJCE] PBEWithHmacSHA384AndAES_128
     * [SunJCE] PBEWithMD5AndTripleDES
     */
    public static void main(String[] args) {
        Provider[] providers = Security.getProviders();
        for (Provider provider : providers) {
            String providerName = provider.getName();
            String version = provider.getVersionStr();
            Set<Provider.Service> services = provider.getServices();
            for (Provider.Service service : services) {
                if (service.getType().equals("AlgorithmParameters")) {
                    System.out.println("[" + providerName + " " + version + "] " + service.getAlgorithm());
                }
            }
        }
    }
}
