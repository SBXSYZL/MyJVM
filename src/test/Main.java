package test;

import jvm.runtimeDataArea.threadDependent.OperandStack;

/**
 * @author 22454
 */
public class Main {


    public static void main(String[] args) {
        final int[] a = {10};
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                a[0]++;
            }
        });
        System.out.println(a[0]);
    }
}
