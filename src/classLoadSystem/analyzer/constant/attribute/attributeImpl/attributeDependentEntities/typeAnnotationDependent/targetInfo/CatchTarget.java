package classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.typeAnnotationDependent.targetInfo;

import classLoadSystem.analyzer.ByteCodeFile;

/**
 * @author 22454
 */
public class CatchTarget implements TargetInfo {
    /**
     * U2
     */
    private int exceptionTableIndex;

    public CatchTarget(ByteCodeFile byteCodeFile) {
        exceptionTableIndex = byteCodeFile.readTwoUint();
    }
}
