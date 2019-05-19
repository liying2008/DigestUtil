package cc.duduhuo.util.test

import cc.duduhuo.util.digest.Base64
import cc.duduhuo.util.digest.CRC32
import cc.duduhuo.util.digest.Digest
import org.junit.Test
import java.io.File

/**
 * =======================================================
 * Author: liying - liruoer2008@yeah.net
 * Datetime: 2019/4/13 12:43
 * Description:
 * Remarks:
 * =======================================================
 */
class FileTestInKotlin {
    @Test
    fun test() {
        println("============== Base64 ==============")
        println("base64 = " + Base64.encode(File("build.gradle.kts")))
        Base64.decodeToFile(Base64.encode(File("build.gradle.kts")), File("../build.gradle.kts2"))
        println("============== Digest ==============")
        val start = System.currentTimeMillis()
        println("md2 = " + Digest.md2Hex(File("build.gradle.kts"), true))
        println("md5 = " + Digest.md5Hex(File("build.gradle.kts"), true))
        println("sha1 = " + Digest.sha1Hex(File("build.gradle.kts"), true))
        println("sha224 = " + Digest.sha224Hex(File("build.gradle.kts"), true))
        println("sha256 = " + Digest.sha256Hex(File("build.gradle.kts"), true))
        println("sha384 = " + Digest.sha384Hex(File("build.gradle.kts"), true))
        println("sha512 = " + Digest.sha512Hex(File("build.gradle.kts"), true))
        val end = System.currentTimeMillis()
        println("time: " + (end - start) / 1000.0)

        println("============== CRC32 ==============")
        println("crc32 = " + CRC32.getValue(File("E:\\iso", "ubuntu-18.04-desktop-amd64.iso")))

    }
}