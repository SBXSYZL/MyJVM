package classLoadSystem.analyzer.constant.attribute.attributeImpl.runtimeTypeAnnotations;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.TypeAnnotation;

/**
 * @author 22454
 */
public abstract class AttributeInfoRuntimeTypeAnnotations implements AttributeInfo {
    /**
     * U2
     */
    private int numAnnotations;
    private TypeAnnotation[] annotations;

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        this.numAnnotations = byteCodeFile.readTwoUint();
        annotations = new TypeAnnotation[numAnnotations];
        for (int i = 0; i < numAnnotations; i++) {
            annotations[i] = new TypeAnnotation(byteCodeFile);
        }

    }
}
