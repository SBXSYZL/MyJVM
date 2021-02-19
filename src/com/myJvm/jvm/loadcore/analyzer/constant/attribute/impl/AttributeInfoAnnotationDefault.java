package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;
import com.myJvm.jvm.loadcore.analyzer.constant.ConstantPool;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.AttributeInfo;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.ElementValue;

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
