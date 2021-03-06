package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;
import com.myJvm.jvm.loadcore.analyzer.constant.ConstantPool;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.AttributeInfo;

/**
 * @author 22454
 */
public class AttributeInfoSourceFile extends AttributeInfo {
    private int sourceFileIndex;
    private ConstantPool constantPool;

    public AttributeInfoSourceFile(int attributeNameIndex, int attributeLength) {
        super(attributeNameIndex, attributeLength);
    }

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, int attributeLength, ConstantPool constantPool) throws Exception {
        this.constantPool = constantPool;
        this.sourceFileIndex = byteCodeFile.readTwoUint();
    }

    public String getSourceFileName() throws Exception {
        return constantPool.getUtf8(sourceFileIndex);
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    @Override
    public String toString() {
        try {
            return "SourceFileIndex: " + sourceFileIndex + "\t" + "   --->   " + constantPool.getUtf8(sourceFileIndex) + "\n";
        } catch (Exception e) {
            e.printStackTrace();
            return "\n";
        }
    }
}
