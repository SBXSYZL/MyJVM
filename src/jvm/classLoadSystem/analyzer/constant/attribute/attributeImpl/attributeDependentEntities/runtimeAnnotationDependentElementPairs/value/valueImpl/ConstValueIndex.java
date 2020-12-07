package jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.value.valueImpl;

import jvm.classLoadSystem.analyzer.ByteCodeFile;
import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.value.Value;

/**
 * @author 22454
 */
public class ConstValueIndex implements Value {
    /**
     * U2
     */
    private int constValueIndex;

    public ConstValueIndex(ByteCodeFile byteCodeFile) {
        this.constValueIndex = byteCodeFile.readTwoUint();
    }
}
