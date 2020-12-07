package jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.value.valueImpl;

import jvm.classLoadSystem.analyzer.ByteCodeFile;
import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.value.Value;

/**
 * @author 22454
 */
public class EnumConstValue implements Value {
    /**
     * U2
     */
    private int typeNameIndex;
    /**
     * U2
     */
    private int constNameIndex;

    public EnumConstValue(ByteCodeFile byteCodeFile) {
        this.typeNameIndex = byteCodeFile.readTwoUint();
        this.constNameIndex = byteCodeFile.readTwoUint();
    }
}
