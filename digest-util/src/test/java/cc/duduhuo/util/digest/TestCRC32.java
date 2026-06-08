/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.digest;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestCRC32 {
    @Test
    public void test() throws IOException {
        assertEquals("352441c2", CRC32.getValue("abc"));
        assertEquals("c909a417", CRC32.getValue(new File("for-testing-only.txt")));

        // long start2 = System.currentTimeMillis();
        // System.out.println("calculate large file...");
        // String r = CRC32.getValue(Path.of("C:\\Users\\liying\\AppData\\Local\\Docker\\wsl\\data\\ext4.vhdx"));
        // System.out.println("result: " + r);
        // long end2 = System.currentTimeMillis();
        // System.out.println("time2: " + (end2 - start2) / 1000.0 + "s");
    }
}
