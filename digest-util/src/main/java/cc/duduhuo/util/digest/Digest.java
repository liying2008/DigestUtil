/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.digest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Digest {

    /**
     * Default charset is {@link StandardCharsets#UTF_8}.
     */
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    public static final class Algorithm {
        public static final String MD2 = "MD2";
        public static final String MD5 = "MD5";
        public static final String SHA_1 = "SHA-1";
        public static final String SHA_224 = "SHA-224";
        public static final String SHA_256 = "SHA-256";
        public static final String SHA_384 = "SHA-384";
        public static final String SHA_512 = "SHA-512";
        public static final String SHA_512_224 = "SHA-512/224";
        public static final String SHA_512_256 = "SHA-512/256";
        public static final String SHA3_224 = "SHA3-224";
        public static final String SHA3_256 = "SHA3-256";
        public static final String SHA3_384 = "SHA3-384";
        public static final String SHA3_512 = "SHA3-512";
    }

    /**
     * Gets a {@code MessageDigest} for the given {@code algorithm}.
     *
     * @param algorithm the name of the algorithm requested.
     * @return A digest instance.
     * @throws IllegalArgumentException when a {@link NoSuchAlgorithmException} is caught.
     * @see MessageDigest#getInstance(String)
     */
    public static MessageDigest getDigest(final String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (final NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Calculates the message digest for the given data and given algorithm.
     *
     * @param bytes     Data to digest
     * @param algorithm the name of the algorithm requested.
     * @return digest result
     */
    public static byte[] digest(final byte[] bytes, String algorithm) {
        MessageDigest messageDigest = getDigest(algorithm);
        messageDigest.update(bytes);
        return messageDigest.digest();
    }

    /**
     * Calculates the message digest for the given data and given algorithm.
     *
     * @param str       Data to digest
     * @param algorithm the name of the algorithm requested.
     * @param charset   the charset of the string
     * @return digest result
     */
    public static byte[] digest(final String str, final String algorithm, final Charset charset) {
        MessageDigest messageDigest = getDigest(algorithm);
        messageDigest.update(str.getBytes(charset));
        return messageDigest.digest();
    }

    /**
     * Calculates the message digest for the given data and given algorithm.
     *
     * @param inputStream Data to digest
     * @param algorithm   the name of the algorithm requested.
     * @return digest result
     * @throws IOException if an I/O error occurs
     */
    public static byte[] digest(final InputStream inputStream, final String algorithm) throws IOException {
        MessageDigest messageDigest = getDigest(algorithm);
        byte[] buffer = new byte[8192];
        var length = inputStream.read(buffer);
        while (length > -1) {
            messageDigest.update(buffer, 0, length);
            length = inputStream.read(buffer);
        }
        return messageDigest.digest();
    }

    /**
     * Calculates the message digest for the given data and given algorithm.
     *
     * @param file      Data to digest
     * @param algorithm the name of the algorithm requested.
     * @return digest result
     * @throws IOException if an I/O error occurs
     */
    public static byte[] digest(final File file, final String algorithm) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            return digest(inputStream, algorithm);
        }
    }

    /**
     * Calculates the message digest for the given data and given algorithm.
     *
     * @param file      Data to digest
     * @param algorithm the name of the algorithm requested.
     * @return digest result
     * @throws IOException if an I/O error occurs
     */
    public static byte[] digest(final Path file, final String algorithm) throws IOException {
        try (InputStream inputStream = Files.newInputStream(file)) {
            return digest(inputStream, algorithm);
        }
    }

    // region MD2

    /**
     * Calculates the MD2 digest
     *
     * @param data Data to digest
     * @return MD2 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] md2(final InputStream data) throws IOException {
        return digest(data, Algorithm.MD2);
    }

    /**
     * Calculates the MD2 digest
     *
     * @param data Data to digest
     * @return MD2 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] md2(final File data) throws IOException {
        return digest(data, Algorithm.MD2);
    }

    /**
     * Calculates the MD2 digest
     *
     * @param data Data to digest
     * @return MD2 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] md2(final Path data) throws IOException {
        return digest(data, Algorithm.MD2);
    }

    /**
     * Calculates the MD2 digest
     *
     * @param data Data to digest
     * @return MD2 digest
     */
    public static byte[] md2(final byte[] data) {
        return digest(data, Algorithm.MD2);
    }

    /**
     * Calculates the MD2 digest
     *
     * @param data Data to digest
     * @return MD2 digest
     */
    public static byte[] md2(final String data) {
        return digest(data, Algorithm.MD2, DEFAULT_CHARSET);
    }
    // endregion

    // region MD5

    /**
     * Calculates the MD5 digest
     *
     * @param data Data to digest
     * @return MD5 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] md5(final InputStream data) throws IOException {
        return digest(data, Algorithm.MD5);
    }

    /**
     * Calculates the MD5 digest
     *
     * @param data Data to digest
     * @return MD5 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] md5(final File data) throws IOException {
        return digest(data, Algorithm.MD5);
    }

    /**
     * Calculates the MD5 digest
     *
     * @param data Data to digest
     * @return MD5 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] md5(final Path data) throws IOException {
        return digest(data, Algorithm.MD5);
    }

    /**
     * Calculates the MD5 digest
     *
     * @param data Data to digest
     * @return MD5 digest
     */
    public static byte[] md5(final byte[] data) {
        return digest(data, Algorithm.MD5);
    }

    /**
     * Calculates the MD5 digest
     *
     * @param data Data to digest
     * @return MD5 digest
     */
    public static byte[] md5(final String data) {
        return digest(data, Algorithm.MD5, DEFAULT_CHARSET);
    }
    // endregion

    // region SHA-1

    /**
     * Calculates the SHA-1 digest
     *
     * @param data Data to digest
     * @return SHA-1 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] sha1(final InputStream data) throws IOException {
        return digest(data, Algorithm.SHA_1);
    }

    /**
     * Calculates the SHA-1 digest
     *
     * @param data Data to digest
     * @return SHA-1 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] sha1(final File data) throws IOException {
        return digest(data, Algorithm.SHA_1);
    }

    /**
     * Calculates the SHA-1 digest
     *
     * @param data Data to digest
     * @return SHA-1 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] sha1(final Path data) throws IOException {
        return digest(data, Algorithm.SHA_1);
    }

    /**
     * Calculates the SHA-1 digest
     *
     * @param data Data to digest
     * @return SHA-1 digest
     */
    public static byte[] sha1(final byte[] data) {
        return digest(data, Algorithm.SHA_1);
    }

    /**
     * Calculates the SHA-1 digest
     *
     * @param data Data to digest
     * @return SHA-1 digest
     */
    public static byte[] sha1(final String data) {
        return digest(data, Algorithm.SHA_1, DEFAULT_CHARSET);
    }
    // endregion

    // region SHA-224

    /**
     * Calculates the SHA-224 digest
     *
     * @param data Data to digest
     * @return SHA-224 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] sha224(final InputStream data) throws IOException {
        return digest(data, Algorithm.SHA_224);
    }

    /**
     * Calculates the SHA-224 digest
     *
     * @param data Data to digest
     * @return SHA-224 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] sha224(final File data) throws IOException {
        return digest(data, Algorithm.SHA_224);
    }

    /**
     * Calculates the SHA-224 digest
     *
     * @param data Data to digest
     * @return SHA-224 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] sha224(final Path data) throws IOException {
        return digest(data, Algorithm.SHA_224);
    }

    /**
     * Calculates the SHA-224 digest
     *
     * @param data Data to digest
     * @return SHA-224 digest
     */
    public static byte[] sha224(final byte[] data) {
        return digest(data, Algorithm.SHA_224);
    }

    /**
     * Calculates the SHA-224 digest
     *
     * @param data Data to digest
     * @return SHA-224 digest
     */
    public static byte[] sha224(final String data) {
        return digest(data, Algorithm.SHA_224, DEFAULT_CHARSET);
    }
    // endregion

    // region SHA-256

    /**
     * Calculates the SHA-256 digest
     *
     * @param data Data to digest
     * @return SHA-256 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] sha256(final InputStream data) throws IOException {
        return digest(data, Algorithm.SHA_256);
    }

    /**
     * Calculates the SHA-256 digest
     *
     * @param data Data to digest
     * @return SHA-256 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] sha256(final File data) throws IOException {
        return digest(data, Algorithm.SHA_256);
    }

    /**
     * Calculates the SHA-256 digest
     *
     * @param data Data to digest
     * @return SHA-256 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] sha256(final Path data) throws IOException {
        return digest(data, Algorithm.SHA_256);
    }

    /**
     * Calculates the SHA-256 digest
     *
     * @param data Data to digest
     * @return SHA-256 digest
     */
    public static byte[] sha256(final byte[] data) {
        return digest(data, Algorithm.SHA_256);
    }

    /**
     * Calculates the SHA-256 digest
     *
     * @param data Data to digest
     * @return SHA-256 digest
     */
    public static byte[] sha256(final String data) {
        return digest(data, Algorithm.SHA_256, DEFAULT_CHARSET);
    }
    // endregion

    // region SHA-384

    /**
     * Calculates the SHA-384 digest
     *
     * @param data Data to digest
     * @return SHA-384 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] sha384(final InputStream data) throws IOException {
        return digest(data, Algorithm.SHA_384);
    }

    /**
     * Calculates the SHA-384 digest
     *
     * @param data Data to digest
     * @return SHA-384 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] sha384(final File data) throws IOException {
        return digest(data, Algorithm.SHA_384);
    }

    /**
     * Calculates the SHA-384 digest
     *
     * @param data Data to digest
     * @return SHA-384 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] sha384(final Path data) throws IOException {
        return digest(data, Algorithm.SHA_384);
    }

    /**
     * Calculates the SHA-384 digest
     *
     * @param data Data to digest
     * @return SHA-384 digest
     */
    public static byte[] sha384(final byte[] data) {
        return digest(data, Algorithm.SHA_384);
    }

    /**
     * Calculates the SHA-384 digest
     *
     * @param data Data to digest
     * @return SHA-384 digest
     */
    public static byte[] sha384(final String data) {
        return digest(data, Algorithm.SHA_384, DEFAULT_CHARSET);
    }
    // endregion

    // region SHA-512

    /**
     * Calculates the SHA-512 digest
     *
     * @param data Data to digest
     * @return SHA-512 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] sha512(final InputStream data) throws IOException {
        return digest(data, Algorithm.SHA_512);
    }

    /**
     * Calculates the SHA-512 digest
     *
     * @param data Data to digest
     * @return SHA-512 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] sha512(final File data) throws IOException {
        return digest(data, Algorithm.SHA_512);
    }

    /**
     * Calculates the SHA-512 digest
     *
     * @param data Data to digest
     * @return SHA-512 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] sha512(final Path data) throws IOException {
        return digest(data, Algorithm.SHA_512);
    }

    /**
     * Calculates the SHA-512 digest
     *
     * @param data Data to digest
     * @return SHA-512 digest
     */
    public static byte[] sha512(final byte[] data) {
        return digest(data, Algorithm.SHA_512);
    }

    /**
     * Calculates the SHA-512 digest
     *
     * @param data Data to digest
     * @return SHA-512 digest
     */
    public static byte[] sha512(final String data) {
        return digest(data, Algorithm.SHA_512, DEFAULT_CHARSET);
    }
    // endregion

    // region SHA-512/224

    /**
     * Calculates the SHA-512/224 digest
     *
     * @param data Data to digest
     * @return SHA-512/224 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] sha512_224(final InputStream data) throws IOException {
        return digest(data, Algorithm.SHA_512_224);
    }

    /**
     * Calculates the SHA-512/224 digest
     *
     * @param data Data to digest
     * @return SHA-512/224 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] sha512_224(final File data) throws IOException {
        return digest(data, Algorithm.SHA_512_224);
    }

    /**
     * Calculates the SHA-512/224 digest
     *
     * @param data Data to digest
     * @return SHA-512/224 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] sha512_224(final Path data) throws IOException {
        return digest(data, Algorithm.SHA_512_224);
    }

    /**
     * Calculates the SHA-512/224 digest
     *
     * @param data Data to digest
     * @return SHA-512/224 digest
     */
    public static byte[] sha512_224(final byte[] data) {
        return digest(data, Algorithm.SHA_512_224);
    }

    /**
     * Calculates the SHA-512/224 digest
     *
     * @param data Data to digest
     * @return SHA-512/224 digest
     */
    public static byte[] sha512_224(final String data) {
        return digest(data, Algorithm.SHA_512_224, DEFAULT_CHARSET);
    }
    // endregion

    // region SHA-512/256

    /**
     * Calculates the SHA-512/256 digest
     *
     * @param data Data to digest
     * @return SHA-512/256 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] sha512_256(final InputStream data) throws IOException {
        return digest(data, Algorithm.SHA_512_256);
    }

    /**
     * Calculates the SHA-512/256 digest
     *
     * @param data Data to digest
     * @return SHA-512/256 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] sha512_256(final File data) throws IOException {
        return digest(data, Algorithm.SHA_512_256);
    }

    /**
     * Calculates the SHA-512/256 digest
     *
     * @param data Data to digest
     * @return SHA-512/256 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] sha512_256(final Path data) throws IOException {
        return digest(data, Algorithm.SHA_512_256);
    }

    /**
     * Calculates the SHA-512/256 digest
     *
     * @param data Data to digest
     * @return SHA-512/256 digest
     */
    public static byte[] sha512_256(final byte[] data) {
        return digest(data, Algorithm.SHA_512_256);
    }

    /**
     * Calculates the SHA-512/256 digest
     *
     * @param data Data to digest
     * @return SHA-512/256 digest
     */
    public static byte[] sha512_256(final String data) {
        return digest(data, Algorithm.SHA_512_256, DEFAULT_CHARSET);
    }
    // endregion

    // region SHA3-224

    /**
     * Calculates the SHA3-224 digest
     *
     * @param data Data to digest
     * @return SHA3-224 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] sha3_224(final InputStream data) throws IOException {
        return digest(data, Algorithm.SHA3_224);
    }

    /**
     * Calculates the SHA3-224 digest
     *
     * @param data Data to digest
     * @return SHA3-224 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] sha3_224(final File data) throws IOException {
        return digest(data, Algorithm.SHA3_224);
    }

    /**
     * Calculates the SHA3-224 digest
     *
     * @param data Data to digest
     * @return SHA3-224 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] sha3_224(final Path data) throws IOException {
        return digest(data, Algorithm.SHA3_224);
    }

    /**
     * Calculates the SHA3-224 digest
     *
     * @param data Data to digest
     * @return SHA3-224 digest
     */
    public static byte[] sha3_224(final byte[] data) {
        return digest(data, Algorithm.SHA3_224);
    }

    /**
     * Calculates the SHA3-224 digest
     *
     * @param data Data to digest
     * @return SHA3-224 digest
     */
    public static byte[] sha3_224(final String data) {
        return digest(data, Algorithm.SHA3_224, DEFAULT_CHARSET);
    }
    // endregion

    // region SHA3-256

    /**
     * Calculates the SHA3-256 digest
     *
     * @param data Data to digest
     * @return SHA3-256 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] sha3_256(final InputStream data) throws IOException {
        return digest(data, Algorithm.SHA3_256);
    }

    /**
     * Calculates the SHA3-256 digest
     *
     * @param data Data to digest
     * @return SHA3-256 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] sha3_256(final File data) throws IOException {
        return digest(data, Algorithm.SHA3_256);
    }

    /**
     * Calculates the SHA3-256 digest
     *
     * @param data Data to digest
     * @return SHA3-256 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] sha3_256(final Path data) throws IOException {
        return digest(data, Algorithm.SHA3_256);
    }

    /**
     * Calculates the SHA3-256 digest
     *
     * @param data Data to digest
     * @return SHA3-256 digest
     */
    public static byte[] sha3_256(final byte[] data) {
        return digest(data, Algorithm.SHA3_256);
    }

    /**
     * Calculates the SHA3-256 digest
     *
     * @param data Data to digest
     * @return SHA3-256 digest
     */
    public static byte[] sha3_256(final String data) {
        return digest(data, Algorithm.SHA3_256, DEFAULT_CHARSET);
    }
    // endregion

    // region SHA3-384

    /**
     * Calculates the SHA3-384 digest
     *
     * @param data Data to digest
     * @return SHA3-384 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] sha3_384(final InputStream data) throws IOException {
        return digest(data, Algorithm.SHA3_384);
    }

    /**
     * Calculates the SHA3-384 digest
     *
     * @param data Data to digest
     * @return SHA3-384 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] sha3_384(final File data) throws IOException {
        return digest(data, Algorithm.SHA3_384);
    }

    /**
     * Calculates the SHA3-384 digest
     *
     * @param data Data to digest
     * @return SHA3-384 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] sha3_384(final Path data) throws IOException {
        return digest(data, Algorithm.SHA3_384);
    }

    /**
     * Calculates the SHA3-384 digest
     *
     * @param data Data to digest
     * @return SHA3-384 digest
     */
    public static byte[] sha3_384(final byte[] data) {
        return digest(data, Algorithm.SHA3_384);
    }

    /**
     * Calculates the SHA3-384 digest
     *
     * @param data Data to digest
     * @return SHA3-384 digest
     */
    public static byte[] sha3_384(final String data) {
        return digest(data, Algorithm.SHA3_384, DEFAULT_CHARSET);
    }
    // endregion

    // region SHA3-512

    /**
     * Calculates the SHA3-512 digest
     *
     * @param data Data to digest
     * @return SHA3-512 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] sha3_512(final InputStream data) throws IOException {
        return digest(data, Algorithm.SHA3_512);
    }

    /**
     * Calculates the SHA3-512 digest
     *
     * @param data Data to digest
     * @return SHA3-512 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] sha3_512(final File data) throws IOException {
        return digest(data, Algorithm.SHA3_512);
    }

    /**
     * Calculates the SHA3-512 digest
     *
     * @param data Data to digest
     * @return SHA3-512 digest
     * @throws IOException if an I/O error occurs
     */
    public static byte[] sha3_512(final Path data) throws IOException {
        return digest(data, Algorithm.SHA3_512);
    }

    /**
     * Calculates the SHA3-512 digest
     *
     * @param data Data to digest
     * @return SHA3-512 digest
     */
    public static byte[] sha3_512(final byte[] data) {
        return digest(data, Algorithm.SHA3_512);
    }

    /**
     * Calculates the SHA3-512 digest
     *
     * @param data Data to digest
     * @return SHA3-512 digest
     */
    public static byte[] sha3_512(final String data) {
        return digest(data, Algorithm.SHA3_512, DEFAULT_CHARSET);
    }
    // endregion

    private Digest() {
        // don't instantiate
    }
}
