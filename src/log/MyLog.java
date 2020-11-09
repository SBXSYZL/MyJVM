package log;

import cmd.MyCmd;
import com.sun.javafx.binding.StringFormatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * @author 22454
 */
public final class MyLog {
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss  ");
    private static final String INFO_LABEL = "\033[32m INFO \033[m";
    private static final String DEBUG_LABEL = "\033[36m DEBUG \033[m";
    private static final String WARN_LABEL = "\033[37m WARN \033[m";
    private static final String ERROR_LABEL = "\033[31m ERROR \033[m";
    private static final String SUCCESS_LABEL = "\033[32m SUCCESS \033[m";
    private static final String COMMAND_LABEL = "\033[29m COMMAND \033[m";
    private static final int MAX_LABEL_LENGTH = 20;
    private static final int MAX_THREAD_NAME_LENGTH = 10;

    public synchronized static void info(final String msg) {
        String realMsg = formatMsg(msg, INFO_LABEL);
        System.out.println(realMsg);
    }

    public synchronized static void debug(final String msg) {
        String realMsg = formatMsg(msg, DEBUG_LABEL);
        System.out.println(realMsg);
    }

    public synchronized static void warn(final String msg) {
        String realMsg = formatMsg(msg, WARN_LABEL);
        System.out.println(realMsg);
    }

    public synchronized static void error(final String msg) {
        String realMsg = formatMsg(msg, ERROR_LABEL);
        System.out.println(realMsg);
    }

    public synchronized static void success(final String msg) {

        String realMsg = formatMsg(msg, SUCCESS_LABEL);
        System.out.println(realMsg);
    }

    public synchronized static void command(final String msg) {

        String realMsg = formatMsg(msg, COMMAND_LABEL);
        System.out.println(realMsg);
    }

    private synchronized static String formatMsg(final String msg, final String label) {

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

    private synchronized static String currentTime() {
        return dateTimeFormatter.format(LocalDateTime.now());
    }
}
