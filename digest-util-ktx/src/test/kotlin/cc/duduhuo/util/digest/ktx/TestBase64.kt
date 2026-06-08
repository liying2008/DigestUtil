/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.digest.ktx

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File
import java.io.IOException

class TestBase64 {
    @Test
    @Throws(IOException::class)
    fun test() {
        Assertions.assertEquals("YWJj", "abc".base64().encodeToString())
        Assertions.assertEquals("YWJj\n", "abc".base64().encodeToString(flags = cc.duduhuo.util.digest.Base64.DEFAULT))
        Assertions.assertEquals("YWJj", "abc".toByteArray().base64().encodeToString())
        val s = File("for-testing-only.txt").base64().encodeToString()
        println("base64 = $s")
        Assertions.assertEquals(s, s.base64().decodeToString().base64().encodeToString())
        File("for-testing-only.txt").base64().encode().base64().decodeToFile(out = File("for-testing-only.txt2"))
    }
}
