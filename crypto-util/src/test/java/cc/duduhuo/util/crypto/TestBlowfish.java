/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.crypto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

public class TestBlowfish {
    // 8 字节长度
    private static final String IV = "n834nbf#";

    static class TestingCBC extends Blowfish.CBC {
        @Override
        public byte[] createIv() {
            return IV.getBytes(StandardCharsets.UTF_8);
        }
    }

    static class TestingCFB extends Blowfish.CFB {
        @Override
        public byte[] createIv() {
            return IV.getBytes(StandardCharsets.UTF_8);
        }
    }

    static class TestingOFB extends Blowfish.OFB {
        @Override
        public byte[] createIv() {
            return IV.getBytes(StandardCharsets.UTF_8);
        }
    }

    static class TestingCTR extends Blowfish.CTR {
        @Override
        public byte[] createIv() {
            return IV.getBytes(StandardCharsets.UTF_8);
        }
    }

    @Test
    public void testAll() {
        testECB();
        testCBC();
        testCFB();
        testOFB();
        testCTR();
    }

    @Test
    public void testECB() {
        String plaintext = "中文abc123";
        String key = "1234567";
        Blowfish.ECB ecb1 = new Blowfish.ECB();
        String e1 = ecb1.encrypt(plaintext, key);
        System.out.println("ecb1 encrypt: " + e1);
        Assertions.assertEquals("TIjDK2RP7eoVPJwoj84kPw==", e1);
        String d1 = ecb1.decrypt(e1, key);
        System.out.println("ecb1 decrypt: " + d1);
        Assertions.assertEquals(plaintext, d1);

        Blowfish.ECB ecb2 = new Blowfish.ECB().keyLength(56).keyPadding((byte) '0');
        String e2 = ecb2.encrypt(plaintext, key);
        System.out.println("ecb2 encrypt: " + e2);
        Assertions.assertEquals("HN8GYyf1Te112fT92gCpFA==", e2);
        String d2 = ecb2.decrypt(e2, key);
        System.out.println("ecb2 decrypt: " + d2);
        Assertions.assertEquals(plaintext, d2);
    }

    @Test
    public void testCBC() {
        String plaintext = "中文abc123";
        String key = "1234567";
        Blowfish.CBC cbc1 = new Blowfish.CBC();
        String e1 = cbc1.encrypt(plaintext, key);
        System.out.println("cbc1 encrypt: " + e1);
        String d1 = cbc1.decrypt(e1, key);
        System.out.println("cbc1 decrypt: " + d1);
        Assertions.assertEquals(plaintext, d1);

        Blowfish.CBC cbc2 = new Blowfish.CBC().keyLength(56).keyPadding((byte) '0');
        String e2 = cbc2.encrypt(plaintext, key);
        System.out.println("cbc2 encrypt: " + e2);
        String d2 = cbc2.decrypt(e2, key);
        System.out.println("cbc2 decrypt: " + d2);
        Assertions.assertEquals(plaintext, d2);

        Assertions.assertNotEquals(e1, e2);

        TestingCBC cbc3 = (TestingCBC) new TestingCBC().keyLength(56).keyPadding((byte) '0');
        String e3 = cbc3.encrypt(plaintext, key);
        System.out.println("cbc3 encrypt: " + e3);
        Assertions.assertEquals("bjgzNG5iZiN3qTxtvEQGCIewHvsil2yP", e3);
        String d3 = cbc3.decrypt(e3, key);
        System.out.println("cbc3 decrypt: " + d3);
        Assertions.assertEquals(plaintext, d3);
    }

