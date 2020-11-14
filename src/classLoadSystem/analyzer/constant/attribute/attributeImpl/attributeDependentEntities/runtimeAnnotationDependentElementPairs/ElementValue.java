package classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.runtimeAnnotationDependentElementPairs;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.value.Value;

/**
 * @author 22454
 */
public class ElementValue {
    /**
     * U1
     */
    private int tag;
    private Value value;

    public ElementValue(ByteCodeFile byteCodeFile) throws Exception {
        this.tag = byteCodeFile.readOneUint();
        this.value = Value.createValue(tag, byteCodeFile);
    }
}
