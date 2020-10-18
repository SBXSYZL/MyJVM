package cmd.CommandCollection;

import cmd.MyCmd;
import exception.EmBusinessErr;

/**
 * @author 22454
 */
public class SystemCommand extends Command {
    @Override
    public String getPrefix() {
        return prefix;
    }

    @Override
    public void exec(String[] commandSection, MyCmd cmd) throws Exception {
        parse(commandSection);
        String command = commandSection[0];
        switch (command) {
            case "history":
                cmd.history();
                break;
            case "clear":
                cmd.clear();
                break;
            default:
                throw new Exception(EmBusinessErr.UNKNOWN_ERROR.getErrMsg());
        }
    }

    @Override
    public String getUsage() {
        return null;
    }

    @Override
    protected void parse(String[] commandSection) throws Exception {
        if (commandSection.length > 1) {
            throw new Exception(EmBusinessErr.SYSTEM_COMMAND_ERROR.getErrMsg());
        }
    }
}
