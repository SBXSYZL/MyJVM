package com.myJvm.jvm.loadcore.analyzer.constant.constantInfo.impl;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;
import com.myJvm.jvm.loadcore.analyzer.constant.ConstantPool;
import com.myJvm.jvm.loadcore.analyzer.constant.constantInfo.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoMethodType implements ConstantInfo {
    private static final Integer TAG = METHOD_TYPE_TAG;
    /**
     * 方法的描述符
     */
    private int descriptorIndex;
    private ConstantPool constantPool;

    @Override
    public void setInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) {
        this.descriptorIndex = byteCodeFile.readTwoUint();
        this.constantPool = constantPool;
    }

    @Override
    public int numOfIndex() {
        return 1;
    }

    @Override
    public int getTag() {
        return TAG;
    }

    @Override
    public String toString() {
        return "ConstantInfoMethodType{" +
                "descriptorIndex=" + descriptorIndex +
                ", constantPool=" + constantPool +
                '}';
    }
}
