package com.myJvm.utils;

/**
 * @author 22454
 */
public class CheckUtil {

    public static void checkIndex(int arrayLength, int index) {
        if (index < 0 || index > arrayLength) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
