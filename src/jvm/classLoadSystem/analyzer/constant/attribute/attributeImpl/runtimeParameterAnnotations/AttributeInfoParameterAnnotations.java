package jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.runtimeParameterAnnotations;

import jvm.classLoadSystem.analyzer.ByteCodeFile;
import jvm.classLoadSystem.analyzer.constant.ConstantPool;
import jvm.classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.ParameterAnnotations;

/**
 * @author 22454
 */
public abstract class AttributeInfoParameterAnnotations implements AttributeInfo {
    private int numParameters;
    private ParameterAnnotations[] parameterAnnotations;

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        this.numParameters = byteCodeFile.readOneUint();
        this.parameterAnnotations = new ParameterAnnotations[numParameters];
        for (int i = 0; i < numParameters; i++) {
            parameterAnnotations[i] = new ParameterAnnotations(byteCodeFile);
        }
    }
}
