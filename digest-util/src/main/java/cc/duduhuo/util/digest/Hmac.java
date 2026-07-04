/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.digest;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public final class Hmac {

    /**
     * Default charset is {@link StandardCharsets#UTF_8}.
     */
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    public static final class Algorithm {
        // public static final String HMAC_MD2 = "HmacMD2"; // not available
        public static final String HMAC_MD5 = "HmacMD5";
        public static final String HMAC_SHA_1 = "HmacSHA1";
        public static final String HMAC_SHA_224 = "HmacSHA224";
        public static final String HMAC_SHA_256 = "HmacSHA256";
        public static final String HMAC_SHA_384 = "HmacSHA384";
        public static final String HMAC_SHA_512 = "HmacSHA512";
        // public static final String HMAC_SHA_512_224 = "HmacSHA-512/224"; // not available
        // public static final String HMAC_SHA_512_256 = "HmacSHA-512/256"; // not available
        // public static final String HMAC_SHA3_224 = "HmacSHA3-224"; // not available
        // public static final String HMAC_SHA3_256 = "HmacSHA3-256"; // not available
        // public static final String HMAC_SHA3_384 = "HmacSHA3-384"; // not available
        // public static final String HMAC_SHA3_512 = "HmacSHA3-512"; // not available
    }

    /**
     * Gets an initialized {@code Mac} for the given {@code algorithm}.
     *
     * @param algorithm the name of the algorithm requested.
     * @param key       The key for the keyed digest (must not be null).
     * @return A Mac instance initialized with the given key.
     * @throws IllegalArgumentException when a {@link NoSuchAlgorithmException} is caught or key is null or key is invalid.
     * @see Mac#getInstance(String)
     * @see Mac#init(java.security.Key)
     */
    public static Mac getInitializedMac(final String algorithm, final byte[] key) {
        if (key == null) {
            throw new IllegalArgumentException("Key must not be null");
        }
        try {
            final SecretKeySpec keySpec = new SecretKeySpec(key, algorithm);
            final Mac mac = Mac.getInstance(algorithm);
            mac.init(keySpec);
            return mac;
        } catch (final NoSuchAlgorithmException | InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Calculates the Message Authentication Code (MAC) for the given key and data.
     *
     * @param bytes     The data which should digest.
     * @param algorithm the name of the algorithm requested.
     * @param key       The key for the keyed digest (must not be null).
     * @return MAC for the given algorithm, key and value.
     */
    public static byte[] hmac(final byte[] bytes, String algorithm, final byte[] key) {
        Mac mac = getInitializedMac(algorithm, key);
        mac.update(bytes);
        return mac.doFinal();
    }

    /**
     * Calculates the Message Authentication Code (MAC) for the given key and data.
     *
     * @param str       The data which should digest.
     * @param algorithm the name of the algorithm requested.
     * @param key       The key for the keyed digest (must not be null).
     * @param charset   The charset for the string.
     * @return MAC for the given algorithm, key and value.
     */
    public static byte[] hmac(final String str, final String algorithm, final byte[] key, final Charset charset) {
        Mac mac = getInitializedMac(algorithm, key);
        mac.update(str.getBytes(charset));
        return mac.doFinal();
    }

    /**
     * Calculates the Message Authentication Code (MAC) for the given key and data.
     *
     * @param inputStream The data which should digest.
     * @param algorithm   the name of the algorithm requested.
     * @param key         The key for the keyed digest (must not be null).
     * @return MAC for the given algorithm, key and value.
     * @throws IOException if an I/O error occurs
     */
    public static byte[] hmac(final InputStream inputStream, final String algorithm, final byte[] key) throws IOException {
        Mac mac = getInitializedMac(algorithm, key);
        byte[] buffer = new byte[8192];
        var length = inputStream.read(buffer);
        while (length > -1) {
            mac.update(buffer, 0, length);
            length = inputStream.read(buffer);
        }
        return mac.doFinal();
    }

    /**
     * Calculates the Message Authentication Code (MAC) for the given key and data.
     *
     * @param file      The data which should digest.
     * @param algorithm the name of the algorithm requested.
     * @param key       The key for the keyed digest (must not be null).
     * @return MAC for the given algorithm, key and value.
     * @throws IOException if an I/O error occurs
     */
    public static byte[] hmac(final File file, final String algorithm, final byte[] key) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            return hmac(inputStream, algorithm, key);
        }
    }

    /**
     * Calculates the Message Authentication Code (MAC) for the given key and data.
     *
     * @param file      The data which should digest.
     * @param algorithm the name of the algorithm requested.
     * @param key       The key for the keyed digest (must not be null).
     * @return MAC for the given algorithm, key and value.
     * @throws IOException if an I/O error occurs
     */
    public static byte[] hmac(final Path file, final String algorithm, final byte[] key) throws IOException {
        try (InputStream inputStream = Files.newInputStream(file)) {
            return hmac(inputStream, algorithm, key);
        }
    }

    // region HmacMD5

    /**
     * Calculates a HmacMD5 Message Authentication Code (MAC) for the given key and data.
     *
     * @param data The data which should digest.
     * @return HmacMD5 MAC for the given key and data.
     * @throws IOException if an I/O error occurs
     */
    public static byte[] hmacMd5(final InputStream data, final byte[] key) throws IOException {
        return hmac(data, Algorithm.HMAC_MD5, key);
    }

    /**
     * Calculates a HmacMD5 Message Authentication Code (MAC) for the given key and data.
     *
     * @param data The data which should digest.
     * @return HmacMD5 MAC for the given key and data.
     * @throws IOException if an I/O error occurs
     */
    public static byte[] hmacMd5(final File data, final byte[] key) throws IOException {
        return hmac(data, Algorithm.HMAC_MD5, key);
    }

    /**
     * Calculates a HmacMD5 Message Authentication Code (MAC) for the given key and data.
     *
     * @param data The data which should digest.
     * @return HmacMD5 MAC for the given key and data.
     * @throws IOException if an I/O error occurs
     */
    public static byte[] hmacMd5(final Path data, final byte[] key) throws IOException {
        return hmac(data, Algorithm.HMAC_MD5, key);
    }

    /**
     * Calculates a HmacMD5 Message Authentication Code (MAC) for the given key and data.
     *
     * @param data The data which should digest.
     * @return HmacMD5 MAC for the given key and data.
     */
    public static byte[] hmacMd5(final byte[] data, final byte[] key) {
        return hmac(data, Algorithm.HMAC_MD5, key);
    }

    /**
     * Calculates a HmacMD5 Message Authentication Code (MAC) for the given key and data.
     *
     * @param data The data which should digest.
     * @return HmacMD5 MAC for the given key and data.
     */
    public static byte[] hmacMd5(final String data, final byte[] key) {
        return hmac(data, Algorithm.HMAC_MD5, key, DEFAULT_CHARSET);
    }
    // endregion

    // region HmacSHA1

    /**
     * Calculates a HmacSHA1 Message Authentication Code (MAC) for the given key and data.
     *
     * @param data The data which should digest.
     * @return HmacSHA1 MAC for the given key and data.
     * @throws IOException if an I/O error occurs
     */
    public static byte[] hmacSha1(final InputStream data, final byte[] key) throws IOException {
        return hmac(data, Algorithm.HMAC_SHA_1, key);
    }

    /**
     * Calculates a HmacSHA1 Message Authentication Code (MAC) for the given key and data.
     *
     * @param data The data which should digest.
     * @return HmacSHA1 MAC for the given key and data.
     * @throws IOException if an I/O error occurs
     */
    public static byte[] hmacSha1(final File data, final byte[] key) throws IOException {
        return hmac(data, Algorithm.HMAC_SHA_1, key);
    }

    /**
     * Calculates a HmacSHA1 Message Authentication Code (MAC) for the given key and data.
     *
     * @param data The data which should digest.
     * @return HmacSHA1 MAC for the given key and data.
     * @throws IOException if an I/O error occurs
     */
    public static byte[] hmacSha1(final Path data, final byte[] key) throws IOException {
        return hmac(data, Algorithm.HMAC_SHA_1, key);
    }

    /**
     * Calculates a HmacSHA1 Message Authentication Code (MAC) for the given key and data.
     *
     * @param data The data which should digest.
     * @return HmacSHA1 MAC for the given key and data.
     */
    public static byte[] hmacSha1(final byte[] data, final byte[] key) {
        return hmac(data, Algorithm.HMAC_SHA_1, key);
    }

    /**
     * Calculates a HmacSHA1 Message Authentication Code (MAC) for the given key and data.
     *
     * @param data The data which should digest.
     * @return HmacSHA1 MAC for the given key and data.
     */
    public static byte[] hmacSha1(final String data, final byte[] key) {
        return hmac(data, Algorithm.HMAC_SHA_1, key, DEFAULT_CHARSET);
    }
    // endregion

    // region HmacSHA224

    /**
     * Calculates a HmacSHA224 Message Authentication Code (MAC) for the given key and data.
     *
     * @param data The data which should digest.
     * @return HmacSHA224 MAC for the given key and data.
     * @throws IOException if an I/O error occurs
     */
    public static byte[] hmacSha224(final InputStream data, final byte[] key) throws IOException {
        return hmac(data, Algorithm.HMAC_SHA_224, key);
    }

    /**
     * Calculates a HmacSHA224 Message Authentication Code (MAC) for the given key and data.
     *
     * @param data The data which should digest.
     * @return HmacSHA224 MAC for the given key and data.
     * @throws IOException if an I/O error occurs
     */
    public static byte[] hmacSha224(final File data, final byte[] key) throws IOException {
        return hmac(data, Algorithm.HMAC_SHA_224, key);
    }

    /**
     * Calculates a HmacSHA224 Message Authentication Code (MAC) for the given key and data.
     *
     * @param data The data which should digest.
     * @return HmacSHA224 MAC for the given key and data.
     * @throws IOException if an I/O error occurs
     */
    public static byte[] hmacSha224(final Path data, final byte[] key) throws IOException {
        return hmac(data, Algorithm.HMAC_SHA_224, key);
    }

    /**
     * Calculates a HmacSHA224 Message Authentication Code (MAC) for the given key and data.
     *
     * @param data The data which should digest.
     * @return HmacSHA224 MAC for the given key and data.
     */
    public static byte[] hmacSha224(final byte[] data, final byte[] key) {
        return hmac(data, Algorithm.HMAC_SHA_224, key);
    }

    /**
     * Calculates a HmacSHA224 Message Authentication Code (MAC) for the given key and data.
     *
     * @param data The data which should digest.
     * @return HmacSHA224 MAC for the given key and data.
     */
    public static byte[] hmacSha224(final String data, final byte[] key) {
        return hmac(data, Algorithm.HMAC_SHA_224, key, DEFAULT_CHARSET);
    }
    // endregion

    // region HmacSHA256

    /**
     * Calculates a HmacSHA256 Message Authentication Code (MAC) for the given key and data.
     *
     * @param data The data which should digest.
     * @return HmacSHA256 MAC for the given key and data.
     * @throws IOException if an I/O error occurs
     */
    public static byte[] hmacSha256(final InputStream data, final byte[] key) throws IOException {
        return hmac(data, Algorithm.HMAC_SHA_256, key);
    }

    /**
     * Calculates a HmacSHA256 Message Authentication Code (MAC) for the given key and data.
     *
     * @param data The data which should digest.
     * @return HmacSHA256 MAC for the given key and data.
     * @throws IOException if an I/O error occurs
     */
    public static byte[] hmacSha256(final File data, final byte[] key) throws IOException {
        return hmac(data, Algorithm.HMAC_SHA_256, key);
    }

    /**
     * Calculates a HmacSHA256 Message Authentication Code (MAC) for the given key and data.
     *
     * @param data The data which should digest.
     * @return HmacSHA256 MAC for the given key and data.
     * @throws IOException if an I/O error occurs
     */
    public static byte[] hmacSha256(final Path data, final byte[] key) throws IOException {
        return hmac(data, Algorithm.HMAC_SHA_256, key);
    }

    /**
     * Calculates a HmacSHA256 Message Authentication Code (MAC) for the given key and data.
     *
     * @param data The data which should digest.
     * @return HmacSHA256 MAC for the given key and data.
     */
    public static byte[] hmacSha256(final byte[] data, final byte[] key) {
        return hmac(data, Algorithm.HMAC_SHA_256, key);
    }

    /**
     * Calculates a HmacSHA256 Message Authentication Code (MAC) for the given key and data.
     *
     * @param data The data which should digest.
     * @return HmacSHA256 MAC for the given key and data.
     */
    public static byte[] hmacSha256(final String data, final byte[] key) {
        return hmac(data, Algorithm.HMAC_SHA_256, key, DEFAULT_CHARSET);
    }
    // endregion

    // region HmacSHA384

    /**
     * Calculates a HmacSHA384 Message Authentication Code (MAC) for the given key and data.
     *
     * @param data The data which should digest.
     * @return HmacSHA384 MAC for the given key and data.
     * @throws IOException if an I/O error occurs
     */
    public static byte[] hmacSha384(final InputStream data, final byte[] key) throws IOException {
        return hmac(data, Algorithm.HMAC_SHA_384, key);
    }

    /**
     * Calculates a HmacSHA384 Message Authentication Code (MAC) for the given key and data.
     *
     * @param data The data which should digest.
     * @return HmacSHA384 MAC for the given key and data.
     * @throws IOException if an I/O error occurs
     */
    public static byte[] hmacSha384(final File data, final byte[] key) throws IOException {
        return hmac(data, Algorithm.HMAC_SHA_384, key);
    }

    /**
     * Calculates a HmacSHA384 Message Authentication Code (MAC) for the given key and data.
     *
     * @param data The data which should digest.
     * @return HmacSHA384 MAC for the given key and data.
     * @throws IOException if an I/O error occurs
     */
    public static byte[] hmacSha384(final Path data, final byte[] key) throws IOException {
        return hmac(data, Algorithm.HMAC_SHA_384, key);
    }

    /**
     * Calculates a HmacSHA384 Message Authentication Code (MAC) for the given key and data.
     *
     * @param data The data which should digest.
     * @return HmacSHA384 MAC for the given key and data.
     */
    public static byte[] hmacSha384(final byte[] data, final byte[] key) {
        return hmac(data, Algorithm.HMAC_SHA_384, key);
    }

    /**
     * Calculates a HmacSHA384 Message Authentication Code (MAC) for the given key and data.
     *
     * @param data The data which should digest.
     * @return HmacSHA384 MAC for the given key and data.
     */
    public static byte[] hmacSha384(final String data, final byte[] key) {
        return hmac(data, Algorithm.HMAC_SHA_384, key, DEFAULT_CHARSET);
    }
    // endregion

    // region HmacSHA512

    /**
     * Calculates a HmacSHA512 Message Authentication Code (MAC) for the given key and data.
     *
     * @param data The data which should digest.
     * @return HmacSHA512 MAC for the given key and data.
     * @throws IOException if an I/O error occurs
     */
    public static byte[] hmacSha512(final InputStream data, final byte[] key) throws IOException {
        return hmac(data, Algorithm.HMAC_SHA_512, key);
    }

    /**
     * Calculates a HmacSHA512 Message Authentication Code (MAC) for the given key and data.
     *
     * @param data The data which should digest.
     * @return HmacSHA512 MAC for the given key and data.
     * @throws IOException if an I/O error occurs
     */
    public static byte[] hmacSha512(final File data, final byte[] key) throws IOException {
        return hmac(data, Algorithm.HMAC_SHA_512, key);
    }

    /**
     * Calculates a HmacSHA512 Message Authentication Code (MAC) for the given key and data.
     *
     * @param data The data which should digest.
     * @return HmacSHA512 MAC for the given key and data.
     * @throws IOException if an I/O error occurs
     */
    public static byte[] hmacSha512(final Path data, final byte[] key) throws IOException {
        return hmac(data, Algorithm.HMAC_SHA_512, key);
    }

    /**
     * Calculates a HmacSHA512 Message Authentication Code (MAC) for the given key and data.
     *
     * @param data The data which should digest.
     * @return HmacSHA512 MAC for the given key and data.
     */
    public static byte[] hmacSha512(final byte[] data, final byte[] key) {
        return hmac(data, Algorithm.HMAC_SHA_512, key);
    }

    /**
     * Calculates a HmacSHA512 Message Authentication Code (MAC) for the given key and data.
     *
     * @param data The data which should digest.
     * @return HmacSHA512 MAC for the given key and data.
     */
    public static byte[] hmacSha512(final String data, final byte[] key) {
        return hmac(data, Algorithm.HMAC_SHA_512, key, DEFAULT_CHARSET);
    }
    // endregion

    private Hmac() {
        // don't instantiate
    }
}
