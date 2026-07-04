/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.digest;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static cc.duduhuo.util.digest.Digest.md2;
import static cc.duduhuo.util.digest.Digest.md5;
import static cc.duduhuo.util.digest.Digest.sha1;
import static cc.duduhuo.util.digest.Digest.sha224;
import static cc.duduhuo.util.digest.Digest.sha256;
import static cc.duduhuo.util.digest.Digest.sha384;
import static cc.duduhuo.util.digest.Digest.sha3_224;
import static cc.duduhuo.util.digest.Digest.sha3_256;
import static cc.duduhuo.util.digest.Digest.sha3_384;
import static cc.duduhuo.util.digest.Digest.sha3_512;
import static cc.duduhuo.util.digest.Digest.sha512;
import static cc.duduhuo.util.digest.Digest.sha512_224;
import static cc.duduhuo.util.digest.Digest.sha512_256;
import static cc.duduhuo.util.digest.Hex.hex;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestDigest {
    @Test
    public void test() throws IOException {
        long start = System.currentTimeMillis();
        final String data = "abc";
        assertEquals("da853b0d3f88d99b30283a69e6ded6bb", hex(md2(data)));
        assertEquals("900150983cd24fb0d6963f7d28e17f72", hex(md5(data)));
        assertEquals("a9993e364706816aba3e25717850c26c9cd0d89d", hex(sha1(data)));
        assertEquals("23097d223405d8228642a477bda255b32aadbce4bda0b3f7e36c9da7", hex(sha224(data)));
        assertEquals("ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad", hex(sha256(data)));
        assertEquals("cb00753f45a35e8bb5a03d699ac65007272c32ab0eded1631a8b605a43ff5bed8086072ba1e7cc2358baeca134c825a7", hex(sha384(data)));
        assertEquals("ddaf35a193617abacc417349ae20413112e6fa4e89a97ea20a9eeee64b55d39a2192992a274fc1a836ba3c23a3feebbd454d4423643ce80e2a9ac94fa54ca49f", hex(sha512(data)));
        assertEquals("4634270f707b6a54daae7530460842e20e37ed265ceee9a43e8924aa", hex(sha512_224(data)));
        assertEquals("53048e2681941ef99b2e29b76b4c7dabe4c2d0c634fc6d46e0e2f13107e7af23", hex(sha512_256(data)));
        assertEquals("e642824c3f8cf24ad09234ee7d3c766fc9a3a5168d0c94ad73b46fdf", hex(sha3_224(data)));
        assertEquals("3a985da74fe225b2045c172d6bd390bd855f086e3e9d525b46bfe24511431532", hex(sha3_256(data)));
        assertEquals("ec01498288516fc926459f58e2c6ad8df9b473cb0fc08c2596da7cf0e49be4b298d88cea927ac7f539f1edf228376d25", hex(sha3_384(data)));
        assertEquals("b751850b1a57168a5693cd924b6b096e08f621827444f70d884f5d0240d2712e10e116e9192af3c91a7ec57647e3934057340b4cf408d5a56592f8274eec53f0", hex(sha3_512(data)));

        final File file = new File("for-testing-only.txt");
        assertEquals("9922cac2cecf67d4748c0766a32640af", hex(md2(file)));
        assertEquals("3ec9eb0402f33a7c87737162e58e3906", hex(md5(file)));
        assertEquals("75634596101f327c1ef1a7308880d696155b2f0b", hex(sha1(file)));
        assertEquals("ed1524213d245609812e9b9d1f0578371e48d75fa728d2f3cf376efd", hex(sha224(file)));
        assertEquals("2f7001b04bccd9696177a8347e1f9201f2100d7c0e80c3870a9383cdabe105e0", hex(sha256(file)));
        assertEquals("005530946e491f4585b09473a57a4ab767e2e0d88bdb82a72278b2dbcfe5478605cd5373129af32b93f7c5f67f5fbbc3", hex(sha384(file)));
        assertEquals("1407bb7125f87f45a2a982a239a4fc8b22e6ee94841fe47b69c534d6244c9fe6214540f0d5c057cc2f94311f4402bb383e18f657cb3dc6bffbab23a00c9f2841", hex(sha512(file)));
        assertEquals("bb9c4c9547daf4459bec4bf0a18b446e62161ba171ff293797c0ef6f", hex(sha512_224(file)));
        assertEquals("cc144287f5ec4e94df316bbea19583c5e99e56a255d40a75e7dc84ba95a9c236", hex(sha512_256(file)));
        assertEquals("46b0ba383e50e514c9fa0540817e3d89fbe45224152120667ebce17b", hex(sha3_224(file)));
        assertEquals("bd6c4f85025d2d3ea5866ef01cac5f72080f64f50d33743c8c4499679b23e1e6", hex(sha3_256(file)));
        assertEquals("918445b4e6637f0bc7d508e30d9110d843c4992e5833bd69ab08fef961fe90c4aa5f8a9738408e7ff804e64314e19766", hex(sha3_384(file)));
        assertEquals("76c3303dbb9d954c9779c17cedc8a00ebc7342bde1775a79bcaf3b59e596fe59fefab2c3b2e956008ec3b394e9d9675c4de5d2af1f0c6921acedd92e0e1c9f5d", hex(sha3_512(file)));
        long end = System.currentTimeMillis();
        System.out.println("time: " + (end - start) / 1000.0 + "s");

        // long start2 = System.currentTimeMillis();
        // System.out.println("calculate large file...");
        // String r = hex(sha512(Path.of("C:\\Users\\liying\\AppData\\Local\\Docker\\wsl\\data\\ext4.vhdx")));
        // System.out.println("result: " + r);
        // long end2 = System.currentTimeMillis();
        // System.out.println("time2: " + (end2 - start2) / 1000.0 + "s");
    }
}
