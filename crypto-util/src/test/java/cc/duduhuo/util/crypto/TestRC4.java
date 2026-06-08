/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.crypto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestRC4 {

    @Test
    public void testRC4() {
        String plaintext = "中文abc123";
        String key = "1234567";
        RC4 rc1 = new RC4();
        String e1 = rc1.encrypt(plaintext, key);
        System.out.println("rc1 encrypt: " + e1);
        Assertions.assertEquals("mbd1aUczdw5sBxxh", e1);
        String d1 = rc1.decrypt(e1, key);
        System.out.println("rc1 decrypt: " + d1);
        Assertions.assertEquals(plaintext, d1);

        RC4 rc2 = new RC4().keyLength(16).keyPadding((byte) '0');
        String e2 = rc2.encrypt(plaintext, key);
        Assertions.assertEquals("zC5bsNXmT5JJCzWh", e2);
        System.out.println("rc2 encrypt: " + e2);
        String d2 = rc2.decrypt(e2, key);
        System.out.println("rc2 decrypt: " + d2);
        Assertions.assertEquals(plaintext, d2);

        Assertions.assertNotEquals(e1, e2);
    }
}
