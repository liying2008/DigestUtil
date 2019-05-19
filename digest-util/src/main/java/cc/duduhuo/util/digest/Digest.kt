package cc.duduhuo.util.digest

import java.io.File
import java.io.FileInputStream
import java.security.MessageDigest

/**
 * =======================================================<br>
 * Author: liying - liruoer2008@yeah.net <br>
 * Datetime: 2017/11/5 20:07 <br>
 * Description: Java Digest Util <br>
 * Remarks: <br>
 * =======================================================<br>
 */
object Digest {
    private val DIGITS_LOWER = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')
    private val DIGITS_UPPER = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')

    private fun digest(bytes: ByteArray, algorithm: String): ByteArray {
        val messageDigest = MessageDigest.getInstance(algorithm)
        messageDigest.update(bytes)
        return messageDigest.digest()
    }

    private fun fileDigest(file: File, algorithm: String): ByteArray {
        var fileInputStream: FileInputStream? = null
        val md5 = MessageDigest.getInstance(algorithm)
        try {
            fileInputStream = file.inputStream()
            val buffer = ByteArray(8192)
            var length = fileInputStream.read(buffer)
            while (length != -1) {
                md5.update(buffer, 0, length)
                length = fileInputStream.read(buffer)
            }
            return md5.digest()
        } catch (e: Exception) {
            throw e
        } finally {
            fileInputStream?.close()
        }
    }

    /**
     * Calculates the MD2 digest and returns the value as a 16 element <code>byte[]</code>.
     * @param file File to digest
     * @return MD2 digest
     */
    @JvmStatic
    fun md2(file: File): ByteArray {
        return fileDigest(file, "MD2")
    }

    /**
     * Calculates the MD2 digest and returns the value as a 16 element <code>byte[]</code>.
     * @param data Data to digest
     * @return MD2 digest
     */
    @JvmStatic
    fun md2(data: ByteArray): ByteArray {
        return digest(data, "MD2")
    }

    /**
     * Calculates the MD5 digest and returns the value as a 16 element <code>byte[]</code>.
     *
     * @param file File to digest
     * @return MD5 digest
     */
    @JvmStatic
    fun md5(file: File): ByteArray {
        return fileDigest(file, "MD5")
    }

    /**
     * Calculates the MD5 digest and returns the value as a 16 element <code>byte[]</code>.
     *
     * @param data Data to digest
     * @return MD5 digest
     */
    @JvmStatic
    fun md5(data: ByteArray): ByteArray {
        return digest(data, "MD5")
    }

    /**
     * Calculates the SHA-1 digest and returns the value as a <code>byte[]</code>.
     *
     * @param file File to digest
     * @return SHA-1 digest
     */
    @JvmStatic
    fun sha1(file: File): ByteArray {
        return fileDigest(file, "SHA-1")
    }

    /**
     * Calculates the SHA-1 digest and returns the value as a <code>byte[]</code>.
     *
     * @param data Data to digest
     * @return SHA-1 digest
     */
    @JvmStatic
    fun sha1(data: ByteArray): ByteArray {
        return digest(data, "SHA-1")
    }

    /**
     * Calculates the SHA-224 digest and returns the value as a <code>byte[]</code>.
     *
     * @param file File to digest
     * @return SHA-224 digest
     */
    @JvmStatic
    fun sha224(file: File): ByteArray {
        return fileDigest(file, "SHA-224")
    }

    /**
     * Calculates the SHA-224 digest and returns the value as a <code>byte[]</code>.
     *
     * @param data Data to digest
     * @return SHA-224 digest
     */
    @JvmStatic
    fun sha224(data: ByteArray): ByteArray {
        return digest(data, "SHA-224")
    }

    /**
     * Calculates the SHA-256 digest and returns the value as a <code>byte[]</code>.
     *
     * @param file File to digest
     * @return SHA-256 digest
     */
    @JvmStatic
    fun sha256(file: File): ByteArray {
        return fileDigest(file, "SHA-256")
    }

    /**
     * Calculates the SHA-256 digest and returns the value as a <code>byte[]</code>.
     *
     * @param data Data to digest
     * @return SHA-256 digest
     */
    @JvmStatic
    fun sha256(data: ByteArray): ByteArray {
        return digest(data, "SHA-256")
    }

    /**
     * Calculates the SHA-384 digest and returns the value as a <code>byte[]</code>.
     *
     * @param file File to digest
     * @return SHA-384 digest
     */
    @JvmStatic
    fun sha384(file: File): ByteArray {
        return fileDigest(file, "SHA-384")
    }

    /**
     * Calculates the SHA-384 digest and returns the value as a <code>byte[]</code>.
     *
     * @param data Data to digest
     * @return SHA-384 digest
     */
    @JvmStatic
    fun sha384(data: ByteArray): ByteArray {
        return digest(data, "SHA-384")
    }

    /**
     * Calculates the SHA-512 digest and returns the value as a <code>byte[]</code>.
     *
     * @param file File to digest
     * @return SHA-512 digest
     */
    @JvmStatic
    fun sha512(file: File): ByteArray {
        return fileDigest(file, "SHA-512")
    }

    /**
     * Calculates the SHA-512 digest and returns the value as a <code>byte[]</code>.
     *
     * @param data Data to digest
     * @return SHA-512 digest
     */
    @JvmStatic
    fun sha512(data: ByteArray): ByteArray {
        return digest(data, "SHA-512")
    }

    /**
     * Reads through a file and returns the digest for the file data.
     * @return the digest as a hex string
     */
    @JvmStatic
    @JvmOverloads
    fun File.hex(upperCase: Boolean = false): String {
        return String(this.readBytes().encodeHex(upperCase))
    }

    /**
     * Reads through a byte array and returns the digest for the data.
     *
     * @param upperCase Hex string with capital letters
     * @return the digest as a hex string
     */
    @JvmStatic
    @JvmOverloads
    fun ByteArray.hex(upperCase: Boolean = false): String {
        return String(this.encodeHex(upperCase))
    }

    /**
     * Reads through a byte array and returns the digest for the data.
     *
     * @param upperCase Hex string with capital letters
     * @return the digest as a char array
     */
    @JvmStatic
    @JvmOverloads
    fun ByteArray.encodeHex(upperCase: Boolean = false): CharArray {
        val l = this.size
        val out = CharArray(l shl 1)
        // two characters form the hex value.
        var i = 0
        var j = 0
        val digitsArray = if (upperCase) DIGITS_UPPER else DIGITS_LOWER

        while (i < l) {
            out[j++] = digitsArray[(0xF0 and this[i].toInt()).ushr(4)]
            out[j++] = digitsArray[0x0F and this[i].toInt()]
            i++
        }
        return out
    }
}