/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.digest.ktx

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File
import java.io.IOException

class TestHmac {
    @Test
    @Throws(IOException::class)
    fun test() {
        val start = System.currentTimeMillis()
        val data = "abc"
        val key = "this-is-a-very-secure-32byte-key".toByteArray()
        Assertions.assertEquals("36d33dad3e1013b9fec5760467d875bd", data.hmacMd5(key).hex())
        Assertions.assertEquals("98f088e5c251daef74e8a33f40157911aeccbed3", data.hmacSha1(key).hex())
        Assertions.assertEquals("c9f02d70e4a18986ad54db309510014769c1dcfd6192c34e66fd5c94", data.hmacSha224(key).hex())
        Assertions.assertEquals("de20581716e4db7a4a46b77c078fd0a3d329e02fa82045941d7d5f2f3636dd97", data.hmacSha256(key).hex())
        Assertions.assertEquals("3dd9634a8bde117e75df88b9ce918fcd3c767f15d01863f2331072a5495d0389e0de55156e749279a38cc02d4b973d41", data.hmacSha384(key).hex())
        Assertions.assertEquals("aa1d11418a3b32ad5b723df2404638f4829d7d0898cf45fe6f20ba9e048bda4a9fafda8e2b3301175e3022308181c802a9c785230233e695079d6208fa8dbc6f", data.hmacSha512(key).hex())

        val file = File("for-testing-only.txt")
        Assertions.assertEquals("8941c5954fca79b12ca3d1741ff57f75", file.hmacMd5(key).hex())
        Assertions.assertEquals("e05876763fa90276c79e9f261f66b45731cfb16d", file.hmacSha1(key).hex())
        Assertions.assertEquals("53c577c9a78d9bb59355da1735032f9b347837ee1938cd66542332a7", file.hmacSha224(key).hex())
        Assertions.assertEquals("4aeba3656218367fe5533862212bbe69dd2709edd78aea5bb2f8b38569298b01", file.hmacSha256(key).hex())
        Assertions.assertEquals("3172d5d36a581b9b0b7415701a0a9f2624e33e06fba5d01be937404c5aa09aa30459ab6bb24e5787b39567dbd157a2f7", file.hmacSha384(key).hex())
        Assertions.assertEquals("a3e21ce7fdf2e1f3fa041d078b754490cea1add53c55cdfed3573f06e68c805b1b742b98edf698713cc46d9a053355a15b37de601424b12810893d2f168826f0", file.hmacSha512(key).hex())
        val end = System.currentTimeMillis()
        println("time: " + (end - start) / 1000.0 + "s")
    }
}
