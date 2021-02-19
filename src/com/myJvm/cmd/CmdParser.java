package com.myJvm.cmd;

import com.myJvm.cmd.commands.Command;
import com.myJvm.cmd.commands.CommandLoader;
import com.myJvm.exception.EmCommandErr;
import com.myJvm.exception.JvmException;
import com.myJvm.log.MyLog;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 22454
 */
public class CmdParser implements Parser {
    private static ConcurrentHashMap<String, Command> KEY_WORD_MAP = null;
    private static final CommandLoader COMMAND_LOADER = new CommandLoader();

    public CmdParser() {
        try {
            KEY_WORD_MAP = COMMAND_LOADER.getClassHashMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean parse(String commandStr) throws Exception {
        commandStr = commandStr.trim();
        if (commandStr.length() == 0) {
            return false;
        }
        String[] commandSections = commandStr.split(" ");
        if (commandSections.length == 0) {
            return false;
        }
        Command cls = KEY_WORD_MAP.get(commandSections[0]);
        if (cls == null) {
            cls = KEY_WORD_MAP.get("system");
        }

        if (cls != null) {
            System.out.println(cls.getClass().getName() + "  ");
            cls.exec(commandSections);

        } else {
            MyLog.error(EmCommandErr.UNKNOWN_ERROR.getErrMsg());
            throw new JvmException(EmCommandErr.UNKNOWN_ERROR);
        }
        return true;
    }


}
