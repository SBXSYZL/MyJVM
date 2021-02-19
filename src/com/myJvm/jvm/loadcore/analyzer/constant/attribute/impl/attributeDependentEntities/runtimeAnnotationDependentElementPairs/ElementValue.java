package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.runtimeAnnotationDependentElementPairs;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.value.Value;

/**
 * @author 22454
 */
public class ElementValue {
    /**
     * U1
     */
    private int tag;
    private Value value;

    public ElementValue(ByteCodeFile byteCodeFile) throws Exception {
        this.tag = byteCodeFile.readOneUint();
        this.value = Value.createValue(tag, byteCodeFile);
    }
}
