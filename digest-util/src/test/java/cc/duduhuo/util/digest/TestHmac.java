/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.digest;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static cc.duduhuo.util.digest.Hmac.hmacMd5;
import static cc.duduhuo.util.digest.Hmac.hmacSha1;
import static cc.duduhuo.util.digest.Hmac.hmacSha224;
import static cc.duduhuo.util.digest.Hmac.hmacSha256;
import static cc.duduhuo.util.digest.Hmac.hmacSha384;
import static cc.duduhuo.util.digest.Hmac.hmacSha512;
import static cc.duduhuo.util.digest.Hex.hex;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestHmac {
    @Test
    public void test() throws IOException {
        long start = System.currentTimeMillis();
        final String data = "abc";
        final byte[] key = "this-is-a-very-secure-32byte-key".getBytes();
        assertEquals("36d33dad3e1013b9fec5760467d875bd", hex(hmacMd5(data, key)));
        assertEquals("98f088e5c251daef74e8a33f40157911aeccbed3", hex(hmacSha1(data, key)));
        assertEquals("c9f02d70e4a18986ad54db309510014769c1dcfd6192c34e66fd5c94", hex(hmacSha224(data, key)));
        assertEquals("de20581716e4db7a4a46b77c078fd0a3d329e02fa82045941d7d5f2f3636dd97", hex(hmacSha256(data, key)));
        assertEquals("3dd9634a8bde117e75df88b9ce918fcd3c767f15d01863f2331072a5495d0389e0de55156e749279a38cc02d4b973d41", hex(hmacSha384(data, key)));
        assertEquals("aa1d11418a3b32ad5b723df2404638f4829d7d0898cf45fe6f20ba9e048bda4a9fafda8e2b3301175e3022308181c802a9c785230233e695079d6208fa8dbc6f", hex(hmacSha512(data, key)));

        final File file = new File("for-testing-only.txt");
        assertEquals("8941c5954fca79b12ca3d1741ff57f75", hex(hmacMd5(file, key)));
        assertEquals("e05876763fa90276c79e9f261f66b45731cfb16d", hex(hmacSha1(file, key)));
        assertEquals("53c577c9a78d9bb59355da1735032f9b347837ee1938cd66542332a7", hex(hmacSha224(file, key)));
        assertEquals("4aeba3656218367fe5533862212bbe69dd2709edd78aea5bb2f8b38569298b01", hex(hmacSha256(file, key)));
        assertEquals("3172d5d36a581b9b0b7415701a0a9f2624e33e06fba5d01be937404c5aa09aa30459ab6bb24e5787b39567dbd157a2f7", hex(hmacSha384(file, key)));
        assertEquals("a3e21ce7fdf2e1f3fa041d078b754490cea1add53c55cdfed3573f06e68c805b1b742b98edf698713cc46d9a053355a15b37de601424b12810893d2f168826f0", hex(hmacSha512(file, key)));
        long end = System.currentTimeMillis();
        System.out.println("time: " + (end - start) / 1000.0 + "s");

        // long start2 = System.currentTimeMillis();
        // System.out.println("calculate large file...");
        // String r = hex(hmacSha512(Path.of("C:\\Users\\liying\\AppData\\Local\\Docker\\wsl\\data\\ext4.vhdx"), key));
        // System.out.println("result: " + r);
        // long end2 = System.currentTimeMillis();
        // System.out.println("time2: " + (end2 - start2) / 1000.0 + "s");
    }
}
