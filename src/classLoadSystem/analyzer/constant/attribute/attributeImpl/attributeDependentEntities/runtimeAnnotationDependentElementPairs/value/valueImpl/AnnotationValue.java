package classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.value.valueImpl;

import classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.Annotation;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.value.Value;

/**
 * @author 22454
 */
public class AnnotationValue implements Value {
    private Annotation annotationValue;

    public AnnotationValue(Annotation annotationValue) {
        this.annotationValue = annotationValue;
    }
}
