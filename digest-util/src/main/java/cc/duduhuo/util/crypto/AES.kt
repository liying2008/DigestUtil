package cc.duduhuo.util.crypto

import cc.duduhuo.util.digest.Base64
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
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
    /**
     * Encrypts a password.
     * @param input The password to be encrypted.
     * @param seed The seed.
     * @return Encrypted password.
     */
    @JvmStatic
    @Throws(Exception::class)
    fun encrypt(input: ByteArray, seed: ByteArray): String {
        val key = getRawKey(seed)
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
    @Throws(Exception::class)
    fun encrypt(input: String, seed: ByteArray): String {
        val key = getRawKey(seed)
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
    @Throws(Exception::class)
    fun encrypt(input: ByteArray, seed: String): String {
        val key = getRawKey(seed.toByteArray())
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
    @Throws(Exception::class)
    fun encrypt(input: String, seed: String): String {
        val key = getRawKey(seed.toByteArray())
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
    @Throws(Exception::class)
    fun decrypt(input: ByteArray, seed: ByteArray): String {
        val key = getRawKey(seed)
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
    @Throws(Exception::class)
    fun decrypt(input: String, seed: ByteArray): String {
        val key = getRawKey(seed)
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
    @Throws(Exception::class)
    fun decrypt(input: ByteArray, seed: String): String {
        val key = getRawKey(seed.toByteArray())
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
    @Throws(Exception::class)
    fun decrypt(input: String, seed: String): String {
        val key = getRawKey(seed.toByteArray())
        val encrypted = Base64.decode(input)
        val result = toDecrypt(key, encrypted)
        return String(result)
    }

    @Throws(Exception::class)
    private fun getRawKey(seed: ByteArray): ByteArray {
        val kGen = KeyGenerator.getInstance(ALGORITHM)
        val sr = SecureRandom(seed)
        kGen.init(128, sr)
        val key = kGen.generateKey()
        return key.encoded
    }

    @Throws(Exception::class)
    private fun toEncrypt(key: ByteArray, input: ByteArray): ByteArray {
        val sKeySpec = SecretKeySpec(key, ALGORITHM)
        val cipher = Cipher.getInstance(ALGORITHM)
        cipher.init(Cipher.ENCRYPT_MODE, sKeySpec)
        return cipher.doFinal(input)
    }

    @Throws(Exception::class)
    private fun toDecrypt(key: ByteArray, encrypted: ByteArray): ByteArray {
        val sKeySpec = SecretKeySpec(key, ALGORITHM)
        val cipher = Cipher.getInstance(ALGORITHM)
        cipher.init(Cipher.DECRYPT_MODE, sKeySpec)
        return cipher.doFinal(encrypted)
    }
}
