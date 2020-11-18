package cmd.CommandCollection;

import exception.EmCommandErr;

import static cmd.MyCmd.*;

/**
 * @author 22454
 */
public class SystemCommand extends Command {
    @Override
    public String getPrefix() {
        return prefix;
    }

    @Override
    public void exec(String[] commandSection) throws Exception {
        parse(commandSection);
        String command = commandSection[0];
        switch (command) {
            case "history":
                history();
                break;
            case "clear":
                clear();
                break;
            default:
                throw new Exception(EmCommandErr.UNKNOWN_ERROR.getErrMsg());
        }
    }

    @Override
    public String getUsage() {
        return null;
    }

    @Override
    protected void parse(String[] commandSection) throws Exception {
        if (commandSection.length > 1) {
            throw new Exception(EmCommandErr.SYSTEM_COMMAND_ERROR.getErrMsg());
        }
    }
}
