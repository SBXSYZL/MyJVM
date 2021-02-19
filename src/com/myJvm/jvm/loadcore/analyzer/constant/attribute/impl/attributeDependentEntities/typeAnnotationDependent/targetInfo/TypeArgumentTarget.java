package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.typeAnnotationDependent.targetInfo;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;

/**
 * @author 22454
 */
public class TypeArgumentTarget implements TargetInfo {
    /**
     * U2
     */
    private int offset;
    /**
     * U1
     */
    private int typeArgumentIndex;

    public TypeArgumentTarget(ByteCodeFile byteCodeFile) {
        this.offset = byteCodeFile.readTwoUint();
        this.typeArgumentIndex = byteCodeFile.readOneUint();
    }
}
