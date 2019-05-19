package cc.duduhuo.sample

import cc.duduhuo.util.digest.Base64
import cc.duduhuo.util.digest.CRC32
import cc.duduhuo.util.digest.Digest
import cc.duduhuo.util.digest.Digest.hex
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
    println("md2 = " + Digest.md2("abc".toByteArray()).hex())
    println("md5 = " + Digest.md5("abc".toByteArray()).hex())
    println("sha1 = " + Digest.sha1("abc".toByteArray()).hex())
    println("sha224 = " + Digest.sha224("abc".toByteArray()).hex())
    println("sha256 = " + Digest.sha256("abc".toByteArray()).hex())
    println("sha384 = " + Digest.sha384("abc".toByteArray()).hex())
    println("sha512 = " + Digest.sha512("abc".toByteArray()).hex())
    // File digest
    println("sha256 = " + Digest.sha256(File("build.gradle.kts")).hex())

    println("============== CRC32 ==============")
    println("crc32 = " + CRC32.getValue("abc"))
    println("crc32 = " + CRC32.getValue(File("build.gradle.kts")))
}