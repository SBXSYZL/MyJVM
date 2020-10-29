package cmd;

import classLoadSystem.Loader;
import classLoadSystem.loaderImpl.MyLoader;
import log.MyLog;

import java.util.StringJoiner;

/**
 * @author 22454
 */
public class MyCmd {
    private final CmdParser cmdParser;
    private static CmdPanel cmdPanel;
    private final Loader loader;

    public MyCmd() throws Exception {
        MyLog.info("My Java Virtual Machine Start...");
        MyLog.info("Loading Cmd Panel...");
        cmdPanel = new CmdPanel();
        MyLog.success("Cmd Panel Load Successfully.");
        MyLog.info("Loading Cmd Parser...");
        cmdParser = new CmdParser();
        cmdPanel.setParser(this::parse);
        MyLog.success("Cmd Parser Load Successfully.");
        MyLog.success("My Java Virtual Machine Start Successfully.");
        MyLog.info("Class Loader Start...");
        loader = new MyLoader();
        MyLog.success("Class Loader Start Successfully.");
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MyCmd.class.getSimpleName() + "[", "]")
                .add("cmdParser=" + cmdParser)
                .add("cmdPanel=" + cmdPanel)
                .toString();
    }

    public static void print(String str) {
        if (cmdPanel != null) {
            cmdPanel.print(str);
        } else {
            System.out.println(str);
        }
    }

    public static void printAndAdjust(String str) {
        if (cmdPanel != null) {
            cmdPanel.printAndAdjust(str);
        } else {
            System.out.println(str);
        }
    }

    private void parse(String str) throws Exception {
        cmdParser.parseCommand(str);
    }

    public static void history() {
        printAndAdjust(cmdPanel.historyString());
    }

    public static void clear() {
        cmdPanel.clear();
    }
}
