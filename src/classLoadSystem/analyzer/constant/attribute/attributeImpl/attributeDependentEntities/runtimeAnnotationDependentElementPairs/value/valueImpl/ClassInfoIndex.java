package classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.value.valueImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.value.Value;

/**
 * @author 22454
 */
public class ClassInfoIndex implements Value {
    /**
     * U2
     */
    private int classInfoIndex;

    public ClassInfoIndex(ByteCodeFile byteCodeFile) {
        this.classInfoIndex = byteCodeFile.readTwoUint();
    }
}
