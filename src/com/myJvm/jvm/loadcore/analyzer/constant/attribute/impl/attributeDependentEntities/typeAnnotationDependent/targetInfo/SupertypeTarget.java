package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.typeAnnotationDependent.targetInfo;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;

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
