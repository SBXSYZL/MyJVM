package com.myJvm.jvm.loadcore.analyzer.constant.constantInfo.impl;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;
import com.myJvm.jvm.loadcore.analyzer.constant.ConstantPool;
import com.myJvm.jvm.loadcore.analyzer.constant.constantInfo.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoNameAndType implements ConstantInfo {
    private static final Integer TAG = NAME_AND_TYPE_TAG;

    private int funcNameIndex;
    private int funcDescIndex;
    private ConstantPool constantPool;

    @Override
    public void setInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) {
        this.funcNameIndex = byteCodeFile.readTwoUint();
        this.funcDescIndex = byteCodeFile.readTwoUint();
        this.constantPool = constantPool;
    }

    @Override
    public int numOfIndex() {
        return 2;
    }

    @Override
    public int getTag() {
        return TAG;
    }

    public int getFuncNameIndex() {
        return funcNameIndex;
    }

    public int getFuncDescIndex() {
        return funcDescIndex;
    }

    @Override
    public String toString() {
        return "ConstantInfoNameAndType{" +
                "funcNameIndex=" + funcNameIndex +
                ", funcDescIndex=" + funcDescIndex +
                ", constantPool=" + constantPool +
                '}';
    }
}
