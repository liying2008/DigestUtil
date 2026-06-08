/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.sample;

import cc.duduhuo.util.crypto.AES;
import cc.duduhuo.util.crypto.Blowfish;
import cc.duduhuo.util.crypto.ChaCha20;
import cc.duduhuo.util.crypto.ChaCha20Poly1305;
import cc.duduhuo.util.crypto.DES;
import cc.duduhuo.util.crypto.RC2;
import cc.duduhuo.util.crypto.RC4;
import cc.duduhuo.util.crypto.TripleDES;

public class CryptoUtilDemo {
    public static void aes() {
        System.out.println("============== AES/GCM ==============");
        String plaintext = "中文abc123";
        String key = "0123456789abcdef";
        AES.GCM cipher = new AES.GCM();
        String ct = cipher.encrypt(plaintext, key);
        System.out.println("[AES/GCM] encrypted: " + ct); // [AES/GCM] encrypted: aRsw9ZBEsyVPJR+oLJ4s8yeGQkX76NLGAOaxqjqyyzhdKn3hq9C13w==
        String pt = cipher.decrypt(ct, key);
        System.out.println("[AES/GCM] decrypted: " + pt); // [AES/GCM] decrypted: 中文abc123
        // 还支持 AES/ECB、AES/CBC、AES/CTR、AES/CFB、AES/OFB 等模式
    }

    public static void des() {
        System.out.println("============== DES/CBC ==============");
        String plaintext = "中文abc123";
        String key = "01234567";
        DES.CBC cipher = new DES.CBC();
        String ct = cipher.encrypt(plaintext, key);
        System.out.println("[DES/CBC] encrypted: " + ct); // [DES/CBC] encrypted: 7t9J4VIa2TiZtV+SmNlYYF6EaSNnbjdE
        String pt = cipher.decrypt(ct, key);
        System.out.println("[DES/CBC] decrypted: " + pt); // [DES/CBC] decrypted: 中文abc123
        // 还支持 DES/ECB、DES/CTR、DES/CFB、DES/OFB 等模式
    }

    public static void tripleDes() {
        System.out.println("============== Triple DES/CBC ==============");
        String plaintext = "中文abc123";
        String key = "0123456789abcdef01234567";
        TripleDES.CBC cipher = new TripleDES.CBC();
        String ct = cipher.encrypt(plaintext, key);
        System.out.println("[Triple DES/CBC] encrypted: " + ct); // [Triple DES/CBC] encrypted: 78Rn4ZSWREOFBlwhDCJkOmiXXNQNz8do
        String pt = cipher.decrypt(ct, key);
        System.out.println("[Triple DES/CBC] decrypted: " + pt); // [Triple DES/CBC] decrypted: 中文abc123
        // 还支持 DESede/ECB、DESede/CTR、DESede/CFB、DESede/OFB 等模式
    }

    public static void blowfish() {
        System.out.println("============== Blowfish/CBC ==============");
        String plaintext = "中文abc123";
        String key = "01234567";
        Blowfish.CBC cipher = new Blowfish.CBC();
        String ct = cipher.encrypt(plaintext, key);
        System.out.println("[Blowfish/CBC] encrypted: " + ct); // [Blowfish/CBC] encrypted: 6k2MvcN3a1APzrsYlUZSuCkd7S7Ejd0f
        String pt = cipher.decrypt(ct, key);
        System.out.println("[Blowfish/CBC] decrypted: " + pt); // [Blowfish/CBC] decrypted: 中文abc123
        // 还支持 Blowfish/ECB、Blowfish/CTR、Blowfish/CFB、Blowfish/OFB 等模式
    }

    public static void rc2() {
        System.out.println("============== RC2/CBC ==============");
        String plaintext = "中文abc123";
        String key = "01234567";
        RC2.CBC cipher = new RC2.CBC();
        String ct = cipher.encrypt(plaintext, key);
        System.out.println("[RC2/CBC] encrypted: " + ct); // [RC2/CBC] encrypted: 3uZ0Y8JENBRx6a2vpTGzCnbHSg5oBWb8
        String pt = cipher.decrypt(ct, key);
        System.out.println("[RC2/CBC] decrypted: " + pt); // [RC2/CBC] decrypted: 中文abc123
        // 还支持 RC2/ECB、RC2/CTR、RC2/CFB、RC2/OFB 等模式
    }

    public static void rc4() {
        System.out.println("============== RC4 ==============");
        String plaintext = "中文abc123";
        String key = "01234567";
        RC4 cipher = new RC4();
        String ct = cipher.encrypt(plaintext, key);
        System.out.println("[RC4] encrypted: " + ct); // [RC4] encrypted: dW1e+dCgRFJTYolp
        String pt = cipher.decrypt(ct, key);
        System.out.println("[RC4] decrypted: " + pt); // [RC4] decrypted: 中文abc123
    }

    public static void chacha20() {
        System.out.println("============== ChaCha20 ==============");
        String plaintext = "中文abc123";
        String key = "0123456789abcdef0123456789abcdef";
        ChaCha20 cipher = new ChaCha20();
        String ct = cipher.encrypt(plaintext, key);
        System.out.println("[ChaCha20] encrypted: " + ct); // [ChaCha20] encrypted: O43JS57JHA6m/OtlC/E4lSFU95KwwKkB
        String pt = cipher.decrypt(ct, key);
        System.out.println("[ChaCha20] decrypted: " + pt); // [ChaCha20] decrypted: 中文abc123
    }

    public static void chacha20Poly1305() {
        System.out.println("============== ChaCha20-Poly1305 ==============");
        String plaintext = "中文abc123";
        String key = "0123456789abcdef0123456789abcdef";
        ChaCha20Poly1305 cipher = new ChaCha20Poly1305();
        String ct = cipher.encrypt(plaintext, key);
        System.out.println("[ChaCha20-Poly1305] encrypted: " + ct); // [ChaCha20-Poly1305] encrypted: s3q6HsDD8kTQANjgU5D6ynec8ElKuy6eSF5Jc1WVf54eGE5dKZnejw==
        String pt = cipher.decrypt(ct, key);
        System.out.println("[ChaCha20-Poly1305] decrypted: " + pt); // [ChaCha20-Poly1305] decrypted: 中文abc123
    }

    public static void main(String[] args) {
        aes();
        des();
        tripleDes();
        blowfish();
        rc2();
        rc4();
        chacha20();
        chacha20Poly1305();
    }
}
