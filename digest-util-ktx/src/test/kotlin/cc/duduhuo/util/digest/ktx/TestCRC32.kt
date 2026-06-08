/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.digest.ktx

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File
import java.io.IOException

class TestCRC32 {
    @Test
    @Throws(IOException::class)
    fun test() {
        Assertions.assertEquals("352441c2", "abc".crc32())
        Assertions.assertEquals("c909a417", File("for-testing-only.txt").crc32())
    }
}
