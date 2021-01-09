package jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.runtimeParameterAnnotations;

import jvm.classLoadSystem.analyzer.ByteCodeFile;
import jvm.classLoadSystem.analyzer.constant.ConstantPool;
import jvm.classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.ParameterAnnotations;

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
