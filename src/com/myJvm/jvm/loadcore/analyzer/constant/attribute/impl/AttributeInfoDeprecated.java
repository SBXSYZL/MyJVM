package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;
import com.myJvm.jvm.loadcore.analyzer.constant.ConstantPool;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.AttributeInfo;

/**
 * @author 22454
 */
public class AttributeInfoDeprecated extends AttributeInfo {
    private int attributeNameIndex;
    private int attributeLength;
    private ConstantPool constantPool;

    public AttributeInfoDeprecated(int attributeNameIndex, int attributeLength) {
        super(attributeNameIndex, attributeLength);
    }


    @Override
    public void readInfo(ByteCodeFile byteCodeFile, int attributeLength, ConstantPool constantPool) throws Exception {
        this.constantPool = constantPool;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }
}
