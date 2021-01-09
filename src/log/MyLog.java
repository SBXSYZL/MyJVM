package log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author 22454
 */
public final class MyLog {
    private static boolean useLog = false;
    private static boolean useInfo = false;
    private static boolean useDebug = false;
    private static boolean useWarn = false;
    private static boolean useError = false;
    private static boolean useSuccess = false;
    private static boolean useCommand = false;
    private static boolean usePrint = false;
    private static boolean useNative = false;
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss  ");
    private static final String INFO_LABEL = "\033[32m INFO \033[m";
    private static final String DEBUG_LABEL = "\033[36m DEBUG \033[m";
    private static final String WARN_LABEL = "\033[37m WARN \033[m";
    private static final String ERROR_LABEL = "\033[31m ERROR \033[m";
    private static final String SUCCESS_LABEL = "\033[32m SUCCESS \033[m";
    private static final String COMMAND_LABEL = "\033[29m COMMAND \033[m";
    private static final String NATIVE_LABEL = "\033[29m NATIVE \033[m";
    private static final int MAX_LABEL_LENGTH = 20;
    private static final int MAX_THREAD_NAME_LENGTH = 10;


    public static void info(final String msg) {
        if (useLog && useInfo) {
            String realMsg = formatMsg(msg, INFO_LABEL);
            System.out.println(realMsg);
        }

    }

    public static void debug(final String msg) {
        if (useLog && useDebug) {
            String realMsg = formatMsg(msg, DEBUG_LABEL);
            System.out.println(realMsg);
        }
    }

    public static void warn(final String msg) {
        if (useLog && useWarn) {
            String realMsg = formatMsg(msg, WARN_LABEL);
            System.out.println(realMsg);
        }
    }

    public static void error(final String msg) {
        if (useLog && useError) {
            String realMsg = formatMsg(msg, ERROR_LABEL);
            System.out.println(realMsg);
        }
    }

    public static void success(final String msg) {
        if (useLog && useSuccess) {
            String realMsg = formatMsg(msg, SUCCESS_LABEL);
            System.out.println(realMsg);
        }
    }

    public static void command(final String msg) {
        if (useLog && useCommand) {
            String realMsg = formatMsg(msg, COMMAND_LABEL);
            System.out.println(realMsg);
        }
    }

    public static void nativeLog(final String msg) {
        if (useLog && useNative) {
            String realMsg = formatMsg(msg, NATIVE_LABEL);
            System.out.println(realMsg);
        }
    }

    public static void print(final String msg) {
        if (useLog && usePrint) {
            System.out.print(msg);
        }
    }

    private static String formatMsg(final String msg, final String label) {

        StringBuilder builder = new StringBuilder();
        Thread currentThread = Thread.currentThread();
        StackTraceElement[] stackTrace = currentThread.getStackTrace();
        builder.append(currentTime());

        if (label.length() < MAX_LABEL_LENGTH) {
            StringBuilder labelFormat = new StringBuilder();
            for (int i = 0; i < MAX_LABEL_LENGTH - label.length(); i++) {
                labelFormat.append(" ");
            }
            labelFormat.append(label);
            builder.append(labelFormat);
        } else {
            builder.append(label);
        }
        builder.append(" --- [ ");
        if (currentThread.getName().length() < MAX_THREAD_NAME_LENGTH) {
            StringBuilder threadNameFormat = new StringBuilder();
            for (int i = 0; i < MAX_THREAD_NAME_LENGTH - currentThread.getName().length(); i++) {
                threadNameFormat.append(" ");
            }
            threadNameFormat.append(currentThread.getName());
            builder.append(threadNameFormat);
        } else {
            builder.append(currentThread.getName());
        }
        builder.append(" ] ")
                .append(stackTrace[3].getClassName())
                .append("\t: ")
                .append(msg);
        return builder.toString();
    }

    private static String currentTime() {
        return dateTimeFormatter.format(LocalDateTime.now());
    }

    public static void openLog() {
        useLog = true;
    }

    public static void closeLog() {
        useLog = false;
    }

    public static void openInfoLog() {
        openLog();
        useInfo = true;
    }

    public static void closeInfoLog() {
        useInfo = false;
    }

    public static void openDebugLog() {
        openLog();
        useDebug = true;
    }

    public static void closeDebugLog() {
        useDebug = false;
    }

    public static void openWarnLog() {
        openLog();
        useWarn = true;
    }

    public static void closeWarnLog() {
        useWarn = false;
    }

    public static void openErrorLog() {
        openLog();
        useError = true;
    }

    public static void closeErrorLog() {
        useError = false;
    }

    public static void openSuccess() {
        openLog();
        useSuccess = true;
    }

    public static void closeSuccess() {
        useSuccess = false;
    }

    public static void openCommand() {
        openLog();
        useCommand = true;
    }

    public static void closeCommand() {
        useCommand = false;
    }

    public static void openPrintLog() {
        openLog();
        usePrint = true;
    }

    public static void closePrintLog() {
        usePrint = false;
    }

    public static void openNativeLog() {
        useNative = true;
    }

    public static void closeNativeLog() {
        useNative = false;
    }

    public static void openAllLog() {
        openLog();
        openInfoLog();
        openDebugLog();
        openWarnLog();
        openErrorLog();
        openSuccess();
        openCommand();
        openPrintLog();
        openNativeLog();
    }

    public static void closeAllLog() {
        closeInfoLog();
        closeDebugLog();
        closeWarnLog();
        closeErrorLog();
        closeSuccess();
        closeCommand();
        closePrintLog();
        closeLog();
        closeLog();
    }

}
