package com.myJvm.jvm.loadcore.analyzer.constant.constantInfo.impl;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;
import com.myJvm.jvm.loadcore.analyzer.constant.ConstantPool;
import com.myJvm.jvm.loadcore.analyzer.constant.constantInfo.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoInteger implements ConstantInfo {
    private static final Integer TAG = INTEGER_TAG;
    private int bytes;
    private ConstantPool constantPool;

    @Override
    public void setInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) {
        this.bytes = byteCodeFile.readInteger();
        this.constantPool = constantPool;
    }

    public Integer getInteger() {
        return bytes;
    }

    @Override
    public int numOfIndex() {
        return 0;
    }

    @Override
    public int getTag() {
        return TAG;
    }

    @Override
    public String toString() {
        return "ConstantInfoInteger{" +
                "bytes=" + bytes +
                ", constantPool=" + constantPool +
                '}';
    }
}
