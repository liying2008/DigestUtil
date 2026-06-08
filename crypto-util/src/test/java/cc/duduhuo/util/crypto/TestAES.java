/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.crypto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

public class TestAES {
    // 16 字节长度
    private static final String IV = "n834nbf#0ewfv4d0";

    static class TestingCBC extends AES.CBC {
        @Override
        public byte[] createIv() {
            return IV.getBytes(StandardCharsets.UTF_8);
        }
    }

    static class TestingCFB extends AES.CFB {
        @Override
        public byte[] createIv() {
            return IV.getBytes(StandardCharsets.UTF_8);
        }
    }

    static class TestingOFB extends AES.OFB {
        @Override
        public byte[] createIv() {
            return IV.getBytes(StandardCharsets.UTF_8);
        }
    }

    static class TestingCTR extends AES.CTR {
        @Override
        public byte[] createIv() {
            return IV.getBytes(StandardCharsets.UTF_8);
        }
    }

    static class TestingGCM extends AES.GCM {
        @Override
        public byte[] createIv() {
            return IV.substring(0, 12).getBytes(StandardCharsets.UTF_8);
        }
    }

    @Test
    public void testAll() {
        testECB();
        testCBC();
        testCFB();
        testOFB();
        testCTR();
        testGCM();
    }

    @Test
    public void testECB() {
        String plaintext = "中文abc123";
        String key = "12345678";
        AES.ECB ecb1 = new AES.ECB();
        String e1 = ecb1.encrypt(plaintext, key);
        System.out.println("ecb1 encrypt: " + e1);
        Assertions.assertEquals("jm++ElxeJsjbSbgFFFd97A==", e1);
        String d1 = ecb1.decrypt(e1, key);
        System.out.println("ecb1 decrypt: " + d1);
        Assertions.assertEquals(plaintext, d1);

        AES.ECB ecb2 = new AES.ECB().keyLength(32).keyPadding((byte) '0');
        String e2 = ecb2.encrypt(plaintext, key);
        System.out.println("ecb2 encrypt: " + e2);
        Assertions.assertEquals("XwkL3eJJiio3KqMdqqWmsQ==", e2);
        String d2 = ecb2.decrypt(e2, key);
        System.out.println("ecb2 decrypt: " + d2);
        Assertions.assertEquals(plaintext, d2);
    }

    @Test
    public void testCBC() {
        String plaintext = "中文abc123";
        String key = "12345678";
        AES.CBC cbc1 = new AES.CBC();
        String e1 = cbc1.encrypt(plaintext, key);
        System.out.println("cbc1 encrypt: " + e1);
        String d1 = cbc1.decrypt(e1, key);
        System.out.println("cbc1 decrypt: " + d1);
        Assertions.assertEquals(plaintext, d1);

        AES.CBC cbc2 = new AES.CBC().keyLength(32).keyPadding((byte) '0');
        String e2 = cbc2.encrypt(plaintext, key);
        System.out.println("cbc2 encrypt: " + e2);
        String d2 = cbc2.decrypt(e2, key);
        System.out.println("cbc2 decrypt: " + d2);
        Assertions.assertEquals(plaintext, d2);

        Assertions.assertNotEquals(e1, e2);

        TestingCBC cbc3 = (TestingCBC) new TestingCBC().keyLength(16).keyPadding((byte) '0');
        String e3 = cbc3.encrypt(plaintext, key);
        System.out.println("cbc3 encrypt: " + e3);
        Assertions.assertEquals("bjgzNG5iZiMwZXdmdjRkMP3s6cfIadAjqIepuqq76OQ=", e3);
        String d3 = cbc3.decrypt(e3, key);
        System.out.println("cbc3 decrypt: " + d3);
        Assertions.assertEquals(plaintext, d3);
    }

    @Test
    public void testCFB() {
        String plaintext = "中文abc123";
        String key = "12345678";
        AES.CFB cfb1 = new AES.CFB();
        String e1 = cfb1.encrypt(plaintext, key);
        System.out.println("cfb1 encrypt: " + e1);
        String d1 = cfb1.decrypt(e1, key);
        System.out.println("cfb1 decrypt: " + d1);
        Assertions.assertEquals(plaintext, d1);

        AES.CFB cfb2 = new AES.CFB().keyLength(32).keyPadding((byte) '0');
        String e2 = cfb2.encrypt(plaintext, key);
        System.out.println("cfb2 encrypt: " + e2);
        String d2 = cfb2.decrypt(e2, key);
        System.out.println("cfb2 decrypt: " + d2);
        Assertions.assertEquals(plaintext, d2);

        Assertions.assertNotEquals(e1, e2);

        TestingCFB cfb3 = (TestingCFB) new TestingCFB().keyLength(16).keyPadding((byte) '0');
        String e3 = cfb3.encrypt(plaintext, key);
        System.out.println("cfb3 encrypt: " + e3);
        Assertions.assertEquals("bjgzNG5iZiMwZXdmdjRkMNEFUgHuJwq0tToXTA==", e3);
        String d3 = cfb3.decrypt(e3, key);
        System.out.println("cfb3 decrypt: " + d3);
        Assertions.assertEquals(plaintext, d3);
    }

