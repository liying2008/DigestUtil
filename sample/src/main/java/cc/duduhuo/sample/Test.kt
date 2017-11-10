package cc.duduhuo.sample

import cc.duduhuo.util.crypto.AES
import cc.duduhuo.util.crypto.DES
import cc.duduhuo.util.digest.Digest

/**
 * =======================================================
 * Author: liying - liruoer2008@yeah.net
 * Datetime: 2017/11/5 20:14
 * Description: Test in Kotlin
 * Remarks:
 * =======================================================
 */
fun main(args: Array<String>) {
    println("============== Digest ==============")
    println("md2 = " + Digest.md2Hex("abc", true))
    println("md5 = " + Digest.md5Hex("abc", true))
    println("sha1 = " + Digest.sha1Hex("abc", true))
    println("sha224 = " + Digest.sha224Hex("abc", true))
    println("sha256 = " + Digest.sha256Hex("abc", true))
    println("sha384 = " + Digest.sha384Hex("abc", true))
    println("sha512 = " + Digest.sha512Hex("abc", true))
    println("hex = " + Digest.hex("abc", true))

    println("============== AES ==============")
    val input1 = "abc"
    val seed1 = "12345678"
    val e1 = AES.encrypt(input1, seed1)
    println("AES encrypt = $e1")
    val d1 = AES.decrypt(e1, seed1)
    println("AES decrypt = $d1")

    println("============== DES ==============")
    val input2 = "abc"
    val key2 = "12345678"
    val e2 = DES.encrypt(input2, key2)
    println("DES encrypt = $e2")
    val d2 = DES.decrypt(e2, key2)
    println("DES decrypt = $d2")

}