package cc.duduhuo.util.digest

import java.io.File
import java.io.FileInputStream
import java.nio.charset.Charset
import java.util.zip.CRC32

/**
 * =======================================================<br>
 * Author: liying - liruoer2008@yeah.net<br>
 * Datetime: 2018/11/24 21:48<br>
 * Description: A class that can be used to compute the CRC-32 of a data stream.<br>
 * Remarks: <br>
 * =======================================================<br>
 */
object CRC32 {
    private fun fileCRC32(file: File, radix: Int = 16): String {
        var fileInputStream: FileInputStream? = null
        val crc32 = CRC32()
        try {
            fileInputStream = file.inputStream()
            val buffer = ByteArray(8192)
            var length = fileInputStream.read(buffer)
            while (length != -1) {
                crc32.update(buffer, 0, length)
                length = fileInputStream.read(buffer)
            }
            return crc32.value.toString(radix)
        } catch (e: Exception) {
            throw e
        } finally {
            fileInputStream?.close()
        }
    }

    /**
     * Get CRC-32 value
     * @param data Data to calculate the CRC-32 value
     * @param charset Converts the data from the specified array of bytes to characters using the specified character set
     * @param radix
     * @return CRC-32 value
     */
    @JvmStatic
    @JvmOverloads
    fun getValue(data: String, charset: Charset = Charsets.UTF_8, radix: Int = 16): String {
        val crc32 = CRC32()
        crc32.update(data.toByteArray(charset))
        return crc32.value.toString(radix)
    }

    /**
     * Get CRC-32 value
     * @param file file to calculate the CRC-32 value
     * @param radix
     * @return CRC-32 value
     */
    @JvmStatic
    @JvmOverloads
    fun getValue(file: File, radix: Int = 16): String {
        return fileCRC32(file, radix)
    }
}