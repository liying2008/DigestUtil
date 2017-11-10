package cc.duduhuo.sample;

import cc.duduhuo.util.crypto.AES;
import cc.duduhuo.util.crypto.DES;
import cc.duduhuo.util.digest.Digest;

/**
 * =======================================================
 * Author: liying - liruoer2008@yeah.net
 * Datetime: 2017/11/5 22:33
 * Description: Test in Java
 * Remarks:
 * =======================================================
 */
public class TestInJava {
    public static void main(String[] args) throws Exception {
        System.out.println("============== Digest ==============");
        System.out.println("md2 = " + Digest.md2Hex("abc", true));
        System.out.println("md5 = " + Digest.md5Hex("abc", true));
        System.out.println("sha1 = " + Digest.sha1Hex("abc", true));
        System.out.println("sha224 = " + Digest.sha224Hex("abc", true));
        System.out.println("sha256 = " + Digest.sha256Hex("abc", true));
        System.out.println("sha384 = " + Digest.sha384Hex("abc", true));
        System.out.println("sha512 = " + Digest.sha512Hex("abc", true));
        System.out.println("hex = " + Digest.hex("abc", true));

        System.out.println("============== AES ==============");
        String input1 = "abc";
        String seed1 = "12345678";
        String e1 = AES.encrypt(input1, seed1);
        System.out.println("AES encrypt = " + e1);
        String d1 = AES.decrypt(e1, seed1);
        System.out.println("AES decrypt = " + d1);

        System.out.println("============== DES ==============");
        String input2 = "abc";
        String key2 = "12345678";
        String e2 = DES.encrypt(input2, key2);
        System.out.println("DES encrypt = " + e2);
        String d2 = DES.decrypt(e2, key2);
        System.out.println("DES decrypt = " + d2);
    }
}
