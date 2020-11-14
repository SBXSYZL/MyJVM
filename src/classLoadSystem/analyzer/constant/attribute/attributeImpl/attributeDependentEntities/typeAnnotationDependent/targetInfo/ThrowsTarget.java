package classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.typeAnnotationDependent.targetInfo;

import classLoadSystem.analyzer.ByteCodeFile;

/**
 * @author 22454
 */
public class ThrowsTarget implements TargetInfo {
    /**
     * U2
     */
    private int throwsTypeIndex;

    public ThrowsTarget(ByteCodeFile byteCodeFile) {
        throwsTypeIndex = byteCodeFile.readTwoUint();
    }
}
