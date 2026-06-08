/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.sample.d;

import java.security.Provider;
import java.security.Security;
import java.util.Set;

public class MDName {
    /**
     * OpenJDK 11:
     * <p>
     * [SUN] SHA3-224
     * [SUN] SHA3-384
     * [SUN] SHA3-256
     * [SUN] SHA-512
     * [SUN] SHA
     * [SUN] SHA-512/256
     * [SUN] SHA3-512
     * [SUN] SHA-384
     * [SUN] SHA-256
     * [SUN] SHA-512/224
     * [SUN] SHA-224
     * [SUN] MD5
     * [SUN] MD2
     */
    public static void main(String[] args) {
        Provider[] providers = Security.getProviders();
        for (Provider provider : providers) {
            String providerName = provider.getName();
            String version = provider.getVersionStr();
            Set<Provider.Service> services = provider.getServices();
            for (Provider.Service service : services) {
                if (service.getType().equals("MessageDigest")) {
                    System.out.println("[" + providerName + " " + version + "] " + service.getAlgorithm());
                }
            }
        }
    }
}
