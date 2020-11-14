package classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.typeAnnotationDependent.targetInfo;

import classLoadSystem.analyzer.ByteCodeFile;

/**
 * @author 22454
 */
public class FormalParameterTarget implements TargetInfo {
    /**
     * U1
     */
    private int formalParameterIndex;

    public FormalParameterTarget(ByteCodeFile byteCodeFile) {
        this.formalParameterIndex = byteCodeFile.readOneUint();
    }
}
