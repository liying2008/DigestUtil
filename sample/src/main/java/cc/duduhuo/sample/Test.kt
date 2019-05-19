package cc.duduhuo.sample

import cc.duduhuo.util.digest.Base64
import cc.duduhuo.util.digest.CRC32
import cc.duduhuo.util.digest.Digest
import java.io.File

/**
 * =======================================================
 * Author: liying - liruoer2008@yeah.net
 * Datetime: 2017/11/5 20:14
 * Description: Test in Kotlin
 * Remarks:
 * =======================================================
 */
fun main(args: Array<String>) {
    println("============== Base64 ==============")
    println("base64 = " + Base64.encode("abc"))

    println("============== Digest ==============")
    println("md2 = " + Digest.md2Hex("abc", true))
    println("md5 = " + Digest.md5Hex("abc", true))
    println("sha1 = " + Digest.sha1Hex("abc", true))
    println("sha224 = " + Digest.sha224Hex("abc", true))
    println("sha256 = " + Digest.sha256Hex("abc", true))
    println("sha384 = " + Digest.sha384Hex("abc", true))
    println("sha512 = " + Digest.sha512Hex("abc", true))
    // File digest
    println("sha256 = " + Digest.sha256Hex(File("build.gradle.kts"), true))

    println("============== CRC32 ==============")
    println("crc32 = " + CRC32.getValue("abc"))
    println("crc32 = " + CRC32.getValue(File("build.gradle.kts")))
}