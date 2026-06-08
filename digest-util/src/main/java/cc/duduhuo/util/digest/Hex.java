/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.digest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class Hex {
    private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final char[] DIGITS_UPPER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * Encodes the given byte array to a char array.
     *
     * @param data      Data to convert to hex representation
     * @param upperCase Hex string with capital letters
     * @return a char array
     */
    public static char[] encodeHex(final byte[] data, final boolean upperCase) {
        int l = data.length;
        char[] out = new char[l << 1]; // l shl 1 等效于 l * 2
        int i = 0;
        int j = 0;
        char[] digitsArray = upperCase ? DIGITS_UPPER : DIGITS_LOWER;

        while (i < l) {
            // Kotlin: (0xF0 and this[i].toInt()).ushr(4)
            out[j++] = digitsArray[(0xF0 & data[i]) >>> 4];

            // Kotlin: 0x0F and this[i].toInt()
            out[j++] = digitsArray[0x0F & data[i]];
            i++;
        }
        return out;
    }

    public static char[] encodeHex(final byte[] data) {
        return encodeHex(data, false);
    }

    /**
     * Encodes the given byte array as a hex dump.
     *
     * @param data      Data to convert to hex representation
     * @param upperCase Hex string with capital letters
     * @return a hex string
     */
    public static String hex(final byte[] data, final boolean upperCase) {
        return new String(encodeHex(data, upperCase));
    }

    /**
     * Encodes the given byte array as a hex dump.
     *
     * @param data Data to convert to hex representation
     * @return a hex string
     */
    public static String hex(final byte[] data) {
        return hex(data, false);
    }

    /**
     * Encodes the given file content as a hex dump.
     *
     * @param data      Data to convert to hex representation
     * @param upperCase Hex string with capital letters
     * @return a hex string
     * @throws IOException if an I/O error occurs
     */
    public static String hex(final Path data, final boolean upperCase) throws IOException {
        return hex(Files.readAllBytes(data), upperCase);
    }

    /**
     * Encodes the given file content as a hex dump.
     *
     * @param data Data to convert to hex representation
     * @return a hex string
     * @throws IOException if an I/O error occurs
     */
    public static String hex(final Path data) throws IOException {
        return hex(data, false);
    }

    /**
     * Encodes the given file content as a hex dump.
     *
     * @param data      Data to convert to hex representation
     * @param upperCase Hex string with capital letters
     * @return a hex string
     * @throws IOException if an I/O error occurs
     */
    public static String hex(final File data, final boolean upperCase) throws IOException {
        return hex(data.toPath(), upperCase);
    }

    /**
     * Encodes the given file content as a hex dump.
     *
     * @param data Data to convert to hex representation
     * @return a hex string
     * @throws IOException if an I/O error occurs
     */
    public static String hex(final File data) throws IOException {
        return hex(data, false);
    }

    private Hex() {
        // don't instantiate
    }
}
