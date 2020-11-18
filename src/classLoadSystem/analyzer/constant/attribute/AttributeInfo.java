package classLoadSystem.analyzer.constant.attribute;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.*;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.runtimeAnnotations.runtimeAnnotionsImpl.AttributeInfoRuntimeInvisibleAnnotations;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.runtimeAnnotations.runtimeAnnotionsImpl.AttributeInfoRuntimeVisibleAnnotations;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.runtimeParameterAnnotations.runtimeParameterAnnotationsImpl.AttributeInfoRunVisibleParameterAnnotations;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.runtimeParameterAnnotations.runtimeParameterAnnotationsImpl.AttributeInfoRuntimeInvisibleParameterAnnotations;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.runtimeTypeAnnotations.runtimeTypeAnnotationsImpl.AttributeInfoRuntimeInvisibleTypeAnnotations;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.runtimeTypeAnnotations.runtimeTypeAnnotationsImpl.AttributeInfoRuntimeVisibleTypeAnnotations;
import exception.EmClassLoadErr;
import exception.JvmException;
import log.MyLog;


/**
 * @author 22454
 */
public interface AttributeInfo {

    /**
     * 根据信息创建出属性对象
     *
     * @param attributeNameIndex 属性名下标
     * @param attributeLength    属性长度
     * @param constantPool       常量池
     * @return 属性对象
     * @throws Exception ex
     */
    static AttributeInfo createAttribute(int attributeNameIndex, int attributeLength, ConstantPool constantPool) throws Exception {
        String attributeName = constantPool.getUtf8(attributeNameIndex);
        switch (attributeName) {
            case AttributeTypeEnum.CODE:
                return new AttributeInfoCode();
            case AttributeTypeEnum.CONSTANT_VALUE:
                return new AttributeInfoConstantValue();
            case AttributeTypeEnum.DEPRECATED:
                return new AttributeInfoDeprecated();
            case AttributeTypeEnum.EXCEPTIONS:
                return new AttributeInfoExceptions();
            case AttributeTypeEnum.ENCLOSING_METHOD:
                return new AttributeInfoEnclosingMethod();
            case AttributeTypeEnum.INNER_CLASSES:
                return new AttributeInfoInnerClasses();
            case AttributeTypeEnum.LINE_NUMBER_TABLE:
                return new AttributeInfoLineNumberTable();
            case AttributeTypeEnum.LOCAL_VARIABLE_TABLE:
                return new AttributeInfoLocalVariableTable();
            case AttributeTypeEnum.STACK_MAP_TABLE:
                return new AttributeInfoStackMapTable();
            case AttributeTypeEnum.SIGNATURE:
                return new AttributeInfoSignature();
            case AttributeTypeEnum.SOURCE_FILE:
                return new AttributeInfoSourceFile();
            case AttributeTypeEnum.SOURCE_DEBUG_EXTENSION:
                return new AttributeInfoSourceDebugExtension();
            case AttributeTypeEnum.SYNTHETIC:
                return new AttributeInfoSynthetic();
            case AttributeTypeEnum.LOCAL_VARIABLE_TYPE_TABLE:
                return new AttributeInfoLocalVariableTypeTable();
            case AttributeTypeEnum.RUNTIME_VISIBLE_ANNOTATIONS:
                return new AttributeInfoRuntimeVisibleAnnotations();
            case AttributeTypeEnum.RUNTIME_INVISIBLE_ANNOTATIONS:
                return new AttributeInfoRuntimeInvisibleAnnotations();
            case AttributeTypeEnum.RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS:
                return new AttributeInfoRunVisibleParameterAnnotations();
            case AttributeTypeEnum.RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS:
                return new AttributeInfoRuntimeInvisibleParameterAnnotations();
            case AttributeTypeEnum.ANNOTATION_DEFAULT:
                return new AttributeInfoAnnotationDefault();
            case AttributeTypeEnum.BOOTSTRAP_METHODS:
                return new AttributeInfoBootstrapMethods();
            case AttributeTypeEnum.RUNTIME_VISIBLE_TYPE_ANNOTATIONS:
                return new AttributeInfoRuntimeVisibleTypeAnnotations();
            case AttributeTypeEnum.RUNTIME_INVISIBLE_TYPE_ANNOTATIONS:
                return new AttributeInfoRuntimeInvisibleTypeAnnotations();
            case AttributeTypeEnum.METHOD_PARAMETERS:
                return new AttributeInfoMethodParameters();
            //TODO 未写完所有 case,jdk9 & jdk11 不做实现
            default:
                throw new JvmException(EmClassLoadErr.JVM_VERSION_ERROR, "UnKnown Attribute Info");
        }
    }

    /**
     * 读取多个属性
     *
     * @param byteCodeFile   字节码文件
     * @param attributeCount 属性数量
     * @param constantPool   常量池
     * @return 属性集合
     */
    public static AttributeInfo[] readAttributes(ByteCodeFile byteCodeFile, int attributeCount, ConstantPool constantPool) throws Exception {
        AttributeInfo[] attributeInfos = new AttributeInfo[attributeCount];
        for (int i = 0; i < attributeCount; i++) {
            attributeInfos[i] = readAttribute(byteCodeFile, constantPool);
        }
        return attributeInfos;
    }

    /**
     * 读取属性
     *
     * @param constantPool 常量池
     * @param byteCodeFile 字节码文件
     * @return 属性
     */
    static AttributeInfo readAttribute(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        try {
            int attributeNameIndex = byteCodeFile.readTwoUint();
            int attributeLength = byteCodeFile.readInteger();
            AttributeInfo attributeInfo = createAttribute(attributeNameIndex, attributeLength, constantPool);
            attributeInfo.readInfo(byteCodeFile, constantPool);
            return attributeInfo;
        } catch (Exception e) {
            e.printStackTrace();
            throw new JvmException(EmClassLoadErr.FAILED_TO_READ_ATTRIBUTE);
        }
    }

    /**
     * 读取属性信息
     *
     * @param byteCodeFile 字节码文件
     * @param constantPool 常量池
     * @throws Exception ex
     */
    void readInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception;
}
