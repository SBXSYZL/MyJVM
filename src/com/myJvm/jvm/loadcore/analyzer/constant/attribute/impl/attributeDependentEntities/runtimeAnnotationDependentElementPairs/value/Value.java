package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.value;

import com.myJvm.exception.EmClassLoadErr;
import com.myJvm.exception.JvmException;
import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.Annotation;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.runtimeAnnotationDependentElementPairs.value.valueImpl.*;

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
