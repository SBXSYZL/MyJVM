package com.myJvm.exception;

/**
 * @author 22454
 */

public enum EmCommandErr implements CommonError {
    /**
     *
     */
    UNKNOWN_ERROR(1, "Unknown Command Error"),
    PARSE_COMMAND_ERROR(1001, "Command Parse Error"),
    SYSTEM_COMMAND_ERROR(1003, "System command error"),

    HAS_NOT_PARSER_LOADED(1002, "Has Not Parser Loaded");


    private final int errCode;
    private String msg;

    EmCommandErr(int errCode, String msg) {
        this.errCode = errCode;
        this.msg = msg;
    }

    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.msg;
    }

    @Override
    public void setErrMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "EmCommandErr{" +
                "errCode=" + errCode +
                ", msg='" + msg + '\'' +
                '}';
    }
}
