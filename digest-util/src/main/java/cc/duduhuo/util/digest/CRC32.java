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

/**
 * A class that can be used to compute the CRC-32 of a data stream.
 */
public final class CRC32 {
    /**
     * Default charset is {@link StandardCharsets#UTF_8}.
     */
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    /**
     * Get CRC-32 value
     *
     * @param inputStream Data to calculate the CRC-32 value
     * @return CRC-32 value
     * @throws IOException if an I/O error occurs
     */
    public static String getValue(final InputStream inputStream) throws IOException {
        java.util.zip.CRC32 crc32 = new java.util.zip.CRC32();
        byte[] buffer = new byte[8192];
        var length = inputStream.read(buffer);
        while (length > -1) {
            crc32.update(buffer, 0, length);
            length = inputStream.read(buffer);
        }

        return Long.toHexString(crc32.getValue());
    }

    /**
     * Get CRC-32 value
     *
     * @param data Data to calculate the CRC-32 value
     * @return CRC-32 value
     */
    public static String getValue(final byte[] data) {
        java.util.zip.CRC32 crc32 = new java.util.zip.CRC32();
        crc32.update(data);
        return Long.toHexString(crc32.getValue());
    }

    /**
     * Get CRC-32 value
     *
     * @param data Data to calculate the CRC-32 value
     * @return CRC-32 value
     */
    public static String getValue(final String data) {
        return getValue(data.getBytes(DEFAULT_CHARSET));
    }

    /**
     * Get CRC-32 value
     *
     * @param data Data to calculate the CRC-32 value
     * @return CRC-32 value
     * @throws IOException if an I/O error occurs
     */
    public static String getValue(final File data) throws IOException {
        try (var inputStream = new FileInputStream(data)) {
            return getValue(inputStream);
        }
    }

    /**
     * Get CRC-32 value
     *
     * @param data Data to calculate the CRC-32 value
     * @return CRC-32 value
     * @throws IOException if an I/O error occurs
     */
    public static String getValue(final Path data) throws IOException {
        try (var inputStream = Files.newInputStream(data)) {
            return getValue(inputStream);
        }
    }

    private CRC32() {
        // don't instantiate
    }
}
