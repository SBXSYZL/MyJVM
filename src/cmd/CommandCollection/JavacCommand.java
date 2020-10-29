package cmd.CommandCollection;

import cmd.MyCmd;

/**
 * @author 22454
 */
public class JavacCommand extends Command {

    @Override
    public String getPrefix() {
        return prefix;
    }

    @Override
    public void exec(String[] commandSection) throws Exception {
        throw new Exception(getUsage());

    }

    @Override
    protected void parse(String[] commandSection) {

    }

    @Override
    public String getUsage() {
        return "用法: javac <options> <source files>\n" +
                "其中, 可能的选项包括:\n" +
                "  -g                         生成所有调试信息\n" +
                "  -g:none                    不生成任何调试信息\n" +
                "  -g:{lines,vars,source}     只生成某些调试信息\n" +
                "  -nowarn                    不生成任何警告\n" +
                "  -verbose                   输出有关编译器正在执行的操作的消息\n" +
                "  -deprecation               输出使用已过时的 API 的源位置\n" +
                "  -classpath <路径>            指定查找用户类文件和注释处理程序的位置\n" +
                "  -cp <路径>                   指定查找用户类文件和注释处理程序的位置\n" +
                "  -sourcepath <路径>           指定查找输入源文件的位置\n" +
                "  -bootclasspath <路径>        覆盖引导类文件的位置\n" +
                "  -extdirs <目录>              覆盖所安装扩展的位置\n" +
                "  -endorseddirs <目录>         覆盖签名的标准路径的位置\n" +
                "  -proc:{none,only}          控制是否执行注释处理和/或编译。\n" +
                "  -processor <class1>[,<class2>,<class3>...] 要运行的注释处理程序的名称; 绕过默认的搜索进程\n" +
                "  -processorpath <路径>        指定查找注释处理程序的位置\n" +
                "  -parameters                生成元数据以用于方法参数的反射\n" +
                "  -d <目录>                    指定放置生成的类文件的位置\n" +
                "  -s <目录>                    指定放置生成的源文件的位置\n" +
                "  -h <目录>                    指定放置生成的本机标头文件的位置\n" +
                "  -implicit:{none,class}     指定是否为隐式引用文件生成类文件\n" +
                "  -encoding <编码>             指定源文件使用的字符编码\n" +
                "  -source <发行版>              提供与指定发行版的源兼容性\n" +
                "  -target <发行版>              生成特定 VM 版本的类文件\n" +
                "  -profile <配置文件>            请确保使用的 API 在指定的配置文件中可用\n" +
                "  -version                   版本信息\n" +
                "  -help                      输出标准选项的提要\n" +
                "  -A关键字[=值]                  传递给注释处理程序的选项\n" +
                "  -X                         输出非标准选项的提要\n" +
                "  -J<标记>                     直接将 <标记> 传递给运行时系统\n" +
                "  -Werror                    出现警告时终止编译\n" +
                "  @<文件名>                     从文件读取选项和文件名";
    }
}
