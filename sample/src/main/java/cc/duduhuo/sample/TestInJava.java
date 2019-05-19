package cc.duduhuo.sample;

import cc.duduhuo.util.digest.Base64;
import cc.duduhuo.util.digest.CRC32;

import java.io.File;

import static cc.duduhuo.util.digest.Digest.*;

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
        System.out.println("md2 = " + hex(md2("abc".getBytes())));
        System.out.println("md5 = " + hex(md5("abc".getBytes())));
        System.out.println("sha1 = " + hex(sha1("abc".getBytes())));
        System.out.println("sha224 = " + hex(sha224("abc".getBytes())));
        System.out.println("sha256 = " + hex(sha256("abc".getBytes())));
        System.out.println("sha384 = " + hex(sha384("abc".getBytes())));
        System.out.println("sha512 = " + hex(sha512("abc".getBytes())));
        // File digest
        System.out.println("sha256 = " + hex(sha256(new File("build.gradle.kts"))));

        System.out.println("============== CRC32 ==============");
        System.out.println("crc32 = " + CRC32.getValue("abc"));
        System.out.println("crc32 = " + CRC32.getValue(new File("build.gradle.kts")));
    }
}
