package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.ElementValuePairs;

/**
 * @author 22454
 */
public class Annotation {
    /**
     * U2
     */
    private int typeIndex;
    /**
     * U2
     */
    private int numElementValuePairs;
    private ElementValuePairs[] elementValuePairs;

    public Annotation(ByteCodeFile byteCodeFile) throws Exception {
        this.typeIndex = byteCodeFile.readTwoUint();
        this.numElementValuePairs = byteCodeFile.readTwoUint();
        this.elementValuePairs = new ElementValuePairs[numElementValuePairs];
        for (int i = 0; i < numElementValuePairs; i++) {
            elementValuePairs[i] = new ElementValuePairs(byteCodeFile);
        }
    }
}
