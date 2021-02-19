package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.typeAnnotationDependent.targetInfo;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;

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
