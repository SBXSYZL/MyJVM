package jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.runtimeTypeAnnotations;

import jvm.classLoadSystem.analyzer.ByteCodeFile;
import jvm.classLoadSystem.analyzer.constant.ConstantPool;
import jvm.classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.TypeAnnotation;

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
