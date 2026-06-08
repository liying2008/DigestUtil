/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.digest.ktx

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File
import java.io.IOException

class TestHex {
    @Test
    @Throws(IOException::class)
    fun test() {
        Assertions.assertEquals("616263", "abc".toByteArray().hex())
        val s = File("for-testing-only.txt").hex()
        println("hex = $s")
        Assertions.assertEquals(998, s.length)
    }
}
