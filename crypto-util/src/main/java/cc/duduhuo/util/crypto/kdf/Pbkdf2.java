/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.crypto.kdf;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public final class Pbkdf2 {

    /**
     * Default charset is {@link StandardCharsets#UTF_8}.
     */
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    public static final class Algorithm {
        public static final String HMAC_SHA_1 = "PBKDF2WithHmacSHA1";
        public static final String HMAC_SHA_256 = "PBKDF2WithHmacSHA256";
        public static final String HMAC_SHA_384 = "PBKDF2WithHmacSHA384";
        public static final String HMAC_SHA_512 = "PBKDF2WithHmacSHA512";
    }

    /**
     * Gets a {@code SecretKeyFactory} for the given {@code algorithm}.
     *
     * @param algorithm the name of the algorithm requested.
     * @return A SecretKeyFactory instance.
     * @throws IllegalArgumentException when a {@link NoSuchAlgorithmException} is caught.
     * @see SecretKeyFactory#getInstance(String)
     */
    public static SecretKeyFactory getSecretKeyFactory(final String algorithm) {
        try {
            return SecretKeyFactory.getInstance(algorithm);
        } catch (final NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Derives a key with the PBKDF2 key derivation function.
     *
     * @param password       the password to derive the key from (must not be null).
     * @param salt           the salt, it is recommended to use at least 16 random bytes (must not be null or empty).
     * @param iterationCount the number of iterations, it is recommended to use at least 1000 (must be positive).
     * @param keyLength      the length of the derived key in bits (must be positive).
     * @param algorithm      the name of the algorithm requested.
     * @return the derived key.
     * @throws IllegalArgumentException when a {@link NoSuchAlgorithmException} or {@link InvalidKeySpecException}
     *                                  is caught, or the arguments are invalid.
     */
    public static byte[] pbkdf2(final char[] password, final byte[] salt, final int iterationCount,
                               final int keyLength, final String algorithm) {
        if (password == null) {
            throw new IllegalArgumentException("Password must not be null");
        }
        if (salt == null || salt.length == 0) {
            throw new IllegalArgumentException("Salt must not be null or empty");
        }
        if (iterationCount <= 0) {
            throw new IllegalArgumentException("Iteration count must be a positive number");
        }
        if (keyLength <= 0) {
            throw new IllegalArgumentException("Key length must be a positive number");
        }
        try {
            final PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keyLength);
            return getSecretKeyFactory(algorithm).generateSecret(keySpec).getEncoded();
        } catch (final InvalidKeySpecException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Derives a key with the PBKDF2 key derivation function.
     *
     * <p>Note: the password is decoded with {@link #DEFAULT_CHARSET}, it should be a UTF-8 encoded text password.</p>
     *
     * @param password       the password to derive the key from (must not be null).
     * @param salt           the salt (must not be null or empty).
     * @param iterationCount the number of iterations (must be positive).
     * @param keyLength      the length of the derived key in bits (must be positive).
     * @param algorithm      the name of the algorithm requested.
     * @return the derived key.
     */
    public static byte[] pbkdf2(final byte[] password, final byte[] salt, final int iterationCount,
                               final int keyLength, final String algorithm) {
        if (password == null) {
            throw new IllegalArgumentException("Password must not be null");
        }
        return pbkdf2(new String(password, DEFAULT_CHARSET), salt, iterationCount, keyLength, algorithm);
    }

    /**
     * Derives a key with the PBKDF2 key derivation function.
     *
     * <p>Note: the password is always encoded with {@link StandardCharsets#UTF_8} by the PBKDF2 implementation.</p>
     *
     * @param password       the password to derive the key from (must not be null).
     * @param salt           the salt (must not be null or empty).
     * @param iterationCount the number of iterations (must be positive).
     * @param keyLength      the length of the derived key in bits (must be positive).
     * @param algorithm      the name of the algorithm requested.
     * @return the derived key.
     */
    public static byte[] pbkdf2(final String password, final byte[] salt, final int iterationCount,
                               final int keyLength, final String algorithm) {
        if (password == null) {
            throw new IllegalArgumentException("Password must not be null");
        }
        return pbkdf2(password.toCharArray(), salt, iterationCount, keyLength, algorithm);
    }

    // region PBKDF2WithHmacSHA1

    /**
     * Derives a key with the PBKDF2WithHmacSHA1 key derivation function.
     *
     * @param password       the password to derive the key from.
     * @param salt           the salt.
     * @param iterationCount the number of iterations.
     * @param keyLength      the length of the derived key in bits.
     * @return the derived key.
     */
    public static byte[] pbkdf2WithHmacSha1(final char[] password, final byte[] salt,
                                           final int iterationCount, final int keyLength) {
        return pbkdf2(password, salt, iterationCount, keyLength, Algorithm.HMAC_SHA_1);
    }

    /**
     * Derives a key with the PBKDF2WithHmacSHA1 key derivation function.
     *
     * @param password       the password to derive the key from.
     * @param salt           the salt.
     * @param iterationCount the number of iterations.
     * @param keyLength      the length of the derived key in bits.
     * @return the derived key.
     */
    public static byte[] pbkdf2WithHmacSha1(final byte[] password, final byte[] salt,
                                           final int iterationCount, final int keyLength) {
        return pbkdf2(password, salt, iterationCount, keyLength, Algorithm.HMAC_SHA_1);
    }

    /**
     * Derives a key with the PBKDF2WithHmacSHA1 key derivation function.
     *
     * @param password       the password to derive the key from.
     * @param salt           the salt.
     * @param iterationCount the number of iterations.
     * @param keyLength      the length of the derived key in bits.
     * @return the derived key.
     */
    public static byte[] pbkdf2WithHmacSha1(final String password, final byte[] salt,
                                           final int iterationCount, final int keyLength) {
        return pbkdf2(password, salt, iterationCount, keyLength, Algorithm.HMAC_SHA_1);
    }
    // endregion

    // region PBKDF2WithHmacSHA256

    /**
     * Derives a key with the PBKDF2WithHmacSHA256 key derivation function.
     *
     * @param password       the password to derive the key from.
     * @param salt           the salt.
     * @param iterationCount the number of iterations.
     * @param keyLength      the length of the derived key in bits.
     * @return the derived key.
     */
    public static byte[] pbkdf2WithHmacSha256(final char[] password, final byte[] salt,
                                             final int iterationCount, final int keyLength) {
        return pbkdf2(password, salt, iterationCount, keyLength, Algorithm.HMAC_SHA_256);
    }

    /**
     * Derives a key with the PBKDF2WithHmacSHA256 key derivation function.
     *
     * @param password       the password to derive the key from.
     * @param salt           the salt.
     * @param iterationCount the number of iterations.
     * @param keyLength      the length of the derived key in bits.
     * @return the derived key.
     */
    public static byte[] pbkdf2WithHmacSha256(final byte[] password, final byte[] salt,
                                             final int iterationCount, final int keyLength) {
        return pbkdf2(password, salt, iterationCount, keyLength, Algorithm.HMAC_SHA_256);
    }

    /**
     * Derives a key with the PBKDF2WithHmacSHA256 key derivation function.
     *
     * @param password       the password to derive the key from.
     * @param salt           the salt.
     * @param iterationCount the number of iterations.
     * @param keyLength      the length of the derived key in bits.
     * @return the derived key.
     */
    public static byte[] pbkdf2WithHmacSha256(final String password, final byte[] salt,
                                             final int iterationCount, final int keyLength) {
        return pbkdf2(password, salt, iterationCount, keyLength, Algorithm.HMAC_SHA_256);
    }
    // endregion

    // region PBKDF2WithHmacSHA384

    /**
     * Derives a key with the PBKDF2WithHmacSHA384 key derivation function.
     *
     * @param password       the password to derive the key from.
     * @param salt           the salt.
     * @param iterationCount the number of iterations.
     * @param keyLength      the length of the derived key in bits.
     * @return the derived key.
     */
    public static byte[] pbkdf2WithHmacSha384(final char[] password, final byte[] salt,
                                             final int iterationCount, final int keyLength) {
        return pbkdf2(password, salt, iterationCount, keyLength, Algorithm.HMAC_SHA_384);
    }

    /**
     * Derives a key with the PBKDF2WithHmacSHA384 key derivation function.
     *
     * @param password       the password to derive the key from.
     * @param salt           the salt.
     * @param iterationCount the number of iterations.
     * @param keyLength      the length of the derived key in bits.
     * @return the derived key.
     */
    public static byte[] pbkdf2WithHmacSha384(final byte[] password, final byte[] salt,
                                             final int iterationCount, final int keyLength) {
        return pbkdf2(password, salt, iterationCount, keyLength, Algorithm.HMAC_SHA_384);
    }

    /**
     * Derives a key with the PBKDF2WithHmacSHA384 key derivation function.
     *
     * @param password       the password to derive the key from.
     * @param salt           the salt.
     * @param iterationCount the number of iterations.
     * @param keyLength      the length of the derived key in bits.
     * @return the derived key.
     */
    public static byte[] pbkdf2WithHmacSha384(final String password, final byte[] salt,
                                             final int iterationCount, final int keyLength) {
        return pbkdf2(password, salt, iterationCount, keyLength, Algorithm.HMAC_SHA_384);
    }
    // endregion

    // region PBKDF2WithHmacSHA512

    /**
     * Derives a key with the PBKDF2WithHmacSHA512 key derivation function.
     *
     * @param password       the password to derive the key from.
     * @param salt           the salt.
     * @param iterationCount the number of iterations.
     * @param keyLength      the length of the derived key in bits.
     * @return the derived key.
     */
    public static byte[] pbkdf2WithHmacSha512(final char[] password, final byte[] salt,
                                             final int iterationCount, final int keyLength) {
        return pbkdf2(password, salt, iterationCount, keyLength, Algorithm.HMAC_SHA_512);
    }

    /**
     * Derives a key with the PBKDF2WithHmacSHA512 key derivation function.
     *
     * @param password       the password to derive the key from.
     * @param salt           the salt.
     * @param iterationCount the number of iterations.
     * @param keyLength      the length of the derived key in bits.
     * @return the derived key.
     */
    public static byte[] pbkdf2WithHmacSha512(final byte[] password, final byte[] salt,
                                             final int iterationCount, final int keyLength) {
        return pbkdf2(password, salt, iterationCount, keyLength, Algorithm.HMAC_SHA_512);
    }

    /**
     * Derives a key with the PBKDF2WithHmacSHA512 key derivation function.
     *
     * @param password       the password to derive the key from.
     * @param salt           the salt.
     * @param iterationCount the number of iterations.
     * @param keyLength      the length of the derived key in bits.
     * @return the derived key.
     */
    public static byte[] pbkdf2WithHmacSha512(final String password, final byte[] salt,
                                             final int iterationCount, final int keyLength) {
        return pbkdf2(password, salt, iterationCount, keyLength, Algorithm.HMAC_SHA_512);
    }
    // endregion

    private Pbkdf2() {
        // don't instantiate
    }
}
