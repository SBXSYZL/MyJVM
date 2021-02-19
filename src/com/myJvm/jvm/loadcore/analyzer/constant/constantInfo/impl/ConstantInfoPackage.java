package com.myJvm.jvm.loadcore.analyzer.constant.constantInfo.impl;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;
import com.myJvm.jvm.loadcore.analyzer.constant.ConstantPool;
import com.myJvm.jvm.loadcore.analyzer.constant.constantInfo.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoPackage implements ConstantInfo {
    private static final Integer TAG = PACKAGE_TAG;
    private int nameIndex;
    private ConstantPool constantPool;

    @Override
    public void setInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) {
        this.nameIndex = byteCodeFile.readTwoUint();
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
        return "ConstantInfoPackage{" +
                "nameIndex=" + nameIndex +
                ", constantPool=" + constantPool +
                '}';
    }
}
