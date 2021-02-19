package com.myJvm.jvm.loadcore.analyzer.constant.constantInfo.impl;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;
import com.myJvm.jvm.loadcore.analyzer.constant.ConstantPool;
import com.myJvm.jvm.loadcore.analyzer.constant.constantInfo.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoMethodHandle implements ConstantInfo {
    private static final Integer TAG = METHOD_HANDLE_TAG;
    /**
     * 取值范围 [1,9]
     */
    private int referenceKind;

    /**
     * 常量池索引
     */
    private int referenceIndex;
    private ConstantPool constantPool;

    @Override
    public void setInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) {
        this.referenceKind = byteCodeFile.readOneUint();
        this.referenceIndex = byteCodeFile.readTwoUint();
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
        return "ConstantInfoMethodHandle{" +
                "referenceKind=" + referenceKind +
                ", referenceIndex=" + referenceIndex +
                ", constantPool=" + constantPool +
                '}';
    }
}
