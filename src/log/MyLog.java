package log;

import cmd.MyCmd;

/**
 * @author 22454
 */
public final class MyLog {
    public static void info(final String msg) {
        String realMsg = "[INFO] " + msg;
        System.out.println(realMsg);
    }

    public static void debug(final String msg) {
        String realMsg = "[DEBUG] " + msg;
        System.out.println(realMsg);
    }

    public static void warn(final String msg) {
        String realMsg = "[WARN] " + msg;
        System.out.println(realMsg);
    }

    public static void error(final String msg) {
        String realMsg = "[ERROR] " + msg;
        System.out.println(realMsg);
    }

    public static void success(final String msg) {
        String realMsg = "[SUCCESS] " + msg;
        System.out.println(realMsg);
    }

    public static void command(final String msg) {
        String realMsg = "[COMMAND] " + msg;
        System.out.println(realMsg);
    }
}
