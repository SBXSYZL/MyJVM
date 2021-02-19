package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.value.valueImpl;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.value.Value;

/**
 * @author 22454
 */
public class EnumConstValue implements Value {
    /**
     * U2
     */
    private int typeNameIndex;
    /**
     * U2
     */
    private int constNameIndex;

    public EnumConstValue(ByteCodeFile byteCodeFile) {
        this.typeNameIndex = byteCodeFile.readTwoUint();
        this.constNameIndex = byteCodeFile.readTwoUint();
    }
}
