package cc.duduhuo.util.digest

import java.nio.charset.Charset

/**
 * =======================================================<br>
 * Author: liying - liruoer2008@yeah.net <br>
 * Datetime: 2017/11/5 22:10 <br>
 * Description: Base64 Utils <br>
 * Remarks: <br>
 * =======================================================<br>
 */
object Base64 {
    /**
     * Encodes the specified byte array into a String using the {@link Base64}
     * encoding scheme.
     *
     * <p> This method first encodes all input bytes into a base64 encoded
     * byte array and then constructs a new String by using the encoded byte
     * array and the {@link java.nio.charset.StandardCharsets#ISO_8859_1
     * ISO-8859-1} charset.
     *
     * <p> In other words, an invocation of this method has exactly the same
     * effect as invoking
     * {@code new String(encode(src), StandardCharsets.ISO_8859_1)}.
     *
     * @param   src the byte array to encode
     * @return  A String containing the resulting Base64 encoded characters
     */
    @JvmStatic
    fun encode(src: ByteArray): String {
        return java.util.Base64.getEncoder().encodeToString(src)
    }

    /**
     * Encodes all bytes from the specified byte array into a newly-allocated
     * byte array using the {@link Base64} encoding scheme. The returned byte
     * array is of the length of the resulting bytes.
     *
     * @param   src the byte array to encode
     * @return  A newly-allocated byte array containing the resulting
     *          encoded bytes.
     */
    @JvmStatic
    fun encodeByteArray(src: ByteArray): ByteArray {
        return java.util.Base64.getEncoder().encode(src)
    }

    /**
     * Decodes a Base64 encoded String into a newly-allocated byte array
     * using the {@link Base64} encoding scheme.
     *
     * <p> An invocation of this method has exactly the same effect as invoking
     * {@code decode(src.getBytes(StandardCharsets.ISO_8859_1))}
     *
     * @param   src the string to decode
     *
     * @return  A newly-allocated byte array containing the decoded bytes.
     *
     * @throws  IllegalArgumentException
     *          if {@code src} is not in valid Base64 scheme
     */
    @JvmStatic
    fun decode(src: String): ByteArray {
        return java.util.Base64.getDecoder().decode(src)
    }

    /**
     * Decodes all bytes from the input byte array using the {@link Base64}
     * encoding scheme, writing the results into a newly-allocated output
     * byte array. The returned byte array is of the length of the resulting
     * bytes.
     *
     * @param   src the byte array to decode
     * @return  A newly-allocated byte array containing the decoded bytes.
     *
     * @throws  IllegalArgumentException
     *          if {@code src} is not in valid Base64 scheme
     */
    @JvmStatic
    fun decode(src: ByteArray): ByteArray {
        return java.util.Base64.getDecoder().decode(src)
    }

    /**
     * Encodes the specified String into a String using the {@link Base64}
     * encoding scheme.
     * @param src the string to encode.
     * @param charset Encodes this string using the specified character set.
     * @return  A String containing the resulting Base64 encoded characters.
     */
    @JvmStatic
    @JvmOverloads
    fun encode(src: String, charset: Charset = Charsets.UTF_8): String {
        return encode(src.toByteArray(charset))
    }

    /**
     * Decodes a Base64 encoded String
     * using the {@link Base64} encoding scheme.
     ** @param   src the string to decode
     * @param charset Converts the data from the specified array of bytes to characters using the specified character set
     *
     * @return  A String containing the resulting Base64 decoded characters.
     *
     * @throws  IllegalArgumentException
     *          if {@code src} is not in valid Base64 scheme
     */
    @JvmStatic
    @JvmOverloads
    fun decode(src: String, charset: Charset = Charsets.UTF_8): String {
        return String(decode(src), charset)
    }
}
