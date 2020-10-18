package cmd;

import cmd.CommandCollection.Command;
import cmd.CommandCollection.CommandLoader;
import exception.CmdException;
import exception.EmBusinessErr;
import log.MyLog;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 22454
 */
public class CmdParser {
    private static ConcurrentHashMap<String, Command> KEY_WORD_MAP = null;
    private static final CommandLoader COMMAND_LOADER = new CommandLoader();

    public CmdParser() {
        try {
            KEY_WORD_MAP = COMMAND_LOADER.getClassHashMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void parseCommand(String commandStr, MyCmd cmd) throws Exception {
        commandStr = commandStr.trim();
        if (commandStr.length() == 0) {
            return;
        }
        String[] commandSections = commandStr.split(" ");
        if (commandSections.length == 0) {
            return;
        }
        Command cls = KEY_WORD_MAP.get(commandSections[0]);
        if (cls == null) {
            cls = KEY_WORD_MAP.get("system");
        }

        if (cls != null) {
            System.out.println(cls.getClass().getName());
            cls.exec(commandSections, cmd);

        } else {
            MyLog.error(EmBusinessErr.UNKNOWN_ERROR.getErrMsg());
            throw new CmdException(EmBusinessErr.UNKNOWN_ERROR);
        }
    }


}
