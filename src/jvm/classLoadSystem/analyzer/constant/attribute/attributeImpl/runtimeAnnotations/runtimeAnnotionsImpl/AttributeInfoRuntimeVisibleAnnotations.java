package jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.runtimeAnnotations.runtimeAnnotionsImpl;

import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.runtimeAnnotations.AttributeInfoRuntimeAnnotations;

/**
 * @author 22454
 */
public class AttributeInfoRuntimeVisibleAnnotations extends AttributeInfoRuntimeAnnotations {
    public AttributeInfoRuntimeVisibleAnnotations(int attributeNameIndex, int attributeLength) {
        super(attributeNameIndex, attributeLength);
    }

    @Override
    public String toString() {
        return "NumberOfAnnotations: " + numAnnotations + "\n";
    }
}
