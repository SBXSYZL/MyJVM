package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.runtimeParameterAnnotations;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;
import com.myJvm.jvm.loadcore.analyzer.constant.ConstantPool;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.AttributeInfo;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.ParameterAnnotations;

/**
 * @author 22454
 */
public abstract class AttributeInfoParameterAnnotations extends AttributeInfo {
    private int numParameters;
    private ParameterAnnotations[] parameterAnnotations;

    public AttributeInfoParameterAnnotations(int attributeNameIndex, int attributeLength) {
        super(attributeNameIndex, attributeLength);
    }

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, int attributeLength, ConstantPool constantPool) throws Exception {
        this.numParameters = byteCodeFile.readOneUint();
        this.parameterAnnotations = new ParameterAnnotations[numParameters];
        for (int i = 0; i < numParameters; i++) {
            parameterAnnotations[i] = new ParameterAnnotations(byteCodeFile);
        }
    }
}
