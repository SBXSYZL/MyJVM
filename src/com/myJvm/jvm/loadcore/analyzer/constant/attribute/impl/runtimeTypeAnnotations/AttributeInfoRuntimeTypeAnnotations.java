package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.runtimeTypeAnnotations;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;
import com.myJvm.jvm.loadcore.analyzer.constant.ConstantPool;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.AttributeInfo;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.TypeAnnotation;

/**
 * @author 22454
 */
public abstract class AttributeInfoRuntimeTypeAnnotations extends AttributeInfo {
    /**
     * U2
     */
    private int numAnnotations;
    private TypeAnnotation[] annotations;

    public AttributeInfoRuntimeTypeAnnotations(int attributeNameIndex, int attributeLength) {
        super(attributeNameIndex, attributeLength);
    }

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, int attributeLength, ConstantPool constantPool) throws Exception {
        this.numAnnotations = byteCodeFile.readTwoUint();
        annotations = new TypeAnnotation[numAnnotations];
        for (int i = 0; i < numAnnotations; i++) {
            annotations[i] = new TypeAnnotation(byteCodeFile);
        }

    }
}
