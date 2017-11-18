package cc.duduhuo.util.crypto

import cc.duduhuo.util.digest.Base64
import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.DESKeySpec


/**
 * =======================================================<br>
 * Author: liying - liruoer2008@yeah.net <br>
 * Datetime: 2017/11/6 22:10 <br>
 * Description: DES Tools <br>
 * Remarks: <br>
 * =======================================================<br>
 */
object DES {
    private const val ALGORITHM = "DES"
    private const val TRANSFORMATION = "DES"
    /**
     * Encrypts a password.
     * @param input The password to be encrypted.
     * @param key Creates a DESKeySpec object using the first 8 bytes in key as the key material for the DES key.
     * @return Encrypted password.
     */
    @JvmStatic
    @Throws(Exception::class)
    fun encrypt(input: ByteArray, key: ByteArray): String {
        val bt = toEncrypt(input, Arrays.copyOf(key, 16))
        return Base64.encode(bt)
    }

    /**
     * Encrypts a password.
     * @param input The password to be encrypted.
     * @param key Creates a DESKeySpec object using a string as the key material for the DES key.
     * @return Encrypted password.
     */
    @JvmStatic
    @Throws(Exception::class)
    fun encrypt(input: ByteArray, key: String): String {
        val bt = toEncrypt(input, Arrays.copyOf(key.toByteArray(), 16))
        return Base64.encode(bt)
    }

    /**
     * Encrypts a password.
     * @param input The password to be encrypted.
     * @param key Creates a DESKeySpec object using the first 8 bytes in key as the key material for the DES key.
     * @return Encrypted password.
     */
    @JvmStatic
    @Throws(Exception::class)
    fun encrypt(input: String, key: ByteArray): String {
        val bt = toEncrypt(input.toByteArray(), Arrays.copyOf(key, 16))
        return Base64.encode(bt)
    }

    /**
     * Encrypts a password.
     * @param input The password to be encrypted.
     * @param key Creates a DESKeySpec object using a string as the key material for the DES key.
     * @return Encrypted password.
     */
    @JvmStatic
    @Throws(Exception::class)
    fun encrypt(input: String, key: String): String {
        val bt = toEncrypt(input.toByteArray(), Arrays.copyOf(key.toByteArray(), 16))
        return Base64.encode(bt)
    }

    /**
     * Decrypt a password.
     * @param input The password to be decrypted.
     * @param key Creates a DESKeySpec object using the first 8 bytes in key as the key material for the DES key.
     * @return Decrypted password.
     */
    @JvmStatic
    @Throws(Exception::class)
    fun decrypt(input: String, key: ByteArray): String {
        val buf = Base64.decode(input)
        val bt = toDecrypt(buf, Arrays.copyOf(key, 16))
        return String(bt)
    }

    /**
     * Decrypt a password.
     * @param input The password to be decrypted.
     * @param key Creates a DESKeySpec object using a string as the key material for the DES key.
     * @return Decrypted password.
     */
    @JvmStatic
    @Throws(Exception::class)
    fun decrypt(input: String, key: String): String {
        val buf = Base64.decode(input)
        val bt = toDecrypt(buf, Arrays.copyOf(key.toByteArray(), 16))
        return String(bt)
    }

    @Throws(Exception::class)
    private fun toEncrypt(data: ByteArray, key: ByteArray): ByteArray {
        val dks = DESKeySpec(key)
        val keyFactory = SecretKeyFactory.getInstance(ALGORITHM)
        val sKey = keyFactory.generateSecret(dks)
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, sKey)
        return cipher.doFinal(data)
    }

    @Throws(Exception::class)
    private fun toDecrypt(data: ByteArray, key: ByteArray): ByteArray {
        val dks = DESKeySpec(key)
        val keyFactory = SecretKeyFactory.getInstance(ALGORITHM)
        val sKey = keyFactory.generateSecret(dks)
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.DECRYPT_MODE, sKey)
        return cipher.doFinal(data)
    }
}