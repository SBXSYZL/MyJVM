package jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl;

import jvm.classLoadSystem.analyzer.ByteCodeFile;
import jvm.classLoadSystem.analyzer.constant.ConstantPool;
import jvm.classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.ElementValue;

/**
 * @author 22454
 */
public class AttributeInfoAnnotationDefault implements AttributeInfo {
    private ElementValue defaultValue;

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        defaultValue = new ElementValue(byteCodeFile);
    }

    public ElementValue getDefaultValue() {
        return defaultValue;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DefaultValue: ").append("\n")
                .append("\t").append(defaultValue);
        return builder.toString();
    }
}
