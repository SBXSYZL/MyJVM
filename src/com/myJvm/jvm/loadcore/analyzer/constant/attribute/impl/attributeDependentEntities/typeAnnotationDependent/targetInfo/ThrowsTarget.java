package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.typeAnnotationDependent.targetInfo;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;

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
