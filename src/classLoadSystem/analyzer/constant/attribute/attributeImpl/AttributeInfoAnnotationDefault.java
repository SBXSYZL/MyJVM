package classLoadSystem.analyzer.constant.attribute.attributeImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.ElementValue;

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
