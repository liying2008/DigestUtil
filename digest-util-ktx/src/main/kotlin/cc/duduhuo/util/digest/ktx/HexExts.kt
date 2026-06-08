/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.digest.ktx

import cc.duduhuo.util.digest.Hex
import java.io.File
import java.nio.file.Path

fun ByteArray.hex(upperCase: Boolean = false) = Hex.hex(this, upperCase)
fun Path.hex(upperCase: Boolean = false) = Hex.hex(this, upperCase)
fun File.hex(upperCase: Boolean = false) = Hex.hex(this, upperCase)
