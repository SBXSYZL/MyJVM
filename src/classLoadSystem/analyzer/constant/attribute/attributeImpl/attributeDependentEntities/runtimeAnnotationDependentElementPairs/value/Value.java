package classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.value;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.Annotation;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.value.valueImpl.*;
import exception.EmClassLoadErr;
import exception.JvmException;

/**
 * @author 22454
 */
public interface Value {
    /**
     * 根据 tag 创建值
     *
     * @param tag          tag
     * @param byteCodeFile 字节码文件
     * @return 值
     * @throws Exception ex
     */
    static Value createValue(int tag, ByteCodeFile byteCodeFile) throws Exception {
        char tagChar = (char) tag;
        switch (tagChar) {
            case 'B':
            case 'C':
            case 'D':
            case 'F':
            case 'I':
            case 'J':
            case 'S':
            case 'Z':
            case 's':
                return new ConstValueIndex(byteCodeFile);
            case 'e':
                return new EnumConstValue(byteCodeFile);
            case 'c':
                return new ClassInfoIndex(byteCodeFile);
            case '@':
                return new AnnotationValue(new Annotation(byteCodeFile));
            case '[':
                return new ArrayValue(byteCodeFile);
            default:
                throw new JvmException(EmClassLoadErr.FAILED_TO_CREATE_VALUE);
        }
    }
}
