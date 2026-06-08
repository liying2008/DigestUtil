/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.digest.ktx

import cc.duduhuo.util.digest.Digest
import java.io.File
import java.io.InputStream
import java.nio.charset.Charset
import java.nio.file.Path

fun ByteArray.digest(algorithm: String) = Digest.digest(this, algorithm)
fun ByteArray.md2() = Digest.md2(this)
fun ByteArray.md5() = Digest.md5(this)
fun ByteArray.sha1() = Digest.sha1(this)
fun ByteArray.sha224() = Digest.sha224(this)
fun ByteArray.sha256() = Digest.sha256(this)
fun ByteArray.sha384() = Digest.sha384(this)
fun ByteArray.sha512() = Digest.sha512(this)
fun ByteArray.sha512_224() = Digest.sha512_224(this)
fun ByteArray.sha512_256() = Digest.sha512_256(this)
fun ByteArray.sha3_224() = Digest.sha3_224(this)
fun ByteArray.sha3_256() = Digest.sha3_256(this)
fun ByteArray.sha3_384() = Digest.sha3_384(this)
fun ByteArray.sha3_512() = Digest.sha3_512(this)

fun String.digest(algorithm: String, charset: Charset = Charsets.UTF_8) = Digest.digest(this, algorithm, charset)
fun String.md2() = Digest.md2(this)
fun String.md5() = Digest.md5(this)
fun String.sha1() = Digest.sha1(this)
fun String.sha224() = Digest.sha224(this)
fun String.sha256() = Digest.sha256(this)
fun String.sha384() = Digest.sha384(this)
fun String.sha512() = Digest.sha512(this)
fun String.sha512_224() = Digest.sha512_224(this)
fun String.sha512_256() = Digest.sha512_256(this)
fun String.sha3_224() = Digest.sha3_224(this)
fun String.sha3_256() = Digest.sha3_256(this)
fun String.sha3_384() = Digest.sha3_384(this)
fun String.sha3_512() = Digest.sha3_512(this)

fun File.digest(algorithm: String) = Digest.digest(this, algorithm)
fun File.md2() = Digest.md2(this)
fun File.md5() = Digest.md5(this)
fun File.sha1() = Digest.sha1(this)
fun File.sha224() = Digest.sha224(this)
fun File.sha256() = Digest.sha256(this)
fun File.sha384() = Digest.sha384(this)
fun File.sha512() = Digest.sha512(this)
fun File.sha512_224() = Digest.sha512_224(this)
fun File.sha512_256() = Digest.sha512_256(this)
fun File.sha3_224() = Digest.sha3_224(this)
fun File.sha3_256() = Digest.sha3_256(this)
fun File.sha3_384() = Digest.sha3_384(this)
fun File.sha3_512() = Digest.sha3_512(this)

fun Path.digest(algorithm: String) = Digest.digest(this, algorithm)
fun Path.md2() = Digest.md2(this)
fun Path.md5() = Digest.md5(this)
fun Path.sha1() = Digest.sha1(this)
fun Path.sha224() = Digest.sha224(this)
fun Path.sha256() = Digest.sha256(this)
fun Path.sha384() = Digest.sha384(this)
fun Path.sha512() = Digest.sha512(this)
fun Path.sha512_224() = Digest.sha512_224(this)
fun Path.sha512_256() = Digest.sha512_256(this)
fun Path.sha3_224() = Digest.sha3_224(this)
fun Path.sha3_256() = Digest.sha3_256(this)
fun Path.sha3_384() = Digest.sha3_384(this)
fun Path.sha3_512() = Digest.sha3_512(this)

fun InputStream.digest(algorithm: String) = Digest.digest(this, algorithm)
fun InputStream.md2() = Digest.md2(this)
fun InputStream.md5() = Digest.md5(this)
fun InputStream.sha1() = Digest.sha1(this)
fun InputStream.sha224() = Digest.sha224(this)
fun InputStream.sha256() = Digest.sha256(this)
fun InputStream.sha384() = Digest.sha384(this)
fun InputStream.sha512() = Digest.sha512(this)
fun InputStream.sha512_224() = Digest.sha512_224(this)
fun InputStream.sha512_256() = Digest.sha512_256(this)
fun InputStream.sha3_224() = Digest.sha3_224(this)
fun InputStream.sha3_256() = Digest.sha3_256(this)
fun InputStream.sha3_384() = Digest.sha3_384(this)
fun InputStream.sha3_512() = Digest.sha3_512(this)
