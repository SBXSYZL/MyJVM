package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.typeAnnotationDependent.targetInfo;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;

/**
 * @author 22454
 */
public class FormalParameterTarget implements TargetInfo {
    /**
     * U1
     */
    private int formalParameterIndex;

    public FormalParameterTarget(ByteCodeFile byteCodeFile) {
        this.formalParameterIndex = byteCodeFile.readOneUint();
    }
}
