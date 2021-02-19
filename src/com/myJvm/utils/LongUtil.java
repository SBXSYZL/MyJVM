package com.myJvm.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 22454
 */
public class LongUtil {
    public static final String HIGH_32_BIT_INTEGER = "high_32_bit_integer";
    public static final String LOW_32_BIT_INTEGER = "low_32_bit_integer";
    private static final long TWO_TO_THE_31ND_POWER_MINUS_1 = 4294967295L;

    public static Map<String, Integer> splitLongIntoTwoIntegers(long value) {
        int high = (int) (value >> 32);
        int low = (int) value;
        HashMap<String, Integer> map = new HashMap<>(2);
        map.put(HIGH_32_BIT_INTEGER, high);
        map.put(LOW_32_BIT_INTEGER, low);
        return map;
    }

    public static long twoIntegersAreRestoredToLong(int high, int low) {
        long realHigh = high;
        realHigh <<= 32;
        long realLow = low;
        //直接干掉前面32位,原因是，
        // 如果这个数是负数，他的转换规则是前面的32位全部补1，
        // 如果是正数，全部补0
        // ，因此只要把前面的32位直接干掉，就可以完美解决问题了
        realLow &= TWO_TO_THE_31ND_POWER_MINUS_1;
        return realHigh | realLow;
    }
//    public static class

}
