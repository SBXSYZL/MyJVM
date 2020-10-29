import classLoadSystem.analyzer.constant.ConstantInfo;
import classLoadSystem.analyzer.constant.constantInfoImpl.ConstantInfoClass;
import cmd.MyCmd;

/**
 * @author 22454
 */
public class Main {
    public native void test();

    public static void main(String[] args) throws Exception {
        ConstantInfoClass constantInfoClass = new ConstantInfoClass("");
        new ConstantInfoClass("");
        new ConstantInfoClass("");
        System.out.println(((ConstantInfo) constantInfoClass).getTag());

        new MyCmd();
    }
}
