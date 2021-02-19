package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.typeAnnotationDependent.targetInfo;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;

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
