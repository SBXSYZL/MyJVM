package test;

import jvm.runtimeDataArea.threadDependent.OperandStack;

/**
 * @author 22454
 */
public class Main {
    public static void main(String[] args) throws Exception {
        OperandStack stack = new OperandStack(10);
        String s = "" + (-((1L << 32) - 1));
//        System.out.println(s);
        long l = Long.parseLong(s);
//        System.out.println(l);
//
        stack.pushInteger(10);
        stack.pushLong(l);
        stack.pushLong(100000000000L);
        stack.pushDouble(3.1415926535);
        stack.pushBoolean(true);
        stack.pushFloat(3.203f);
////
        System.out.println(stack.popFloat());
        System.out.println(stack.popBoolean());
        System.out.println(stack.popDouble());
//
        System.out.println(stack.popLong());
        System.out.println(stack.popLong());
        System.out.println(stack.popInteger());
        System.out.println();
        System.out.println();
//        System.out.println(Integer.MAX_VALUE);
//        String s1 = "" + ((1 << 31) - 1);
//        int i = Integer.parseUnsignedInt(s1);
//        System.out.println(i);

//        System.out.println(Long.MAX_VALUE);
//        System.out.println(Long.MIN_VALUE);
//        System.out.println(l);

    }
}
