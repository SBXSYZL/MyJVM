package classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.typeAnnotationDependent.targetInfo;

import classLoadSystem.analyzer.ByteCodeFile;

/**
 * @author 22454
 */
public class TypeParameterTarget implements TargetInfo {
    /**
     * U1
     */
    private int typeParameterIndex;

    public TypeParameterTarget(ByteCodeFile byteCodeFile) {
        this.typeParameterIndex = byteCodeFile.readOneUint();
    }
}
