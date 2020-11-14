package classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.value.valueImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.value.Value;

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
