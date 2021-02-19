package com.myJvm.utils;

/**
 * @author 22454
 */
public class CompareUtil {
    public static boolean between(int left, int right, int current) {
        return current >= left && current <= right;
    }

    public static boolean existsIn(int[] array, int current) {
        for (int i : array) {
            if (i == current) {
                return true;
            }
        }
        return false;
    }


}
