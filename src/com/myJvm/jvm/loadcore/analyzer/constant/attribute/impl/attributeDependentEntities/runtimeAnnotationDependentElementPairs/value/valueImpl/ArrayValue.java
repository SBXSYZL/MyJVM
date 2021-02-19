package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.value.valueImpl;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.ElementValue;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.value.Value;

/**
 * @author 22454
 */
public class ArrayValue implements Value {
    /**
     * U2
     */
    private int numValues;
    private ElementValue[] values;

    public ArrayValue(ByteCodeFile byteCodeFile) throws Exception {
        this.numValues = byteCodeFile.readTwoUint();
        this.values = new ElementValue[numValues];
        for (int i = 0; i < numValues; i++) {
            values[i] = new ElementValue(byteCodeFile);
        }
    }
}
