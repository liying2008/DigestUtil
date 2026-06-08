/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.digest;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestBase64 {
    @Test
    public void test() throws IOException {
        assertEquals("YWJj", Base64.encodeToString("abc"));
        assertEquals(java.util.Base64.getEncoder().encodeToString("abc".getBytes(StandardCharsets.UTF_8)), Base64.encodeToString("abc"));
        assertEquals("YWJj", Base64.encodeToString("abc".getBytes()));
        String s = Base64.encodeToString(new File("for-testing-only.txt"));
        System.out.println("base64 = " + s);
        assertEquals(s, Base64.encodeToString(Base64.decodeToString(s)));
        assertArrayEquals(java.util.Base64.getDecoder().decode("YWJj".getBytes(StandardCharsets.UTF_8)), Base64.decode("YWJj"));
        Base64.decodeToFile(Base64.encode(new File("for-testing-only.txt")), new File("for-testing-only.txt2"));
    }
}
