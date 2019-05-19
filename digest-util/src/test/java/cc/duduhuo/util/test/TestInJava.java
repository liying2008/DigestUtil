package cc.duduhuo.util.test;

import cc.duduhuo.util.digest.Base64;
import cc.duduhuo.util.digest.CRC32;
import org.junit.Test;

import static cc.duduhuo.util.digest.Digest.*;
import static org.junit.Assert.assertEquals;

/**
 * =======================================================
 * Author: liying - liruoer2008@yeah.net
 * Datetime: 2019/4/13 12:40
 * Description:
 * Remarks:
 * =======================================================
 */
public class TestInJava {
    @Test
    public void test() {
        // ============== Base64 ==============
        assertEquals("YWJj\n", Base64.encode("abc"));

        // ============== Digest ==============
        assertEquals("da853b0d3f88d99b30283a69e6ded6bb", hex(md2("abc".getBytes())));
        assertEquals("900150983cd24fb0d6963f7d28e17f72", hex(md5("abc".getBytes())));
        assertEquals("a9993e364706816aba3e25717850c26c9cd0d89d", hex(sha1("abc".getBytes())));
        assertEquals("23097d223405d8228642a477bda255b32aadbce4bda0b3f7e36c9da7", hex(sha224("abc".getBytes())));
        assertEquals("ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad", hex(sha256("abc".getBytes())));
        assertEquals("cb00753f45a35e8bb5a03d699ac65007272c32ab0eded1631a8b605a43ff5bed8086072ba1e7cc2358baeca134c825a7", hex(sha384("abc".getBytes())));
        assertEquals("ddaf35a193617abacc417349ae20413112e6fa4e89a97ea20a9eeee64b55d39a2192992a274fc1a836ba3c23a3feebbd454d4423643ce80e2a9ac94fa54ca49f", hex(sha512("abc".getBytes())));
        assertEquals("616263", hex("abc".getBytes()));

        // ============== CRC32 ==============
        assertEquals("352441c2", CRC32.getValue("abc"));
    }
}
