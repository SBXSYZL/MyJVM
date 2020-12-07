package jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.runtimeAnnotations;

import jvm.classLoadSystem.analyzer.ByteCodeFile;
import jvm.classLoadSystem.analyzer.constant.ConstantPool;
import jvm.classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.Annotation;

/**
 * @author 22454
 */
public abstract class AttributeInfoRuntimeAnnotations implements AttributeInfo {
    protected int numAnnotations;
    protected Annotation[] annotations;
    protected ConstantPool constantPool;


    @Override
    public void readInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        this.numAnnotations = byteCodeFile.readTwoUint();
        annotations = new Annotation[numAnnotations];
        for (int i = 0; i < numAnnotations; i++) {
            annotations[i] = new Annotation(byteCodeFile);
        }
        this.constantPool = constantPool;
    }
}