    @Test
    public void testCFB() {
        String plaintext = "中文abc123";
        String key = "1234567";
        Blowfish.CFB cfb1 = new Blowfish.CFB();
        String e1 = cfb1.encrypt(plaintext, key);
        System.out.println("cfb1 encrypt: " + e1);
        String d1 = cfb1.decrypt(e1, key);
        System.out.println("cfb1 decrypt: " + d1);
        Assertions.assertEquals(plaintext, d1);

        Blowfish.CFB cfb2 = new Blowfish.CFB().keyLength(56).keyPadding((byte) '0');
        String e2 = cfb2.encrypt(plaintext, key);
        System.out.println("cfb2 encrypt: " + e2);
        String d2 = cfb2.decrypt(e2, key);
        System.out.println("cfb2 decrypt: " + d2);
        Assertions.assertEquals(plaintext, d2);

        Assertions.assertNotEquals(e1, e2);

        TestingCFB cfb3 = (TestingCFB) new TestingCFB().keyLength(56).keyPadding((byte) '0');
        String e3 = cfb3.encrypt(plaintext, key);
        System.out.println("cfb3 encrypt: " + e3);
        Assertions.assertEquals("bjgzNG5iZiONXDuk5RRy8jRoBgQ=", e3);
        String d3 = cfb3.decrypt(e3, key);
        System.out.println("cfb3 decrypt: " + d3);
        Assertions.assertEquals(plaintext, d3);
    }

    @Test
    public void testOFB() {
        String plaintext = "中文abc123";
        String key = "1234567";
        Blowfish.OFB ofb1 = new Blowfish.OFB();
        String e1 = ofb1.encrypt(plaintext, key);
        System.out.println("ofb1 encrypt: " + e1);
        String d1 = ofb1.decrypt(e1, key);
        System.out.println("ofb1 decrypt: " + d1);
        Assertions.assertEquals(plaintext, d1);

        Blowfish.OFB ofb2 = new Blowfish.OFB().keyLength(56).keyPadding((byte) '0');
        String e2 = ofb2.encrypt(plaintext, key);
        System.out.println("ofb2 encrypt: " + e2);
        String d2 = ofb2.decrypt(e2, key);
        System.out.println("ofb2 decrypt: " + d2);
        Assertions.assertEquals(plaintext, d2);

        Assertions.assertNotEquals(e1, e2);

        TestingOFB ofb3 = (TestingOFB) new TestingOFB().keyLength(56).keyPadding((byte) '0');
        String e3 = ofb3.encrypt(plaintext, key);
        System.out.println("ofb3 encrypt: " + e3);
        Assertions.assertEquals("bjgzNG5iZiONXDuk5RRy8kmcBhg=", e3);
        String d3 = ofb3.decrypt(e3, key);
        System.out.println("ofb3 decrypt: " + d3);
        Assertions.assertEquals(plaintext, d3);
    }

    @Test
    public void testCTR() {
        String plaintext = "中文abc123";
        String key = "1234567";
        Blowfish.CTR ctr1 = new Blowfish.CTR();
        String e1 = ctr1.encrypt(plaintext, key);
        System.out.println("ctr1 encrypt: " + e1);
        String d1 = ctr1.decrypt(e1, key);
        System.out.println("ctr1 decrypt: " + d1);
        Assertions.assertEquals(plaintext, d1);

        Blowfish.CTR ctr2 = new Blowfish.CTR().keyLength(56).keyPadding((byte) '0');
        String e2 = ctr2.encrypt(plaintext, key);
        System.out.println("ctr2 encrypt: " + e2);
        String d2 = ctr2.decrypt(e2, key);
        System.out.println("ctr2 decrypt: " + d2);
        Assertions.assertEquals(plaintext, d2);

        Assertions.assertNotEquals(e1, e2);

        TestingCTR ctr3 = (TestingCTR) new TestingCTR().keyLength(56).keyPadding((byte) '0');
        String e3 = ctr3.encrypt(plaintext, key);
        System.out.println("ctr3 encrypt: " + e3);
        Assertions.assertEquals("bjgzNG5iZiONXDuk5RRy8jNH1EI=", e3);
        String d3 = ctr3.decrypt(e3, key);
        System.out.println("ctr3 decrypt: " + d3);
        Assertions.assertEquals(plaintext, d3);
    }
}
