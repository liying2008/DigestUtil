package cc.duduhuo.util.crypto

import cc.duduhuo.util.digest.Base64
import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

/**
 * =======================================================<br>
 * Author: liying - liruoer2008@yeah.net <br>
 * Datetime: 2017/10/28 18:37 <br>
 * Description: AES Tools <br>
 * Remarks: <br>
 * =======================================================<br>
 */
object AES {
    private const val ALGORITHM = "AES"
    private const val TRANSFORMATION = "AES"
    /**
     * Encrypts a password.
     * @param input The password to be encrypted.
     * @param seed The seed.
     * @return Encrypted password.
     */
    @JvmStatic
    fun encrypt(input: ByteArray, seed: ByteArray): String {
        val key = getRawKey(Arrays.copyOf(seed, 16))
        val result = toEncrypt(key, input)
        return Base64.encode(result)
    }

    /**
     * Encrypts a password.
     * @param input The password to be encrypted.
     * @param seed The seed.
     * @return Encrypted password.
     */
    @JvmStatic
    fun encrypt(input: String, seed: ByteArray): String {
        val key = getRawKey(Arrays.copyOf(seed, 16))
        val result = toEncrypt(key, input.toByteArray())
        return Base64.encode(result)
    }

    /**
     * Encrypts a password.
     * @param input The password to be encrypted.
     * @param seed The seed.
     * @return Encrypted password.
     */
    @JvmStatic
    fun encrypt(input: ByteArray, seed: String): String {
        val key = getRawKey(Arrays.copyOf(seed.toByteArray(), 16))
        val result = toEncrypt(key, input)
        return Base64.encode(result)
    }

    /**
     * Encrypts a password.
     * @param input The password to be encrypted.
     * @param seed The seed.
     * @return Encrypted password.
     */
    @JvmStatic
    fun encrypt(input: String, seed: String): String {
        val key = getRawKey(Arrays.copyOf(seed.toByteArray(), 16))
        val result = toEncrypt(key, input.toByteArray())
        return Base64.encode(result)
    }

    /**
     * Decrypt a password.
     * @param input The password to be decrypted.
     * @param seed The seed.
     * @return Decrypted password.
     */
    @JvmStatic
    fun decrypt(input: ByteArray, seed: ByteArray): String {
        val key = getRawKey(Arrays.copyOf(seed, 16))
        val encrypted = Base64.decode(input)
        val result = toDecrypt(key, encrypted)
        return String(result)
    }

    /**
     * Decrypt a password.
     * @param input The password to be decrypted.
     * @param seed The seed.
     * @return Decrypted password.
     */
    @JvmStatic
    fun decrypt(input: String, seed: ByteArray): String {
        val key = getRawKey(Arrays.copyOf(seed, 16))
        val encrypted = Base64.decode(input)
        val result = toDecrypt(key, encrypted)
        return String(result)
    }

    /**
     * Decrypt a password.
     * @param input The password to be decrypted.
     * @param seed The seed.
     * @return Decrypted password.
     */
    @JvmStatic
    fun decrypt(input: ByteArray, seed: String): String {
        val key = getRawKey(Arrays.copyOf(seed.toByteArray(), 16))
        val encrypted = Base64.decode(input)
        val result = toDecrypt(key, encrypted)
        return String(result)
    }

    /**
     * Decrypt a password.
     * @param input The password to be decrypted.
     * @param seed The seed.
     * @return Decrypted password.
     */
    @JvmStatic
    fun decrypt(input: String, seed: String): String {
        val key = getRawKey(Arrays.copyOf(seed.toByteArray(), 16))
        val encrypted = Base64.decode(input)
        val result = toDecrypt(key, encrypted)
        return String(result)
    }

    private fun getRawKey(seed: ByteArray): SecretKey = SecretKeySpec(seed, ALGORITHM)

    private fun toEncrypt(key: SecretKey, input: ByteArray): ByteArray {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, key)
        return cipher.doFinal(input)
    }

    private fun toDecrypt(key: SecretKey, encrypted: ByteArray): ByteArray {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.DECRYPT_MODE, key)
        return cipher.doFinal(encrypted)
    }
}
