package jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.typeAnnotationDependent.targetInfo;

import jvm.classLoadSystem.analyzer.ByteCodeFile;

/**
 * @author 22454
 */
public class SupertypeTarget implements TargetInfo {
    /**
     * U2
     */
    private int supertypeIndex;

    public SupertypeTarget(ByteCodeFile byteCodeFile) {
        byteCodeFile.readTwoUint();
    }
}
