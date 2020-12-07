package jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.value.valueImpl;

import jvm.classLoadSystem.analyzer.ByteCodeFile;
import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.value.Value;

/**
 * @author 22454
 */
public class ClassInfoIndex implements Value {
    /**
     * U2
     */
    private int classInfoIndex;

    public ClassInfoIndex(ByteCodeFile byteCodeFile) {
        this.classInfoIndex = byteCodeFile.readTwoUint();
    }
}
