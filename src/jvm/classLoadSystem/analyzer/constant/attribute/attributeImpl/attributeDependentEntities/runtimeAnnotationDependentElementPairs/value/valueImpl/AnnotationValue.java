package jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.value.valueImpl;

import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.Annotation;
import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.value.Value;

/**
 * @author 22454
 */
public class AnnotationValue implements Value {
    private Annotation annotationValue;

    public AnnotationValue(Annotation annotationValue) {
        this.annotationValue = annotationValue;
    }
}
