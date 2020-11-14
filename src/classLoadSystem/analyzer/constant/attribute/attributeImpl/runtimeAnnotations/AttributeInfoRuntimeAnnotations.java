package classLoadSystem.analyzer.constant.attribute.attributeImpl.runtimeAnnotations;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.Annotation;

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
