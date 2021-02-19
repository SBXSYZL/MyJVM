package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.runtimeAnnotations.runtimeAnnotionsImpl;

import com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.runtimeAnnotations.AttributeInfoRuntimeAnnotations;

/**
 * @author 22454
 */
public class AttributeInfoRuntimeInvisibleAnnotations extends AttributeInfoRuntimeAnnotations {
    public AttributeInfoRuntimeInvisibleAnnotations(int attributeNameIndex, int attributeLength) {
        super(attributeNameIndex, attributeLength);
    }

    @Override
    public String toString() {
        return "NumberOfAnnotations: " + numAnnotations + "\n";
    }
}
