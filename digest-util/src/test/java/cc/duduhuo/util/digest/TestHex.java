/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.digest;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static cc.duduhuo.util.digest.Hex.hex;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestHex {
    @Test
    public void test() throws IOException {
        assertEquals("616263", hex("abc".getBytes()));
        String s = hex(new File("for-testing-only.txt"));
        System.out.println("hex = " + s);
        assertEquals(998, s.length());
    }
}
