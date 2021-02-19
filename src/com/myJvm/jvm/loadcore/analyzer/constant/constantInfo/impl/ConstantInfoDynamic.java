package com.myJvm.jvm.loadcore.analyzer.constant.constantInfo.impl;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;
import com.myJvm.jvm.loadcore.analyzer.constant.ConstantPool;
import com.myJvm.jvm.loadcore.analyzer.constant.constantInfo.ConstantInfo;

import java.util.Map;

/**
 * @author 22454
 */
public class ConstantInfoDynamic implements ConstantInfo {
    private static final Integer TAG = DYNAMIC_TAG;
    private int bootstrapMethodAttrIndex;
    private int nameAndTypeIndex;
    private ConstantPool constantPool;

    @Override
    public void setInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) {
        this.bootstrapMethodAttrIndex = byteCodeFile.readTwoUint();
        this.nameAndTypeIndex = byteCodeFile.readTwoUint();
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

//    public String getBootstrapMethodAttr() {
//        return constantPool;
//    }

    public Map<String, String> getNameAndType() throws Exception {
        return constantPool.getNameAndType(nameAndTypeIndex);
    }

    @Override
    public String toString() {
        return "ConstantInfoDynamic{" +
                "bootstrapMethodAttrIndex=" + bootstrapMethodAttrIndex +
                ", nameAndTypeIndex=" + nameAndTypeIndex +
                ", constantPool=" + constantPool +
                '}';
    }
}
