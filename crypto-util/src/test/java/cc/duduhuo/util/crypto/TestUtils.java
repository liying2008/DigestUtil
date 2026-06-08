/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

package cc.duduhuo.util.crypto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class TestUtils {
    @Test
    public void testTruncateOrPad() {
        byte[] result1 = Utils.truncateOrPad("1234567890".getBytes(), 5, (byte) '0');
        System.out.println("result1=" + Arrays.toString(result1));
        Assertions.assertArrayEquals(new byte[]{49, 50, 51, 52, 53}, result1);

        byte[] result2 = Utils.truncateOrPad("1234567890".getBytes(), 10, (byte) '0');
        System.out.println("result1=" + Arrays.toString(result2));
        Assertions.assertArrayEquals(new byte[]{49, 50, 51, 52, 53, 54, 55, 56, 57, 48}, result2);

        byte[] result3 = Utils.truncateOrPad("123".getBytes(), 5, (byte) '0');
        System.out.println("result1=" + Arrays.toString(result3));
        Assertions.assertArrayEquals(new byte[]{49, 50, 51, 48, 48}, result3);

        byte[] result4 = Utils.truncateOrPad("123".getBytes(), 5, (byte) 0);
        System.out.println("result1=" + Arrays.toString(result4));
        Assertions.assertArrayEquals(new byte[]{49, 50, 51, 0, 0}, result4);
    }
}
