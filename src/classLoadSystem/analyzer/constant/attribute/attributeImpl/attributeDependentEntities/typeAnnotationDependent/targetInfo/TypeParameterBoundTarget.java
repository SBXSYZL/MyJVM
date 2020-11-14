package classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.typeAnnotationDependent.targetInfo;

import classLoadSystem.analyzer.ByteCodeFile;

/**
 * @author 22454
 */
public class TypeParameterBoundTarget implements TargetInfo {
    /**
     * U1
     */
    private int typeParameterIndex;
    /**
     * U1
     */
    private int boundIndex;

    public TypeParameterBoundTarget(ByteCodeFile byteCodeFile) {
        this.typeParameterIndex = byteCodeFile.readOneUint();
        this.boundIndex = byteCodeFile.readOneUint();
    }
}
