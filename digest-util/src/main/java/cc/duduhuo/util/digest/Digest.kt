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

    private fun digest(str: String, algorithm: String): ByteArray {
        val messageDigest = MessageDigest.getInstance(algorithm)
        messageDigest.update(str.toByteArray())
        return messageDigest.digest()
    }

    private fun digest(bytes: ByteArray, algorithm: String): ByteArray {
        val messageDigest = MessageDigest.getInstance(algorithm)
        messageDigest.update(bytes)
        return messageDigest.digest()
    }

    @Throws(java.lang.Exception::class)
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

    private fun digestHex(str: String, algorithm: String, upperCase: Boolean = false): String {
        val d = digest(str, algorithm)
        return hex(d, upperCase)
    }

    private fun digestHex(bytes: ByteArray, algorithm: String, upperCase: Boolean = false): String {
        val d = digest(bytes, algorithm)
        return hex(d, upperCase)
    }

    /**
     * Calculates the MD2 digest and returns the value as a 16 element <code>byte[]</code>.
     * @param data Data to digest
     * @return MD2 digest
     */
    @JvmStatic
    fun md2(data: String): ByteArray {
        return digest(data, "MD2")
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
     * Calculates the MD2 digest and returns the value as a 32 character hex string.
     * @param data Data to digest
     * @param upperCase Hex string with capital letters
     * @return MD2 digest as a hex string
     */
    @JvmStatic
    @JvmOverloads
    fun md2Hex(data: String, upperCase: Boolean = false): String {
        return digestHex(data, "MD2", upperCase)
    }

    /**
     * Calculates the MD2 digest and returns the value as a 32 character hex string.
     * @param file File to digest
     * @param upperCase Hex string with capital letters
     * @return MD2 digest as a hex string
     */
    @JvmStatic
    @JvmOverloads
    fun md2Hex(file: File, upperCase: Boolean = false): String {
        val data = fileDigest(file, "MD2")
        return hex(data, upperCase)
    }

    /**
     * Calculates the MD2 digest and returns the value as a 32 character hex string.
     * @param data Data to digest
     * @param upperCase Hex string with capital letters
     * @return MD2 digest as a hex string
     */
    @JvmStatic
    @JvmOverloads
    fun md2Hex(data: ByteArray, upperCase: Boolean = false): String {
        return digestHex(data, "MD2", upperCase)
    }

    /**
     * Calculates the MD5 digest and returns the value as a 16 element <code>byte[]</code>.
     *
     * @param data Data to digest
     * @return MD5 digest
     */
    @JvmStatic
    fun md5(data: String): ByteArray {
        return digest(data, "MD5")
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
     * Calculates the MD5 digest and returns the value as a 32 character hex string.
     *
     * @param data Data to digest
     * @param upperCase Hex string with capital letters
     * @return MD5 digest as a hex string
     */
    @JvmStatic
    @JvmOverloads
    fun md5Hex(data: String, upperCase: Boolean = false): String {
        return digestHex(data, "MD5", upperCase)
    }

    /**
     * Calculates the MD5 digest and returns the value as a 32 character hex string.
     *
     * @param file File to digest
     * @param upperCase Hex string with capital letters
     * @return MD5 digest as a hex string
     */
    @JvmStatic
    @JvmOverloads
    fun md5Hex(file: File, upperCase: Boolean = false): String {
        val data = fileDigest(file, "MD5")
        return hex(data, upperCase)
    }

    /**
     * Calculates the MD5 digest and returns the value as a 32 character hex string.
     *
     * @param data Data to digest
     * @param upperCase Hex string with capital letters
     * @return MD5 digest as a hex string
     */
    @JvmStatic
    @JvmOverloads
    fun md5Hex(data: ByteArray, upperCase: Boolean = false): String {
        return digestHex(data, "MD5", upperCase)
    }

    /**
     * Calculates the SHA-1 digest and returns the value as a <code>byte[]</code>.
     *
     * @param data Data to digest
     * @return SHA-1 digest
     */
    @JvmStatic
    fun sha1(data: String): ByteArray {
        return digest(data, "SHA-1")
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
     * Calculates the SHA-1 digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @param upperCase Hex string with capital letters
     * @return SHA-1 digest as a hex string
     */
    @JvmStatic
    @JvmOverloads
    fun sha1Hex(data: String, upperCase: Boolean = false): String {
        return digestHex(data, "SHA-1", upperCase)
    }

    /**
     * Calculates the SHA-1 digest and returns the value as a hex string.
     *
     * @param file File to digest
     * @param upperCase Hex string with capital letters
     * @return SHA-1 digest as a hex string
     */
    @JvmStatic
    @JvmOverloads
    fun sha1Hex(file: File, upperCase: Boolean = false): String {
        val data = fileDigest(file, "SHA-1")
        return hex(data, upperCase)
    }

    /**
     * Calculates the SHA-1 digest and returns the value as a hex string.
     *
     * @param data Data to digest
     * @param upperCase Hex string with capital letters
     * @return SHA-1 digest as a hex string
     */
    @JvmStatic
    @JvmOverloads
    fun sha1Hex(data: ByteArray, upperCase: Boolean = false): String {
        return digestHex(data, "SHA-1", upperCase)
    }

    /**
     * Calculates the SHA-224 digest and returns the value as a <code>byte[]</code>.
     *
     * @param data Data to digest
     * @return SHA-224 digest
     */
    @JvmStatic
    fun sha224(data: String): ByteArray {
        return digest(data, "SHA-224")
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
     * Calculates the SHA-224 digest and returns the value as a hex string.
     * @param data Data to digest
     * @param upperCase Hex string with capital letters
     * @return SHA-224 digest as a hex string
     */
    @JvmStatic
    @JvmOverloads
    fun sha224Hex(data: String, upperCase: Boolean = false): String {
        return digestHex(data, "SHA-224", upperCase)
    }

    /**
     * Calculates the SHA-224 digest and returns the value as a hex string.
     * @param file File to digest
     * @param upperCase Hex string with capital letters
     * @return SHA-224 digest as a hex string
     */
    @JvmStatic
    @JvmOverloads
    fun sha224Hex(file: File, upperCase: Boolean = false): String {
        val data = fileDigest(file, "SHA-224")
        return hex(data, upperCase)
    }

    /**
     * Calculates the SHA-224 digest and returns the value as a hex string.
     * @param data Data to digest
     * @param upperCase Hex string with capital letters
     * @return SHA-224 digest as a hex string
     */
    @JvmStatic
    @JvmOverloads
    fun sha224Hex(data: ByteArray, upperCase: Boolean = false): String {
        return digestHex(data, "SHA-224", upperCase)
    }

    /**
     * Calculates the SHA-256 digest and returns the value as a <code>byte[]</code>.
     *
     * @param data Data to digest
     * @return SHA-256 digest
     */
    @JvmStatic
    fun sha256(data: String): ByteArray {
        return digest(data, "SHA-256")
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
     * Calculates the SHA-256 digest and returns the value as a hex string.
     * @param data Data to digest
     * @param upperCase Hex string with capital letters
     * @return SHA-256 digest as a hex string
     */
    @JvmStatic
    @JvmOverloads
    fun sha256Hex(data: String, upperCase: Boolean = false): String {
        return digestHex(data, "SHA-256", upperCase)
    }

    /**
     * Calculates the SHA-256 digest and returns the value as a hex string.
     * @param file File to digest
     * @param upperCase Hex string with capital letters
     * @return SHA-256 digest as a hex string
     */
    @JvmStatic
    @JvmOverloads
    @Throws(Exception::class)
    fun sha256Hex(file: File, upperCase: Boolean = false): String {
        val data = fileDigest(file, "SHA-256")
        return hex(data, upperCase)
    }

    /**
     * Calculates the SHA-256 digest and returns the value as a hex string.
     * @param data Data to digest
     * @param upperCase Hex string with capital letters
     * @return SHA-256 digest as a hex string
     */
    @JvmStatic
    @JvmOverloads
    fun sha256Hex(data: ByteArray, upperCase: Boolean = false): String {
        return digestHex(data, "SHA-256", upperCase)
    }

    /**
     * Calculates the SHA-384 digest and returns the value as a <code>byte[]</code>.
     *
     * @param data Data to digest
     * @return SHA-384 digest
     */
    @JvmStatic
    fun sha384(data: String): ByteArray {
        return digest(data, "SHA-384")
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
     * Calculates the SHA-384 digest and returns the value as a hex string.
     * @param data Data to digest
     * @param upperCase Hex string with capital letters
     * @return SHA-384 digest as a hex string
     */
    @JvmStatic
    @JvmOverloads
    fun sha384Hex(data: String, upperCase: Boolean = false): String {
        return digestHex(data, "SHA-384", upperCase)
    }

    /**
     * Calculates the SHA-384 digest and returns the value as a hex string.
     * @param file File to digest
     * @param upperCase Hex string with capital letters
     * @return SHA-384 digest as a hex string
     */
    @JvmStatic
    @JvmOverloads
    fun sha384Hex(file: File, upperCase: Boolean = false): String {
        val data = fileDigest(file, "SHA-384")
        return hex(data, upperCase)
    }

    /**
     * Calculates the SHA-384 digest and returns the value as a hex string.
     * @param data Data to digest
     * @param upperCase Hex string with capital letters
     * @return SHA-384 digest as a hex string
     */
    @JvmStatic
    @JvmOverloads
    fun sha384Hex(data: ByteArray, upperCase: Boolean = false): String {
        return digestHex(data, "SHA-384", upperCase)
    }

    /**
     * Calculates the SHA-512 digest and returns the value as a <code>byte[]</code>.
     *
     * @param data Data to digest
     * @return SHA-512 digest
     */
    @JvmStatic
    fun sha512(data: String): ByteArray {
        return digest(data, "SHA-512")
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
     * Calculates the SHA-512 digest and returns the value as a hex string.
     * @param data Data to digest
     * @param upperCase Hex string with capital letters
     * @return SHA-512 digest as a hex string
     */
    @JvmStatic
    @JvmOverloads
    fun sha512Hex(data: String, upperCase: Boolean = false): String {
        return digestHex(data, "SHA-512", upperCase)
    }

    /**
     * Calculates the SHA-512 digest and returns the value as a hex string.
     * @param file File to digest
     * @param upperCase Hex string with capital letters
     * @return SHA-512 digest as a hex string
     */
    @JvmStatic
    @JvmOverloads
    fun sha512Hex(file: File, upperCase: Boolean = false): String {
        val data = fileDigest(file, "SHA-512")
        return hex(data, upperCase)
    }

    /**
     * Calculates the SHA-512 digest and returns the value as a hex string.
     * @param data Data to digest
     * @param upperCase Hex string with capital letters
     * @return SHA-512 digest as a hex string
     */
    @JvmStatic
    @JvmOverloads
    fun sha512Hex(data: ByteArray, upperCase: Boolean = false): String {
        return digestHex(data, "SHA-512", upperCase)
    }

    /**
     * Reads through a string and returns the digest for the data.
     * @param data Data to digest treated as UTF-8 string
     * @return the digest as a hex string
     */
    @JvmStatic
    @JvmOverloads
    fun hex(data: String, upperCase: Boolean = false): String {
        return String(encodeHex(data.toByteArray(), upperCase))
    }

    /**
     * Reads through a file and returns the digest for the file data.
     * @param file File to digest treated as UTF-8 string
     * @return the digest as a hex string
     */
    @JvmStatic
    @JvmOverloads
    fun hex(file: File, upperCase: Boolean = false): String {
        val data = file.readBytes()
        return String(encodeHex(data, upperCase))
    }

    /**
     * Reads through a byte array and returns the digest for the data.
     *
     * @param data Data to digest
     * @param upperCase Hex string with capital letters
     * @return the digest as a hex string
     */
    @JvmStatic
    @JvmOverloads
    fun hex(data: ByteArray, upperCase: Boolean = false): String {
        return String(encodeHex(data, upperCase))
    }

    /**
     * Reads through a byte array and returns the digest for the data.
     *
     * @param data Data to digest
     * @param upperCase Hex string with capital letters
     * @return the digest as a char array
     */
    @JvmStatic
    @JvmOverloads
    fun encodeHex(data: ByteArray, upperCase: Boolean = false): CharArray {
        val l = data.size
        val out = CharArray(l shl 1)
        // two characters form the hex value.
        var i = 0
        var j = 0
        val digitsArray = if (upperCase) DIGITS_UPPER else DIGITS_LOWER

        while (i < l) {
            out[j++] = digitsArray[(0xF0 and data[i].toInt()).ushr(4)]
            out[j++] = digitsArray[0x0F and data[i].toInt()]
            i++
        }
        return out
    }
}