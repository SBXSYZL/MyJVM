package classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.typeAnnotationDependent.targetInfo;

import classLoadSystem.analyzer.ByteCodeFile;

/**
 * @author 22454
 */
public class OffsetTarget implements TargetInfo {
    /**
     * U2
     */
    private int offset;

    public OffsetTarget(ByteCodeFile byteCodeFile) {
        offset = byteCodeFile.readTwoUint();
    }
}
