package jvm.interpreter.assembly;

/**
 * 运算指令
 *
 * @author 22454
 */
public class MathInstruction {
    public static native int iAdd(int numOne, int numTwo);

    public static native long lAdd(long numOne, long numTwo);

    public static native float fAdd(float numOne, float numTwo);

    public static native double dAdd(double numOne, double numTwo);
}
