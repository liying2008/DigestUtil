package cc.duduhuo.sample;

import cc.duduhuo.util.digest.Base64;
import cc.duduhuo.util.digest.CRC32;
import cc.duduhuo.util.digest.Digest;

import java.io.File;

/**
 * =======================================================
 * Author: liying - liruoer2008@yeah.net
 * Datetime: 2017/11/5 22:33
 * Description: Test in Java
 * Remarks:
 * =======================================================
 */
public class TestInJava {
    public static void main(String[] args) {
        System.out.println("============== Base64 ==============");
        System.out.println("base64 = " + Base64.encode("abc"));

        System.out.println("============== Digest ==============");
        System.out.println("md2 = " + Digest.md2Hex("abc", true));
        System.out.println("md5 = " + Digest.md5Hex("abc", true));
        System.out.println("sha1 = " + Digest.sha1Hex("abc", true));
        System.out.println("sha224 = " + Digest.sha224Hex("abc", true));
        System.out.println("sha256 = " + Digest.sha256Hex("abc", true));
        System.out.println("sha384 = " + Digest.sha384Hex("abc", true));
        System.out.println("sha512 = " + Digest.sha512Hex("abc", true));
        // File digest
        System.out.println("sha256 = " + Digest.sha256Hex(new File("build.gradle.kts"), true));

        System.out.println("============== CRC32 ==============");
        System.out.println("crc32 = " + CRC32.getValue("abc"));
        System.out.println("crc32 = " + CRC32.getValue(new File("build.gradle.kts")));
    }
}
