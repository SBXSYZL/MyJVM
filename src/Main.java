import cmd.MyCmd;
import log.MyLog;

/**
 * @author 22454
 */
public class Main {
    public native void test();

    public static void main(String[] args) throws Exception {
        /*
         * 开启日志 start
         *  默认不开启日志，需要手动开启
//         * */
        MyLog.openLog();
        MyLog.openWarnLog();
        MyLog.openErrorLog();
        MyLog.openDebugLog();
        MyLog.openPrintLog();
        MyLog.openSuccess();
        /*
         * 开启日志 end
         * */
        MyCmd myCmd = new MyCmd();
        myCmd.run();
    }
}
