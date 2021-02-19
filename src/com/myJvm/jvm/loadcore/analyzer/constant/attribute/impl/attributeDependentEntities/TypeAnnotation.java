package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.ElementValuePairs;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.typeAnnotationDependent.TypePath;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.typeAnnotationDependent.targetInfo.TargetInfo;

/**
 * @author 22454
 */
public class TypeAnnotation {
    /**
     * U1
     */
    private int targetType;
    private TargetInfo targetInfo;
    private TypePath targetPath;
    /**
     * U2
     */
    private int typeIndex;
    /**
     * U2
     */
    private int numElementValuePairs;
    private ElementValuePairs[] elementValuePairs;


    public TypeAnnotation(ByteCodeFile byteCodeFile) throws Exception {
        this.targetType = byteCodeFile.readOneUint();
        this.targetInfo = TargetInfo.createTargetInfo(targetType, byteCodeFile);
        this.targetPath = new TypePath(byteCodeFile);
        this.typeIndex = byteCodeFile.readTwoUint();
        this.numElementValuePairs = byteCodeFile.readTwoUint();
        elementValuePairs = new ElementValuePairs[numElementValuePairs];
        for (int i = 0; i < numElementValuePairs; i++) {
            elementValuePairs[i] = new ElementValuePairs(byteCodeFile);
        }

    }
}
