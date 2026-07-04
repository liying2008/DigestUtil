/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.sample

import cc.duduhuo.util.digest.ktx.base64
import cc.duduhuo.util.digest.ktx.crc32
import cc.duduhuo.util.digest.ktx.hex
import cc.duduhuo.util.digest.ktx.hmacMd5
import cc.duduhuo.util.digest.ktx.hmacSha1
import cc.duduhuo.util.digest.ktx.hmacSha224
import cc.duduhuo.util.digest.ktx.hmacSha256
import cc.duduhuo.util.digest.ktx.hmacSha384
import cc.duduhuo.util.digest.ktx.hmacSha512
import cc.duduhuo.util.digest.ktx.md2
import cc.duduhuo.util.digest.ktx.md5
import cc.duduhuo.util.digest.ktx.sha1
import cc.duduhuo.util.digest.ktx.sha224
import cc.duduhuo.util.digest.ktx.sha256
import cc.duduhuo.util.digest.ktx.sha384
import cc.duduhuo.util.digest.ktx.sha3_224
import cc.duduhuo.util.digest.ktx.sha3_256
import cc.duduhuo.util.digest.ktx.sha3_384
import cc.duduhuo.util.digest.ktx.sha3_512
import cc.duduhuo.util.digest.ktx.sha512
import cc.duduhuo.util.digest.ktx.sha512_224
import cc.duduhuo.util.digest.ktx.sha512_256
import java.io.File

fun main() {
    println("============== Base64 ==============")
    println("[Base64] encode = " + "abc".base64().encodeToString())
    println("[Base64] decode = " + "YWJj".base64().decodeToString())
    println("[Base64] encode = " + File("build.gradle.kts").base64().encodeToString())

    println("============== Digest ==============")
    println("[Digest] md2 = " + "abc".md2().hex())
    println("[Digest] md5 = " + "abc".md5().hex())
    println("[Digest] sha1 = " + "abc".sha1().hex())
    println("[Digest] sha224 = " + "abc".sha224().hex())
    println("[Digest] sha256 = " + "abc".sha256().hex())
    println("[Digest] sha384 = " + "abc".sha384().hex())
    println("[Digest] sha512 = " + "abc".sha512().hex())
    println("[Digest] sha512_224 = " + "abc".sha512_224().hex())
    println("[Digest] sha512_256 = " + "abc".sha512_256().hex())
    println("[Digest] sha3_224 = " + "abc".sha3_224().hex())
    println("[Digest] sha3_256 = " + "abc".sha3_256().hex())
    println("[Digest] sha3_384 = " + "abc".sha3_384().hex())
    println("[Digest] sha3_512 = " + "abc".sha3_512().hex())
    // File digest
    println("[Digest] sha256 = " + File("build.gradle.kts").sha256().hex())

    println("============== Hmac ==============")
    val key = "a key".toByteArray()
    println("[Hmac] hmacMd5 = " + "abc".hmacMd5(key).hex())
    println("[Hmac] hmacSha1 = " + "abc".hmacSha1(key).hex())
    println("[Hmac] hmacSha224 = " + "abc".hmacSha224(key).hex())
    println("[Hmac] hmacSha256 = " + "abc".hmacSha256(key).hex())
    println("[Hmac] hmacSha384 = " + "abc".hmacSha384(key).hex())
    println("[Hmac] hmacSha512 = " + "abc".hmacSha512(key).hex())
    // File digest
    println("[Hmac] hmacSha256 = " + File("build.gradle.kts").hmacSha256(key).hex())

    println("============== CRC32 ==============")
    println("[CRC32] value = " + "abc".crc32())
    println("[CRC32] value = " + File("build.gradle.kts").crc32())
}
