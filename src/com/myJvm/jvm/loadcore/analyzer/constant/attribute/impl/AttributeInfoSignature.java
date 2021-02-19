package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;
import com.myJvm.jvm.loadcore.analyzer.constant.ConstantPool;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.AttributeInfo;

/**
 * @author 22454
 */
public class AttributeInfoSignature extends AttributeInfo {
    private int signatureIndex;
    private ConstantPool constantPool;

    public AttributeInfoSignature(int attributeNameIndex, int attributeLength) {
        super(attributeNameIndex, attributeLength);
    }

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, int attributeLength, ConstantPool constantPool) throws Exception {
        this.constantPool = constantPool;
        this.signatureIndex = byteCodeFile.readTwoUint();
    }

    public int getSignatureIndex() {
        return signatureIndex;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    @Override
    public String toString() {
        try {
            return "SignatureIndex: " + signatureIndex + "\t" + "   --->   " + constantPool.getUtf8(signatureIndex) + "\n";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
