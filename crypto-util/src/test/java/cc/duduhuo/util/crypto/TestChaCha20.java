/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.crypto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

public class TestChaCha20 {
    // 16 字节长度
    private static final String IV = "n834nbf#0ewfv4d0";

    static class TestingChaCha20 extends ChaCha20 {
        @Override
        public byte[] createIv() {
            return IV.substring(0, 12).getBytes(StandardCharsets.UTF_8);
        }
    }

    @Test
    public void testChaCha20() {
        String plaintext = "中文abc123";
        String key = "1234567";
        ChaCha20 cc1 = new ChaCha20();
        String e1 = cc1.encrypt(plaintext, key);
        System.out.println("cc1 encrypt: " + e1);
        String d1 = cc1.decrypt(e1, key);
        System.out.println("cc1 decrypt: " + d1);
        Assertions.assertEquals(plaintext, d1);

        ChaCha20 cc2 = new ChaCha20().keyPadding((byte) '0');
        String e2 = cc2.encrypt(plaintext, key);
        System.out.println("cc2 encrypt: " + e2);
        String d2 = cc2.decrypt(e2, key);
        System.out.println("cc2 decrypt: " + d2);
        Assertions.assertEquals(plaintext, d2);

        Assertions.assertNotEquals(e1, e2);

        TestingChaCha20 cc3 = (TestingChaCha20) new TestingChaCha20().counter(1).keyPadding((byte) '0');
        String e3 = cc3.encrypt(plaintext, key);
        System.out.println("cc3 encrypt: " + e3);
        Assertions.assertEquals("bjgzNG5iZiMwZXdm/OGh2Jcep2ldH5Km", e3);
        String d3 = cc3.decrypt(e3, key);
        System.out.println("cc3 decrypt: " + d3);
        Assertions.assertEquals(plaintext, d3);
    }
}
