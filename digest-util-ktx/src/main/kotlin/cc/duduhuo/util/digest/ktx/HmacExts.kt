/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.digest.ktx

import cc.duduhuo.util.digest.Hmac
import java.io.File
import java.io.InputStream
import java.nio.charset.Charset
import java.nio.file.Path

fun ByteArray.hmac(algorithm: String, key: ByteArray) = Hmac.hmac(this, algorithm, key)
fun ByteArray.hmacMd5(key: ByteArray) = Hmac.hmacMd5(this, key)
fun ByteArray.hmacSha1(key: ByteArray) = Hmac.hmacSha1(this, key)
fun ByteArray.hmacSha224(key: ByteArray) = Hmac.hmacSha224(this, key)
fun ByteArray.hmacSha256(key: ByteArray) = Hmac.hmacSha256(this, key)
fun ByteArray.hmacSha384(key: ByteArray) = Hmac.hmacSha384(this, key)
fun ByteArray.hmacSha512(key: ByteArray) = Hmac.hmacSha512(this, key)

fun String.hmac(algorithm: String, key: ByteArray, charset: Charset = Charsets.UTF_8) = Hmac.hmac(this, algorithm, key, charset)
fun String.hmacMd5(key: ByteArray) = Hmac.hmacMd5(this, key)
fun String.hmacSha1(key: ByteArray) = Hmac.hmacSha1(this, key)
fun String.hmacSha224(key: ByteArray) = Hmac.hmacSha224(this, key)
fun String.hmacSha256(key: ByteArray) = Hmac.hmacSha256(this, key)
fun String.hmacSha384(key: ByteArray) = Hmac.hmacSha384(this, key)
fun String.hmacSha512(key: ByteArray) = Hmac.hmacSha512(this, key)

fun File.hmac(algorithm: String, key: ByteArray) = Hmac.hmac(this, algorithm, key)
fun File.hmacMd5(key: ByteArray) = Hmac.hmacMd5(this, key)
fun File.hmacSha1(key: ByteArray) = Hmac.hmacSha1(this, key)
fun File.hmacSha224(key: ByteArray) = Hmac.hmacSha224(this, key)
fun File.hmacSha256(key: ByteArray) = Hmac.hmacSha256(this, key)
fun File.hmacSha384(key: ByteArray) = Hmac.hmacSha384(this, key)
fun File.hmacSha512(key: ByteArray) = Hmac.hmacSha512(this, key)

fun Path.hmac(algorithm: String, key: ByteArray) = Hmac.hmac(this, algorithm, key)
fun Path.hmacMd5(key: ByteArray) = Hmac.hmacMd5(this, key)
fun Path.hmacSha1(key: ByteArray) = Hmac.hmacSha1(this, key)
fun Path.hmacSha224(key: ByteArray) = Hmac.hmacSha224(this, key)
fun Path.hmacSha256(key: ByteArray) = Hmac.hmacSha256(this, key)
fun Path.hmacSha384(key: ByteArray) = Hmac.hmacSha384(this, key)
fun Path.hmacSha512(key: ByteArray) = Hmac.hmacSha512(this, key)

fun InputStream.hmac(algorithm: String, key: ByteArray) = Hmac.hmac(this, algorithm, key)
fun InputStream.hmacMd5(key: ByteArray) = Hmac.hmacMd5(this, key)
fun InputStream.hmacSha1(key: ByteArray) = Hmac.hmacSha1(this, key)
fun InputStream.hmacSha224(key: ByteArray) = Hmac.hmacSha224(this, key)
fun InputStream.hmacSha256(key: ByteArray) = Hmac.hmacSha256(this, key)
fun InputStream.hmacSha384(key: ByteArray) = Hmac.hmacSha384(this, key)
fun InputStream.hmacSha512(key: ByteArray) = Hmac.hmacSha512(this, key)
