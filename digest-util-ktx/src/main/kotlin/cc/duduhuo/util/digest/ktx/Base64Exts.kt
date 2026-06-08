/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.digest.ktx

import cc.duduhuo.util.digest.Base64
import java.io.File
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Path

class Base64(private val src: ByteArray) {
    /**
     * Encodes the specified byte array to Base64 and returns a new byte array.
     */
    fun encode(flags: Int = Base64.NO_WRAP) = Base64.encode(src, flags)

    /**
     * Encodes the specified byte array to Base64 and returns a new String.
     */
    fun encodeToString(flags: Int = Base64.NO_WRAP) = Base64.encodeToString(src, flags)

    /**
     * Decodes a Base64 byte array and returns a new byte array.
     */
    fun decode(flags: Int = Base64.NO_WRAP) = Base64.decode(src, flags)

    /**
     * Decodes a Base64 byte array and returns a new String.
     */
    fun decodeToString(flags: Int = Base64.NO_WRAP, charset: Charset = Charsets.UTF_8) = Base64.decodeToString(src, flags, charset)

    /**
     * Decodes a Base64 byte array to a file
     */
    fun decodeToFile(flags: Int = Base64.NO_WRAP, out: File) = Base64.decodeToFile(src, flags, out)

    /**
     * Decodes a Base64 byte array to a file
     */
    fun decodeToFile(flags: Int = Base64.NO_WRAP, out: Path) = Base64.decodeToFile(src, flags, out)
}

fun ByteArray.base64() = Base64(this)

fun String.base64() = Base64(this.toByteArray())

fun Path.base64() = Base64(Files.readAllBytes(this))

fun File.base64() = Base64(Files.readAllBytes(this.toPath()))
