/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.digest.ktx

import cc.duduhuo.util.digest.CRC32
import java.io.File
import java.io.InputStream
import java.nio.file.Path


fun ByteArray.crc32() = CRC32.getValue(this)

fun String.crc32() = CRC32.getValue(this)

fun File.crc32() = CRC32.getValue(this)

fun Path.crc32() = CRC32.getValue(this)

fun InputStream.crc32() = CRC32.getValue(this)
