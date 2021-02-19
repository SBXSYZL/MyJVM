package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;
import com.myJvm.jvm.loadcore.analyzer.constant.ConstantPool;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.AttributeInfo;

/**
 * @author 22454
 */

public class AttributeInfoSourceDebugExtension extends AttributeInfo {
    private int[] debugExtension;
    private ConstantPool constantPool;

    public AttributeInfoSourceDebugExtension(int attributeNameIndex, int attributeLength) {
        super(attributeNameIndex, attributeLength);
    }

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, int attributeLength, ConstantPool constantPool) throws Exception {
        this.constantPool = constantPool;
        debugExtension = new int[attributeLength];
        for (int i = 0; i < attributeLength; i++) {
            debugExtension[i] = byteCodeFile.readOneUint();
        }
    }


    public int[] getDebugExtension() {
        return debugExtension;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }
}
