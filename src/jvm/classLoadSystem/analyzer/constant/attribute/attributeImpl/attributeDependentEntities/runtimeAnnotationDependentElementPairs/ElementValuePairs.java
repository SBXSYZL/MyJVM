package jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.runtimeAnnotationDependentElementPairs;

import jvm.classLoadSystem.analyzer.ByteCodeFile;

/**
 * @author 22454
 */
public class ElementValuePairs {
    private int elementNameIndex;
    private ElementValue value;

    public ElementValuePairs(ByteCodeFile byteCodeFile) throws Exception {
        this.elementNameIndex = byteCodeFile.readTwoUint();
        this.value = new ElementValue(byteCodeFile);
    }
}