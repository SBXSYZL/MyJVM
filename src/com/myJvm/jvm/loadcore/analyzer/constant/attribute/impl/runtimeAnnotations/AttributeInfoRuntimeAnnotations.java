package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.runtimeAnnotations;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;
import com.myJvm.jvm.loadcore.analyzer.constant.ConstantPool;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.AttributeInfo;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.Annotation;

/**
 * @author 22454
 */
public abstract class AttributeInfoRuntimeAnnotations extends AttributeInfo {
    protected int numAnnotations;
    protected Annotation[] annotations;
    protected ConstantPool constantPool;

    public AttributeInfoRuntimeAnnotations(int attributeNameIndex, int attributeLength) {
        super(attributeNameIndex, attributeLength);
    }


    @Override
    public void readInfo(ByteCodeFile byteCodeFile,int attributeLength, ConstantPool constantPool) throws Exception {
        this.numAnnotations = byteCodeFile.readTwoUint();
        annotations = new Annotation[numAnnotations];
        for (int i = 0; i < numAnnotations; i++) {
            annotations[i] = new Annotation(byteCodeFile);
        }
        this.constantPool = constantPool;
    }
}
