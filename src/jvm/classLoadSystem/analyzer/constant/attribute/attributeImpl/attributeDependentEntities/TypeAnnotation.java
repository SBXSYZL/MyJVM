package jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities;

import jvm.classLoadSystem.analyzer.ByteCodeFile;
import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.ElementValuePairs;
import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.typeAnnotationDependent.TypePath;
import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.typeAnnotationDependent.targetInfo.TargetInfo;

/**
 * @author 22454
 */
public class TypeAnnotation {
    /**
     * U1
     */
    private int targetType;
    private TargetInfo targetInfo;
    private TypePath targetPath;
    /**
     * U2
     */
    private int typeIndex;
    /**
     * U2
     */
    private int numElementValuePairs;
    private ElementValuePairs[] elementValuePairs;


    public TypeAnnotation(ByteCodeFile byteCodeFile) throws Exception {
        this.targetType = byteCodeFile.readOneUint();
        this.targetInfo = TargetInfo.createTargetInfo(targetType, byteCodeFile);
        this.targetPath = new TypePath(byteCodeFile);
        this.typeIndex = byteCodeFile.readTwoUint();
        this.numElementValuePairs = byteCodeFile.readTwoUint();
        elementValuePairs = new ElementValuePairs[numElementValuePairs];
        for (int i = 0; i < numElementValuePairs; i++) {
            elementValuePairs[i] = new ElementValuePairs(byteCodeFile);
        }

    }
}
