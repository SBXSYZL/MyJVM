package com.myJvm.jvm.loadcore.analyzer.constant.constantInfo.impl;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;
import com.myJvm.jvm.loadcore.analyzer.constant.ConstantPool;
import com.myJvm.jvm.loadcore.analyzer.constant.constantInfo.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoLong implements ConstantInfo {
    private static final Integer TAG = LONG_TAG;
    private Long bytes;
    private ConstantPool constantPool;

    @Override
    public void setInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) {
        this.bytes = byteCodeFile.readLong();
        this.constantPool = constantPool;
    }

    @Override
    public int numOfIndex() {
        return 0;
    }

    public long getLong() {
        return bytes;
    }

    @Override
    public int getTag() {
        return TAG;
    }

    @Override
    public String toString() {
        return "ConstantInfoLong{" +
                "bytes=" + bytes +
                ", constantPool=" + constantPool +
                '}';
    }
}
