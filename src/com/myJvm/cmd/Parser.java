package com.myJvm.cmd;

/**
 * @author 22454
 */
public interface Parser {
    /**
     * 解析
     *
     * @param str 字符串
     * @exception:可能异常
     */
    boolean parse(String str) throws Exception;
}