    @Test
    public void testOFB() {
        String plaintext = "中文abc123";
        String key = "12345678";
        AES.OFB ofb1 = new AES.OFB();
        String e1 = ofb1.encrypt(plaintext, key);
        System.out.println("ofb1 encrypt: " + e1);
        String d1 = ofb1.decrypt(e1, key);
        System.out.println("ofb1 decrypt: " + d1);
        Assertions.assertEquals(plaintext, d1);

        AES.OFB ofb2 = new AES.OFB().keyLength(32).keyPadding((byte) '0');
        String e2 = ofb2.encrypt(plaintext, key);
        System.out.println("ofb2 encrypt: " + e2);
        String d2 = ofb2.decrypt(e2, key);
        System.out.println("ofb2 decrypt: " + d2);
        Assertions.assertEquals(plaintext, d2);

        Assertions.assertNotEquals(e1, e2);

        TestingOFB ofb3 = (TestingOFB) new TestingOFB().keyLength(16).keyPadding((byte) '0');
        String e3 = ofb3.encrypt(plaintext, key);
        System.out.println("ofb3 encrypt: " + e3);
        Assertions.assertEquals("bjgzNG5iZiMwZXdmdjRkMNEFUgHuJwq0tToXTA==", e3);
        String d3 = ofb3.decrypt(e3, key);
        System.out.println("ofb3 decrypt: " + d3);
        Assertions.assertEquals(plaintext, d3);
    }

    @Test
    public void testCTR() {
        String plaintext = "中文abc123";
        String key = "12345678";
        AES.CTR ctr1 = new AES.CTR();
        String e1 = ctr1.encrypt(plaintext, key);
        System.out.println("ctr1 encrypt: " + e1);
        String d1 = ctr1.decrypt(e1, key);
        System.out.println("ctr1 decrypt: " + d1);
        Assertions.assertEquals(plaintext, d1);

        AES.CTR ctr2 = new AES.CTR().keyLength(32).keyPadding((byte) '0');
        String e2 = ctr2.encrypt(plaintext, key);
        System.out.println("ctr2 encrypt: " + e2);
        String d2 = ctr2.decrypt(e2, key);
        System.out.println("ctr2 decrypt: " + d2);
        Assertions.assertEquals(plaintext, d2);

        Assertions.assertNotEquals(e1, e2);

        TestingCTR ctr3 = (TestingCTR) new TestingCTR().keyLength(16).keyPadding((byte) '0');
        String e3 = ctr3.encrypt(plaintext, key);
        System.out.println("ctr3 encrypt: " + e3);
        Assertions.assertEquals("bjgzNG5iZiMwZXdmdjRkMNEFUgHuJwq0tToXTA==", e3);
        String d3 = ctr3.decrypt(e3, key);
        System.out.println("ctr3 decrypt: " + d3);
        Assertions.assertEquals(plaintext, d3);
    }

    @Test
    public void testGCM() {
        String plaintext = "中文abc123";
        String key = "12345678";
        AES.GCM gcm1 = new AES.GCM();
        String e1 = gcm1.encrypt(plaintext, key);
        System.out.println("gcm1 encrypt: " + e1);
        String d1 = gcm1.decrypt(e1, key);
        System.out.println("gcm1 decrypt: " + d1);
        Assertions.assertEquals(plaintext, d1);

        AES.GCM gcm2 = new AES.GCM().keyLength(32).keyPadding((byte) '0').tagLengthBit(96);
        String e2 = gcm2.encrypt(plaintext, key);
        System.out.println("gcm2 encrypt: " + e2);
        String d2 = gcm2.decrypt(e2, key);
        System.out.println("gcm2 decrypt: " + d2);
        Assertions.assertEquals(plaintext, d2);

        Assertions.assertNotEquals(e1, e2);

        TestingGCM gcm3 = (TestingGCM) new TestingGCM().keyLength(16).keyPadding((byte) '0').tagLengthBit(128);
        String e3 = gcm3.encrypt(plaintext, key);
        System.out.println("gcm3 encrypt: " + e3);
        Assertions.assertEquals("bjgzNG5iZiMwZXdm1zWVZx04KMkn0NHXIWbzsGEOoR5Tfv6bUd7Zdg==", e3);
        String d3 = gcm3.decrypt(e3, key);
        System.out.println("gcm3 decrypt: " + d3);
        Assertions.assertEquals(plaintext, d3);

        String aad = "teststring";
        TestingGCM gcm4 = (TestingGCM) new TestingGCM().keyLength(16).keyPadding((byte) '0').tagLengthBit(128);
        String e4 = gcm4.encrypt(plaintext, key, aad);
        System.out.println("gcm4 encrypt: " + e4);
        Assertions.assertEquals("bjgzNG5iZiMwZXdm1zWVZx04KMkn0NHX0Q+e8u8HxRTfy7JuVhLKdw==", e4);
        String d4 = gcm4.decrypt(e4, key, aad);
        System.out.println("gcm4 decrypt: " + d4);
        Assertions.assertEquals(plaintext, d4);
    }
}
