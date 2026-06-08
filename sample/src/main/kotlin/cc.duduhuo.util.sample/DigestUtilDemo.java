/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.sample;

import cc.duduhuo.util.digest.Base64;
import cc.duduhuo.util.digest.CRC32;
import cc.duduhuo.util.digest.Digest;
import cc.duduhuo.util.digest.Hex;

import java.io.File;
import java.io.IOException;

public class DigestUtilDemo {
    public static void main(String[] args) throws IOException {
        System.out.println("============== Base64 ==============");
        System.out.println("[Base64] encode = " + Base64.encodeToString("abc"));
        System.out.println("[Base64] decode = " + Base64.decodeToString("YWJj"));
        System.out.println("[Base64] encode = " + Base64.encodeToString(new File("build.gradle.kts")));

        System.out.println("============== Digest ==============");
        System.out.println("[Digest] md2 = " + Hex.hex(Digest.md2("abc")));
        System.out.println("[Digest] md5 = " + Hex.hex(Digest.md5("abc")));
        System.out.println("[Digest] sha1 = " + Hex.hex(Digest.sha1("abc")));
        System.out.println("[Digest] sha224 = " + Hex.hex(Digest.sha224("abc")));
        System.out.println("[Digest] sha256 = " + Hex.hex(Digest.sha256("abc")));
        System.out.println("[Digest] sha384 = " + Hex.hex(Digest.sha384("abc")));
        System.out.println("[Digest] sha512 = " + Hex.hex(Digest.sha512("abc")));
        System.out.println("[Digest] sha512_224 = " + Hex.hex(Digest.sha512_224("abc")));
        System.out.println("[Digest] sha512_256 = " + Hex.hex(Digest.sha512_256("abc")));
        System.out.println("[Digest] sha3_224 = " + Hex.hex(Digest.sha3_224("abc")));
        System.out.println("[Digest] sha3_256 = " + Hex.hex(Digest.sha3_256("abc")));
        System.out.println("[Digest] sha3_384 = " + Hex.hex(Digest.sha3_384("abc")));
        System.out.println("[Digest] sha3_512 = " + Hex.hex(Digest.sha3_512("abc")));
        // File digest
        System.out.println("[Digest] sha256 = " + Hex.hex(Digest.sha256(new File("build.gradle.kts"))));

        System.out.println("============== CRC32 ==============");
        System.out.println("[CRC32] value = " + CRC32.getValue("abc"));
        System.out.println("[CRC32] value = " + CRC32.getValue(new File("build.gradle.kts")));
    }
}
