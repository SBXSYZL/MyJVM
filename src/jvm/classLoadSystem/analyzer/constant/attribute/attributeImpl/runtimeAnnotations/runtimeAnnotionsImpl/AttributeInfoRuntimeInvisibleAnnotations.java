package jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.runtimeAnnotations.runtimeAnnotionsImpl;

import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.runtimeAnnotations.AttributeInfoRuntimeAnnotations;

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
