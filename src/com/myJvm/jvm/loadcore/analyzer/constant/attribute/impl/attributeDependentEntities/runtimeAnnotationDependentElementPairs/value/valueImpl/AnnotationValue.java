package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.value.valueImpl;

import com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.Annotation;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.value.Value;

/**
 * @author 22454
 */
public class AnnotationValue implements Value {
    private Annotation annotationValue;

    public AnnotationValue(Annotation annotationValue) {
        this.annotationValue = annotationValue;
    }
}
