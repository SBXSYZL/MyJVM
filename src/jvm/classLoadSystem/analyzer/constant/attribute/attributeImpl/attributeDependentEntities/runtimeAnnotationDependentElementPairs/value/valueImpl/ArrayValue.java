package jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.value.valueImpl;

import jvm.classLoadSystem.analyzer.ByteCodeFile;
import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.ElementValue;
import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.value.Value;

/**
 * @author 22454
 */
public class ArrayValue implements Value {
    /**
     * U2
     */
    private int numValues;
    private ElementValue[] values;

    public ArrayValue(ByteCodeFile byteCodeFile) throws Exception {
        this.numValues = byteCodeFile.readTwoUint();
        this.values = new ElementValue[numValues];
        for (int i = 0; i < numValues; i++) {
            values[i] = new ElementValue(byteCodeFile);
        }
    }
}
