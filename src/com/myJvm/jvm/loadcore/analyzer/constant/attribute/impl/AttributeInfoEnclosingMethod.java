package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;
import com.myJvm.jvm.loadcore.analyzer.constant.ConstantPool;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.AttributeInfo;

/**
 * @author 22454
 */
public class AttributeInfoEnclosingMethod extends AttributeInfo {
    private int classIndex;
    private int methodIndex;
    private ConstantPool constantPool;

    public AttributeInfoEnclosingMethod(int attributeNameIndex, int attributeLength) {
        super(attributeNameIndex, attributeLength);
    }

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, int attributeLength, ConstantPool constantPool) throws Exception {
        this.classIndex = byteCodeFile.readTwoUint();
        this.methodIndex = byteCodeFile.readTwoUint();
        this.constantPool = constantPool;
    }

    public int getClassIndex() {
        return classIndex;
    }

    public int getMethodIndex() {
        return methodIndex;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    @Override
    public String toString() {
        try {
            StringBuilder builder = new StringBuilder();
            builder.append("Class Index: ").append(classIndex).append("   --->    ").append(constantPool.getClassName(classIndex)).append("\n")
                    .append("Method Index: ").append(methodIndex).append("\n");
            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
