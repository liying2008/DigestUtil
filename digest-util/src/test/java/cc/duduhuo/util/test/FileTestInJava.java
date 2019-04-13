package cc.duduhuo.util.test;

import cc.duduhuo.util.digest.Base64;
import cc.duduhuo.util.digest.CRC32;
import cc.duduhuo.util.digest.Digest;
import org.junit.Test;

import java.io.File;

/**
 * =======================================================
 * Author: liying - liruoer2008@yeah.net
 * Datetime: 2019/4/13 12:43
 * Description:
 * Remarks:
 * =======================================================
 */
public class FileTestInJava {
    @Test
    public void test() {
        System.out.println("============== Base64 ==============");
        System.out.println("base64 = " + Base64.encode(new File("build.gradle")));
        Base64.decodeToFile(Base64.encode(new File("build.gradle")), new File("../build.gradle2"));
        System.out.println("============== Digest ==============");
        long start = System.currentTimeMillis();
        System.out.println("md2 = " + Digest.md2Hex(new File("build.gradle"), true));
        System.out.println("md5 = " + Digest.md5Hex(new File("build.gradle"), true));
        System.out.println("sha1 = " + Digest.sha1Hex(new File("build.gradle"), true));
        System.out.println("sha224 = " + Digest.sha224Hex(new File("build.gradle"), true));
        System.out.println("sha256 = " + Digest.sha256Hex(new File("build.gradle"), true));
        System.out.println("sha384 = " + Digest.sha384Hex(new File("build.gradle"), true));
        System.out.println("sha512 = " + Digest.sha512Hex(new File("build.gradle"), true));
        long end = System.currentTimeMillis();
        System.out.println("time: " + (end - start) / 1000.0);

        System.out.println("============== CRC32 ==============");
        System.out.println("crc32 = " + CRC32.getValue(new File("E:\\iso", "ubuntu-18.04-desktop-amd64.iso")));

    }
}
