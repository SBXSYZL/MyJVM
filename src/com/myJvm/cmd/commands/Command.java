package com.myJvm.cmd.commands;

import java.util.List;

/**
 * @author 22454
 */
public abstract class Command {
    protected String prefix = null;
    protected List<String> args;
    protected List<String> options;

    public Command() {
        String className = this.getClass().getName();
        String pag = this.getClass().getPackage().getName();
        prefix = className.substring(pag.length() + 1, className.length() - 7).toLowerCase();
    }

    /**
     * 获取命令前缀
     *
     * @return 前缀
     */
    public abstract String getPrefix();

    /**
     * 执行
     *
     * @param commandSection 命令+参数段
     * @throws
     */
    public abstract void exec(String[] commandSection) throws Exception;

    /**
     * 获取命令的执行方法
     *
     * @return 执行方法
     */
    public abstract String getUsage();

    /**
     * 解析参数
     */
    protected abstract void parse(String[] commandSection) throws Exception;
}
