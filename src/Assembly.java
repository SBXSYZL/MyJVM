/**
 * @author 22454
 */
public class Assembly {
    static {
        System.loadLibrary(Assembly.class.getName());
    }

    public static native int iAdd(int numOne, int numTwo);

    public static native int iSub(int numOne, int numTwo);

    public static native int iMul(int numOne, int numTwo);

    public static native int iDiv(int numOne, int numTwo);
}
