package cc.duduhuo.util.test;

import cc.duduhuo.util.digest.Base64;
import cc.duduhuo.util.digest.CRC32;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static cc.duduhuo.util.digest.Digest.*;
import static org.junit.Assert.assertEquals;

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
    public void test() throws FileNotFoundException {
        System.out.println("============== Base64 ==============");
        System.out.println("base64 = " + Base64.encode(new File("for-testing-only.txt")));
        Base64.decodeToFile(Base64.encode(new File("for-testing-only.txt")), new File("for-testing-only.txt2"));
        System.out.println("============== Digest ==============");
        long start = System.currentTimeMillis();
        assertEquals("9922cac2cecf67d4748c0766a32640af", hex(md2(new FileInputStream(new File("for-testing-only.txt")))));
        assertEquals("3ec9eb0402f33a7c87737162e58e3906", hex(md5(new FileInputStream(new File("for-testing-only.txt")))));
        assertEquals("75634596101f327c1ef1a7308880d696155b2f0b", hex(sha1(new FileInputStream(new File("for-testing-only.txt")))));
        assertEquals("ed1524213d245609812e9b9d1f0578371e48d75fa728d2f3cf376efd", hex(sha224(new FileInputStream(new File("for-testing-only.txt")))));
        assertEquals("2f7001b04bccd9696177a8347e1f9201f2100d7c0e80c3870a9383cdabe105e0", hex(sha256(new FileInputStream(new File("for-testing-only.txt")))));
        assertEquals("005530946e491f4585b09473a57a4ab767e2e0d88bdb82a72278b2dbcfe5478605cd5373129af32b93f7c5f67f5fbbc3", hex(sha384(new FileInputStream(new File("for-testing-only.txt")))));
        assertEquals("1407bb7125f87f45a2a982a239a4fc8b22e6ee94841fe47b69c534d6244c9fe6214540f0d5c057cc2f94311f4402bb383e18f657cb3dc6bffbab23a00c9f2841", hex(sha512(new FileInputStream(new File("for-testing-only.txt")))));
        long end = System.currentTimeMillis();
        System.out.println("time: " + (end - start) / 1000.0);

        System.out.println("============== CRC32 ==============");
        assertEquals("83c52d9b", CRC32.getValue(new FileInputStream(new File("E:\\iso", "ubuntu-18.04-desktop-amd64.iso"))));

    }
}
