package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.runtimeAnnotationDependentElementPairs;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;

/**
 * @author 22454
 */
public class ElementValuePairs {
    private int elementNameIndex;
    private ElementValue value;

    public ElementValuePairs(ByteCodeFile byteCodeFile) throws Exception {
        this.elementNameIndex = byteCodeFile.readTwoUint();
        this.value = new ElementValue(byteCodeFile);
    }
}