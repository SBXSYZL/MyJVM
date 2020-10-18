package cmd.CommandCollection;

import cmd.CmdConstant;
import cmd.MyCmd;
import exception.EmBusinessErr;
import log.MyLog;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author 22454
 */
public class JavaCommand extends Command {

    @Override
    public String getPrefix() {
        return prefix;
    }

    @Override
    public void exec(String[] commandSection, MyCmd cmd) throws Exception {
        parse(commandSection);
        if (options.size() <= 0 && args.size() <= 0) {
            MyLog.error(EmBusinessErr.PARSE_COMMAND_ERROR.getErrMsg());
            throw new Exception(getUsage());
        }
        options.forEach(option -> {
            switch (option) {
                case CmdConstant.VERSION:
                    if (options.size() > 1) {
                        cmd.print(EmBusinessErr.PARSE_COMMAND_ERROR.getErrMsg());
                    } else {
                        version(cmd);
                    }
                    break;
                case "-jar":
                    execJar(cmd);
                    break;
                case "-help":
                    cmd.printAndAdjust(getUsage());
                    break;
                default:
                    cmd.print(EmBusinessErr.UNKNOWN_ERROR.getErrMsg());
                    cmd.printAndAdjust(getUsage());
                    break;
            }
        });

    }

    @Override
    protected void parse(String[] commandSection) {
        options = new ArrayList<>();
        args = new ArrayList<>();
        for (int i = 1; i < commandSection.length; i++) {
            if (commandSection[i].startsWith(CmdConstant.OPTION_PREFIX)) {
                options.add(commandSection[i]);
            } else {
                args.add(commandSection[i]);
            }
        }
        System.out.println("options: " + Arrays.toString(options.toArray()));
        System.out.println("args: " + Arrays.toString(args.toArray()));
    }

    private void version(MyCmd cmd) {
        cmd.printAndAdjust(CmdConstant.VERSION_INFO);
    }

    private void execJar(MyCmd cmd) {
        cmd.print("jar start...");
        cmd.printAndAdjust("jar exec successfully.");
    }

    @Override
    public String getUsage() {
        return "用法:  java [-options] class [args...]\n" +
                "           或  java [-options] -jar jarfile [args...]\n" +
                "           -version 查看版本信息\n" +
                "           -help 查看使用方法";
    }
}
