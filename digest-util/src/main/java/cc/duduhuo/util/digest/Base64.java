/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.digest;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public final class Base64 {
    /**
     * Default charset is {@link StandardCharsets#UTF_8}.
     */
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    /**
     * Default values for encoder/decoder flags.
     */
    public static final int DEFAULT = 0;

    /**
     * Encoder flag bit to omit the padding '=' characters at the end
     * of the output (if any).
     */
    public static final int NO_PADDING = 1;

    /**
     * Encoder flag bit to omit all line terminators (i.e., the output
     * will be on one long line).
     */
    public static final int NO_WRAP = 2;

    /**
     * Encoder flag bit to indicate lines should be terminated with a
     * CRLF pair instead of just an LF.  Has no effect if {@code
     * NO_WRAP} is specified as well.
     */
    public static final int CRLF = 4;

    /**
     * Encoder/decoder flag bit to indicate using the "URL and
     * filename safe" variant of Base64 (see RFC 3548 section 4) where
     * {@code -} and {@code _} are used in place of {@code +} and
     * {@code /}.
     */
    public static final int URL_SAFE = 8;

    /**
     * Encodes bytes to Base64 bytes
     *
     * @param src   the byte array to encode
     * @param flags controls certain features of the encoded output.
     *              Passing {@code DEFAULT} results in output that
     *              adheres to RFC 2045.
     * @return A newly-allocated byte array containing the resulting
     * encoded bytes.
     */
    public static byte[] encode(byte[] src, int flags) {
        return cc.duduhuo.util.digest.android.util.Base64.encode(src, flags);
    }

    /**
     * Encodes bytes to Base64 bytes
     *
     * @param src the byte array to encode
     * @return A newly-allocated byte array containing the resulting
     * encoded bytes.
     */
    public static byte[] encode(byte[] src) {
        return encode(src, NO_WRAP);
    }

    /**
     * Encodes file content to Base64 bytes
     *
     * @param src   the byte array to encode
     * @param flags controls certain features of the encoded output.
     *              Passing {@code DEFAULT} results in output that
     *              adheres to RFC 2045.
     * @return A newly-allocated byte array containing the resulting
     * encoded bytes.
     * @throws IOException if an I/O error occurs.
     */
    public static byte[] encode(Path src, int flags) throws IOException {
        return encode(Files.readAllBytes(src), flags);
    }

    /**
     * Encodes file content to Base64 bytes
     *
     * @param src the byte array to encode
     * @return A newly-allocated byte array containing the resulting
     * encoded bytes.
     * @throws IOException if an I/O error occurs.
     */
    public static byte[] encode(Path src) throws IOException {
        return encode(src, NO_WRAP);
    }

    /**
     * Encodes file content to Base64 bytes
     *
     * @param src   the byte array to encode
     * @param flags controls certain features of the encoded output.
     *              Passing {@code DEFAULT} results in output that
     *              adheres to RFC 2045.
     * @return A newly-allocated byte array containing the resulting
     * encoded bytes.
     * @throws IOException if an I/O error occurs.
     */
    public static byte[] encode(File src, int flags) throws IOException {
        return encode(Files.readAllBytes(src.toPath()), flags);
    }

    /**
     * Encodes file content to Base64 bytes
     *
     * @param src the byte array to encode
     * @return A newly-allocated byte array containing the resulting
     * encoded bytes.
     * @throws IOException if an I/O error occurs.
     */
    public static byte[] encode(File src) throws IOException {
        return encode(src, NO_WRAP);
    }

    /**
     * Encodes a string to Base64 bytes
     *
     * @param src   the string to encode.
     * @param flags controls certain features of the encoded output.
     *              Passing {@code DEFAULT} results in output that
     *              adheres to RFC 2045.
     * @return A newly-allocated byte array containing the resulting
     * encoded bytes.
     */
    public static byte[] encode(String src, int flags) {
        return encode(src.getBytes(DEFAULT_CHARSET), flags);
    }

    /**
     * Encodes a string to Base64 bytes
     *
     * @param src the string to encode.
     * @return A newly-allocated byte array containing the resulting
     * encoded bytes.
     */
    public static byte[] encode(String src) {
        return encode(src, NO_WRAP);
    }

    /**
     * Encodes bytes to Base64 string
     *
     * @param src   the byte array to encode
     * @param flags controls certain features of the encoded output.
     *              Passing {@code DEFAULT} results in output that
     *              adheres to RFC 2045.
     * @return A String containing the resulting Base64 encoded characters
     */
    public static String encodeToString(byte[] src, int flags) {
        return cc.duduhuo.util.digest.android.util.Base64.encodeToString(src, flags);
    }

    /**
     * Encodes bytes to Base64 string
     *
     * @param src the byte array to encode
     * @return A String containing the resulting Base64 encoded characters
     */
    public static String encodeToString(byte[] src) {
        return encodeToString(src, NO_WRAP);
    }

    /**
     * Encodes a string to Base64 string
     *
     * @param src   the string to encode.
     * @param flags controls certain features of the encoded output.
     *              Passing {@code DEFAULT} results in output that
     *              adheres to RFC 2045.
     * @return A String containing the resulting Base64 encoded characters.
     */
    public static String encodeToString(String src, int flags) {
        return encodeToString(src.getBytes(DEFAULT_CHARSET), flags);
    }

    /**
     * Encodes a string to Base64 string
     *
     * @param src the string to encode.
     * @return A String containing the resulting Base64 encoded characters.
     */
    public static String encodeToString(String src) {
        return encodeToString(src, NO_WRAP);
    }

    /**
     * Encodes the specified File into a String using the Base64 encoding scheme.
     *
     * @param src   the file to encode.
     * @param flags controls certain features of the encoded output.
     *              Passing {@code DEFAULT} results in output that
     *              adheres to RFC 2045.
     * @return A String containing the resulting Base64 encoded characters.
     * @throws IOException if an I/O error occurs.
     */
    public static String encodeToString(File src, int flags) throws IOException {
        return encodeToString(Files.readAllBytes(src.toPath()), flags);
    }

    /**
     * Encodes the specified File into a String using the Base64 encoding scheme.
     *
     * @param src the file to encode.
     * @return A String containing the resulting Base64 encoded characters.
     * @throws IOException if an I/O error occurs.
     */
    public static String encodeToString(File src) throws IOException {
        return encodeToString(src, NO_WRAP);
    }

    /**
     * Encodes the specified File into a String using the Base64 encoding scheme.
     *
     * @param src   the file to encode.
     * @param flags controls certain features of the encoded output.
     *              Passing {@code DEFAULT} results in output that
     *              adheres to RFC 2045.
     * @return A String containing the resulting Base64 encoded characters.
     * @throws IOException if an I/O error occurs.
     */
    public static String encodeToString(Path src, int flags) throws IOException {
        return encodeToString(Files.readAllBytes(src), flags);
    }

    /**
     * Encodes the specified File into a String using the Base64 encoding scheme.
     *
     * @param src the file to encode.
     * @return A String containing the resulting Base64 encoded characters.
     * @throws IOException if an I/O error occurs.
     */
    public static String encodeToString(Path src) throws IOException {
        return encodeToString(src, NO_WRAP);
    }

    /**
     * Decodes a Base64 encoded String to a byte array
     *
     * @param src   the string to decode
     * @param flags controls certain features of the encoded output.
     *              Passing {@code DEFAULT} results in output that
     *              adheres to RFC 2045.
     * @return A newly-allocated byte array containing the decoded bytes.
     */
    public static byte[] decode(String src, int flags) {
        return cc.duduhuo.util.digest.android.util.Base64.decode(src, flags);
    }

    /**
     * Decodes a Base64 encoded String to a byte array
     *
     * @param src the string to decode
     * @return A newly-allocated byte array containing the decoded bytes.
     */
    public static byte[] decode(String src) {
        return decode(src, NO_WRAP);
    }

    /**
     * Decodes a Base64 byte array to an origin byte array
     *
     * @param src   the byte array to decode
     * @param flags controls certain features of the encoded output.
     *              Passing {@code DEFAULT} results in output that
     *              adheres to RFC 2045.
     * @return A newly-allocated byte array containing the decoded bytes.
     */
    public static byte[] decode(byte[] src, int flags) {
        return cc.duduhuo.util.digest.android.util.Base64.decode(src, flags);
    }

    /**
     * Decodes a Base64 byte array to an origin byte array
     *
     * @param src the byte array to decode
     * @return A newly-allocated byte array containing the decoded bytes.
     */
    public static byte[] decode(byte[] src) {
        return decode(src, NO_WRAP);
    }

    /**
     * Decodes a Base64 encoded bytes
     *
     * @param src     the byte array to decode
     * @param flags   controls certain features of the encoded output.
     *                Passing {@code DEFAULT} results in output that
     *                adheres to RFC 2045.
     * @param charset Converts the data from the specified array of bytes to characters using the specified character set
     * @return A String containing the resulting Base64 decoded characters.
     */
    public static String decodeToString(byte[] src, int flags, Charset charset) {
        return new String(decode(src, flags), charset);
    }

    /**
     * Decodes a Base64 encoded bytes
     *
     * @param src the byte array to decode
     * @return A String containing the resulting Base64 decoded characters.
     */
    public static String decodeToString(byte[] src) {
        return decodeToString(src, NO_WRAP, DEFAULT_CHARSET);
    }

    /**
     * Decodes a Base64 encoded String
     *
     * @param src     the string to decode
     * @param flags   controls certain features of the encoded output.
     *                Passing {@code DEFAULT} results in output that
     *                adheres to RFC 2045.
     * @param charset Converts the data from the specified array of bytes to characters using the specified character set
     * @return A String containing the resulting Base64 decoded characters.
     */
    public static String decodeToString(String src, int flags, Charset charset) {
        return new String(decode(src, flags), charset);
    }

    /**
     * Decodes a Base64 encoded String
     *
     * @param src the string to decode
     * @return A String containing the resulting Base64 decoded characters.
     */
    public static String decodeToString(String src) {
        return decodeToString(src, NO_WRAP, DEFAULT_CHARSET);
    }

    /**
     * Decodes a Base64 byte array to a file
     *
     * @param src   the byte array to decode
     * @param flags controls certain features of the encoded output.
     *              Passing {@code DEFAULT} results in output that
     *              adheres to RFC 2045.
     * @param out   the file to be written
     * @throws IOException if an I/O error occurs.
     */
    public static void decodeToFile(byte[] src, int flags, File out) throws IOException {
        byte[] data = cc.duduhuo.util.digest.android.util.Base64.decode(src, flags);
        Files.write(out.toPath(), data);
    }

    /**
     * Decodes a Base64 byte array to a file
     *
     * @param src the byte array to decode
     * @param out the file to be written
     * @throws IOException if an I/O error occurs.
     */
    public static void decodeToFile(byte[] src, File out) throws IOException {
        decodeToFile(src, NO_WRAP, out);
    }

    /**
     * Decodes a Base64 byte array to a file
     *
     * @param src   the byte array to decode
     * @param flags controls certain features of the encoded output.
     *              Passing {@code DEFAULT} results in output that
     *              adheres to RFC 2045.
     * @param out   the file to be written
     * @throws IOException if an I/O error occurs.
     */
    public static void decodeToFile(byte[] src, int flags, Path out) throws IOException {
        byte[] data = cc.duduhuo.util.digest.android.util.Base64.decode(src, flags);
        Files.write(out, data);
    }

    /**
     * Decodes a Base64 byte array to a file
     *
     * @param src the byte array to decode
     * @param out the file to be written
     * @throws IOException if an I/O error occurs.
     */
    public static void decodeToFile(byte[] src, Path out) throws IOException {
        decodeToFile(src, NO_WRAP, out);
    }

    /**
     * Decodes a Base64 encoded String to a file
     *
     * @param src   the string to decode
     * @param flags controls certain features of the encoded output.
     *              Passing {@code DEFAULT} results in output that
     *              adheres to RFC 2045.
     * @param out   the file to be written
     * @throws IOException if an I/O error occurs.
     */
    public static void decodeToFile(String src, int flags, File out) throws IOException {
        byte[] data = cc.duduhuo.util.digest.android.util.Base64.decode(src, flags);
        Files.write(out.toPath(), data);
    }

    /**
     * Decodes a Base64 encoded String to a file
     *
     * @param src the string to decode
     * @param out the file to be written
     * @throws IOException if an I/O error occurs.
     */
    public static void decodeToFile(String src, File out) throws IOException {
        decodeToFile(src, NO_WRAP, out);
    }

    /**
     * Decodes a Base64 encoded String to a file
     *
     * @param src   the string to decode
     * @param flags controls certain features of the encoded output.
     *              Passing {@code DEFAULT} results in output that
     *              adheres to RFC 2045.
     * @param out   the file to be written
     * @throws IOException if an I/O error occurs.
     */
    public static void decodeToFile(String src, int flags, Path out) throws IOException {
        byte[] data = cc.duduhuo.util.digest.android.util.Base64.decode(src, flags);
        Files.write(out, data);
    }

    /**
     * Decodes a Base64 encoded String to a file
     *
     * @param src the string to decode
     * @param out the file to be written
     * @throws IOException if an I/O error occurs.
     */
    public static void decodeToFile(String src, Path out) throws IOException {
        decodeToFile(src, NO_WRAP, out);
    }

    private Base64() {
        // don't instantiate
    }
}
