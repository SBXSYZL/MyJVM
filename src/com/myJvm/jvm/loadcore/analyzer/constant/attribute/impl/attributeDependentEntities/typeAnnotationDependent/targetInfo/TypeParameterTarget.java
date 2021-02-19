package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.typeAnnotationDependent.targetInfo;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;

/**
 * @author 22454
 */
public class TypeParameterTarget implements TargetInfo {
    /**
     * U1
     */
    private int typeParameterIndex;

    public TypeParameterTarget(ByteCodeFile byteCodeFile) {
        this.typeParameterIndex = byteCodeFile.readOneUint();
    }
}
