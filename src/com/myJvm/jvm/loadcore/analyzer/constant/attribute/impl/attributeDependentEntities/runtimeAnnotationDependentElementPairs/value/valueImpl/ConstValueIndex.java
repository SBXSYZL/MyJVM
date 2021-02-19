package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.value.valueImpl;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.value.Value;

/**
 * @author 22454
 */
public class ConstValueIndex implements Value {
    /**
     * U2
     */
    private int constValueIndex;

    public ConstValueIndex(ByteCodeFile byteCodeFile) {
        this.constValueIndex = byteCodeFile.readTwoUint();
    }
}
