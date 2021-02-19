package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.typeAnnotationDependent.targetInfo;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;

/**
 * @author 22454
 */
public class TypeParameterBoundTarget implements TargetInfo {
    /**
     * U1
     */
    private int typeParameterIndex;
    /**
     * U1
     */
    private int boundIndex;

    public TypeParameterBoundTarget(ByteCodeFile byteCodeFile) {
        this.typeParameterIndex = byteCodeFile.readOneUint();
        this.boundIndex = byteCodeFile.readOneUint();
    }
}
