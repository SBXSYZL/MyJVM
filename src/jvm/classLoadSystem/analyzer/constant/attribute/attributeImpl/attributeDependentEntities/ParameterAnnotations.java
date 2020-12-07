package jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities;

import jvm.classLoadSystem.analyzer.ByteCodeFile;

/**
 * @author 22454
 */
public class ParameterAnnotations {
    private int numAnnotations;
    private Annotation[] annotations;

    public ParameterAnnotations(ByteCodeFile byteCodeFile) throws Exception {
        this.numAnnotations = byteCodeFile.readTwoUint();
        annotations = new Annotation[numAnnotations];
        for (int i = 0; i < numAnnotations; i++) {
            annotations[i] = new Annotation(byteCodeFile);
        }
    }
}
