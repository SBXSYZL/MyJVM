package com.myJvm.cmd;

import com.myJvm.log.MyLog;

import java.util.StringJoiner;

/**
 * @author 22454
 */
public class MyCmd {
    private Parser cmdParser;
    private final CmdPanel cmdPanel;
    private static final String MY_JVM =
            "\n\n" +
                    "   **************************************************************************************************\n" +
                    "   *         __      __       __     __    ________    __        __      __      __                 *\n" +
                    "   *        / /\\    /\\ \\      \\ \\   / /   |___   __|   \\ \\      / /     / /\\    /\\ \\                *\n" +
                    "   *       / /\\ \\  / /\\ \\      \\ \\ / /        | |       \\ \\    / /     / /\\ \\  / /\\ \\               *\n" +
                    "   *      / /  \\ \\/ /  \\ \\      |   |         | |        \\ \\  / /     / /  \\ \\/ /  \\ \\              *\n" +
                    "   *     / /    \\__/    \\ \\     |   |     ____| |         \\ \\/ /     / /    \\__/    \\ \\             *\n" +
                    "   *    /_/              \\_\\    |___|    |______|          \\__/     /_/              \\_\\            *\n" +
                    "   *       :: \033[32m My Jvm \033[m ::                                                 \033[32m (v1.0.0.RELEASE) \033[m        *\n" +
                    "   **************************************************************************************************\n";

    public MyCmd() {
        cmdPanel = new CmdPanel();
    }

    public void run() throws Exception {
        MyLog.info("My Java Virtual Machine Start...");
        //+ MY_JVM
        for (int i = 0; i < MY_JVM.length(); i++) {
            MyLog.print("" + MY_JVM.charAt(i));
//            sleep(1);
        }
        System.out.println();
        MyLog.info("Loading Cmd Panel...");

        MyLog.success("Cmd Panel Load Successfully.");
        MyLog.info("Loading Cmd Parser...");
        cmdParser = new CmdParser();
//        cmdPanel.setParser(this::parse);
        MyLog.success("Cmd Parser Load Successfully.");
        MyLog.success("My Java Virtual Machine Start Successfully.");

    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MyCmd.class.getSimpleName() + "[", "]")
                .add("cmdParser=" + cmdParser)
//                .add("cmdPanel=" + cmdPanel)
                .toString();
    }

    public static void print(String str) {
//        if (cmdPanel != null) {
//            cmdPanel.print(str);
//        } else {
//            System.out.println(str);
//        }
    }

    public static void printAndAdjust(String str) {
//        if (cmdPanel != null) {
//            cmdPanel.printAndAdjust(str);
//        } else {
//            System.out.println(str);
//        }
    }

    private boolean parse(String str) throws Exception {
        return cmdParser.parse(str);
    }

    public static void history() {
//        printAndAdjust(cmdPanel.historyString());
    }

    public static void clear() {
//        cmdPanel.clear();
    }
}
