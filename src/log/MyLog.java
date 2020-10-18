package log;

/**
 * @author 22454
 */
public final class MyLog {
    public static void info(final String msg) {
        System.out.println("[INFO] " + msg);
    }

    public static void debug(final String msg) {
        System.out.println("[DEBUG] " + msg);
    }

    public static void warn(final String msg) {
        System.out.println("[WARN] " + msg);
    }

    public static void error(final String msg) {
        System.out.println("[ERROR] " + msg);
    }

    public static void success(final String msg) {
        System.out.println("[SUCCESS] " + msg);
    }

    public static void command(final String msg) {
        System.out.println("[COMMAND] " + msg);
    }
}
