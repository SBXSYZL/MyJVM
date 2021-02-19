package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.value.valueImpl;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.value.Value;

/**
 * @author 22454
 */
public class ClassInfoIndex implements Value {
    /**
     * U2
     */
    private int classInfoIndex;

    public ClassInfoIndex(ByteCodeFile byteCodeFile) {
        this.classInfoIndex = byteCodeFile.readTwoUint();
    }
}
