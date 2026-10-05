/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.crypto.kdf;

import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

import static cc.duduhuo.util.crypto.kdf.Pbkdf2.Algorithm.HMAC_SHA_256;
import static cc.duduhuo.util.crypto.kdf.Pbkdf2.getSecretKeyFactory;
import static cc.duduhuo.util.crypto.kdf.Pbkdf2.pbkdf2;
import static cc.duduhuo.util.crypto.kdf.Pbkdf2.pbkdf2WithHmacSha1;
import static cc.duduhuo.util.crypto.kdf.Pbkdf2.pbkdf2WithHmacSha256;
import static cc.duduhuo.util.crypto.kdf.Pbkdf2.pbkdf2WithHmacSha384;
import static cc.duduhuo.util.crypto.kdf.Pbkdf2.pbkdf2WithHmacSha512;
import static cc.duduhuo.util.digest.Hex.hex;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestPbkdf2 {

    /**
     * Test vectors of RFC 6070 (PBKDF2-HMAC-SHA1).
     */
    @Test
    public void testRfc6070() {
        assertEquals("0c60c80f961f0e71f3a9b524af6012062fe037a6",
            hex(pbkdf2WithHmacSha1("password", "salt".getBytes(), 1, 160)));
        assertEquals("ea6c014dc72d6f8ccd1ed92ace1d41f0d8de8957",
            hex(pbkdf2WithHmacSha1("password", "salt".getBytes(), 2, 160)));
        assertEquals("4b007901b765489abead49d926f721d065a429c1",
            hex(pbkdf2WithHmacSha1("password", "salt".getBytes(), 4096, 160)));
        assertEquals("3d2eec4fe41c849b80c8d83662c0e44a8b291a964cf2f07038",
            hex(pbkdf2WithHmacSha1("passwordPASSWORDpassword",
                "saltSALTsaltSALTsaltSALTsaltSALTsalt".getBytes(), 4096, 200)));
        assertEquals("56fa6aa75548099dcc37d7f03425e0c3",
            hex(pbkdf2WithHmacSha1("pass\0word".toCharArray(), "sa\0lt".getBytes(), 4096, 128)));
    }

    @Test
    public void testPbkdf2WithHmacSha1() {
        assertEquals("0c60c80f961f0e71f3a9b524af6012062fe037a6",
            hex(pbkdf2("password".toCharArray(), "salt".getBytes(), 1, 160,
                Pbkdf2.Algorithm.HMAC_SHA_1)));
        assertEquals("0c60c80f961f0e71f3a9b524af6012062fe037a6",
            hex(pbkdf2("password", "salt".getBytes(), 1, 160, Pbkdf2.Algorithm.HMAC_SHA_1)));
    }

    @Test
    public void testPbkdf2WithHmacSha256() {
        assertEquals("55ac046e56e3089fec1691c22544b605f94185216dde0465e68b9d57c20dacbc"
                + "49ca9cccf179b645991664b39d77ef317c71b845b1e30bd509112041d3a19783",
            hex(pbkdf2WithHmacSha256("passwd", "salt".getBytes(), 1, 512)));
        assertEquals("f5425a9368edddad5cf28116d61d66a3a7b69d63e204f6eadbc0a38ed87e35e"
                + "6629eab836190e52bf183fea1deeb7f2ab1be3dd214d1cf15a5e887e4deada47c",
            hex(pbkdf2WithHmacSha256("passwd", "salt".getBytes(), 80000, 512)));
        // dkLen is not a multiple of the hash length
        assertEquals("632c2812e46d4604102ba7618e9d6d7d2f8128f6266b4a03264d2a0460b7dcb"
                + "388b3b1131f741bcbeb02541c8c2e97bd8bed62ab6425542e45512b7312f440"
                + "ebc6e21f4356a5edf32cf0394e0d5be940e0e930cfe21e38a3ff94e28d26c23f"
                + "ac7701ac92",
            hex(pbkdf2WithHmacSha256("password", "salt".getBytes(), 1000, 800)));
    }

    @Test
    public void testPbkdf2WithHmacSha384() {
        assertEquals("559726be38db125bc85ed7895f6e3cf574c7a01c080c3447db1e8a76764deb3c"
                + "307b94853fbe424f6488c5f4f1289626",
            hex(pbkdf2WithHmacSha384("password", "salt".getBytes(), 4096, 384)));
    }

    @Test
    public void testPbkdf2WithHmacSha512() {
        assertEquals("d197b1b33db0143e018b12f3d1d1479e6cdebdcc97c5c0f87f6902e072f457b"
                + "5143f30602641b3d55cd335988cb36b84376060ecd532e039b742a239434a"
                + "f2d5",
            hex(pbkdf2WithHmacSha512("password", "salt".getBytes(), 4096, 512)));
    }

    /**
     * The password is always encoded with UTF-8, whatever the platform default charset is.
     */
    @Test
    public void testNonAsciiPasswordAndSalt() {
        final byte[] salt = "盐值".getBytes();
        assertEquals("6db407261019ba6b08a17b5d2db212c665d529189f4bdfa3c28228ed199bce33",
            hex(pbkdf2WithHmacSha256("中文abc", salt, 4096, 256)));
        assertEquals("f0dd14e708b1fc2b07c904e80fef90f2166579ec0526dd2ac9d453a11277da1c"
                + "f2b7c77c903e9ad08a5d4129f34fef13a9591d48d264cb0356def1b389b92f7a",
            hex(pbkdf2WithHmacSha512("中文abc", "盐值".getBytes(), 4096, 512)));
    }

    @Test
    public void testDifferentPasswordsProduceDifferentKeys() {
        final byte[] salt = "salt".getBytes();
        final byte[] key1 = pbkdf2WithHmacSha256("password1", salt, 1000, 256);
        final byte[] key2 = pbkdf2WithHmacSha256("password2", salt, 1000, 256);
        assertNotEquals(hex(key1), hex(key2));
    }

    /**
     * Same arguments always produce the same key.
     */
    @Test
    public void testDeterministic() {
        final byte[] salt = "salt".getBytes();
        assertArrayEquals(pbkdf2WithHmacSha256("password", salt, 4096, 256),
            pbkdf2WithHmacSha256("password".getBytes(), salt, 4096, 256));
    }

    @Test
    public void testRandomSalt() {
        final byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        final byte[] key1 = pbkdf2("password", salt, 100_000, 256, HMAC_SHA_256);
        final byte[] key2 = pbkdf2("password", salt, 100_000, 256, HMAC_SHA_256);
        assertArrayEquals(key1, key2);
        assertEquals(32, key1.length);
        assertNotEquals(hex(key1), hex(pbkdf2("password", "salt".getBytes(), 100_000, 256, HMAC_SHA_256)));
    }

    @Test
    public void testKeyLength() {
        final byte[] salt = "salt".getBytes();
        assertEquals(16, pbkdf2WithHmacSha512("password", salt, 1000, 128).length);
        assertEquals(48, pbkdf2WithHmacSha512("password", salt, 1000, 384).length);
        assertEquals(64, pbkdf2WithHmacSha512("password", salt, 1000, 512).length);
    }

    @Test
    public void testGetSecretKeyFactory() {
        assertEquals("PBKDF2WithHmacSHA1", getSecretKeyFactory(Pbkdf2.Algorithm.HMAC_SHA_1).getAlgorithm());
        assertEquals("PBKDF2WithHmacSHA256", getSecretKeyFactory(Pbkdf2.Algorithm.HMAC_SHA_256).getAlgorithm());
        assertEquals("PBKDF2WithHmacSHA384", getSecretKeyFactory(Pbkdf2.Algorithm.HMAC_SHA_384).getAlgorithm());
        assertEquals("PBKDF2WithHmacSHA512", getSecretKeyFactory(Pbkdf2.Algorithm.HMAC_SHA_512).getAlgorithm());
        assertThrows(IllegalArgumentException.class, () -> getSecretKeyFactory("NoSuchAlgorithm"));
    }

    @Test
    public void testIllegalArguments() {
        final byte[] salt = "salt".getBytes();
        assertThrows(IllegalArgumentException.class, () -> pbkdf2((char[]) null, salt, 1000, 256, HMAC_SHA_256));
        assertThrows(IllegalArgumentException.class, () -> pbkdf2((String) null, salt, 1000, 256, HMAC_SHA_256));
        assertThrows(IllegalArgumentException.class, () -> pbkdf2((byte[]) null, salt, 1000, 256, HMAC_SHA_256));
        assertThrows(IllegalArgumentException.class, () -> pbkdf2WithHmacSha256("password", null, 1000, 256));
        assertThrows(IllegalArgumentException.class, () -> pbkdf2WithHmacSha256("password", new byte[0], 1000, 256));
        assertThrows(IllegalArgumentException.class, () -> pbkdf2WithHmacSha256("password", salt, 0, 256));
        assertThrows(IllegalArgumentException.class, () -> pbkdf2WithHmacSha256("password", salt, -1, 256));
        assertThrows(IllegalArgumentException.class, () -> pbkdf2WithHmacSha256("password", salt, 1000, 0));
        assertThrows(IllegalArgumentException.class, () -> pbkdf2WithHmacSha256("password", salt, 1000, -256));
        assertThrows(IllegalArgumentException.class, () -> pbkdf2("password", salt, 1000, 256, "NoSuchAlgorithm"));
    }
}
