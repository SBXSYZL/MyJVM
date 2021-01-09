package jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl;

import jvm.classLoadSystem.analyzer.ByteCodeFile;
import jvm.classLoadSystem.analyzer.constant.ConstantPool;
import jvm.classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.ElementValue;

/**
 * @author 22454
 */
public class AttributeInfoAnnotationDefault extends AttributeInfo {
    private ElementValue defaultValue;

    public AttributeInfoAnnotationDefault(int attributeNameIndex, int attributeLength) {
        super(attributeNameIndex, attributeLength);
    }

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, int attributeLength, ConstantPool constantPool) throws Exception {
        defaultValue = new ElementValue(byteCodeFile);
    }

    public ElementValue getDefaultValue() {
        return defaultValue;
    }

    @Override
    public String toString() {
        return "AttributeInfoAnnotationDefault{" +
                "defaultValue=" + defaultValue +
                ", attributeNameIndex=" + attributeNameIndex +
                ", attributeLength=" + attributeLength +
                '}';
    }
}
