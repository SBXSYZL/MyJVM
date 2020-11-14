package classLoadSystem.analyzer.constant.attribute.attributeImpl.runtimeParameterAnnotations;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.ParameterAnnotations;

/**
 * @author 22454
 */
public class AttributeInfoParameterAnnotations implements AttributeInfo {
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
