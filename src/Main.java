import cmd.MyCmd;

/**
 * @author 22454
 */
public class Main {
    public native void test();

    public static void main(String[] args) throws Exception {
        MyCmd myCmd = new MyCmd();
        myCmd.run();
    }
}
