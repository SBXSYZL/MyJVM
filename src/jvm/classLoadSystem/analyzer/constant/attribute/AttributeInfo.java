package jvm.classLoadSystem.analyzer.constant.attribute;

import jvm.classLoadSystem.analyzer.ByteCodeFile;
import jvm.classLoadSystem.analyzer.constant.ConstantPool;
import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.*;
import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.runtimeAnnotations.runtimeAnnotionsImpl.AttributeInfoRuntimeInvisibleAnnotations;
import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.runtimeAnnotations.runtimeAnnotionsImpl.AttributeInfoRuntimeVisibleAnnotations;
import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.runtimeParameterAnnotations.runtimeParameterAnnotationsImpl.AttributeInfoRunVisibleParameterAnnotations;
import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.runtimeParameterAnnotations.runtimeParameterAnnotationsImpl.AttributeInfoRuntimeInvisibleParameterAnnotations;
import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.runtimeTypeAnnotations.runtimeTypeAnnotationsImpl.AttributeInfoRuntimeInvisibleTypeAnnotations;
import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.runtimeTypeAnnotations.runtimeTypeAnnotationsImpl.AttributeInfoRuntimeVisibleTypeAnnotations;
import exception.EmClassLoadErr;
import exception.JvmException;


/**
 * @author 22454
 */
public abstract class AttributeInfo {
    protected int attributeNameIndex;
    protected int attributeLength;

    public AttributeInfo(int attributeNameIndex, int attributeLength) {
        this.attributeNameIndex = attributeNameIndex;
        this.attributeLength = attributeLength;
    }

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
                return new AttributeInfoCode(attributeNameIndex, attributeLength);
            case AttributeTypeEnum.CONSTANT_VALUE:
                return new AttributeInfoConstantValue(attributeNameIndex, attributeLength);
            case AttributeTypeEnum.DEPRECATED:
                return new AttributeInfoDeprecated(attributeNameIndex, attributeLength);
            case AttributeTypeEnum.EXCEPTIONS:
                return new AttributeInfoExceptions(attributeNameIndex, attributeLength);
            case AttributeTypeEnum.ENCLOSING_METHOD:
                return new AttributeInfoEnclosingMethod(attributeNameIndex, attributeLength);
            case AttributeTypeEnum.INNER_CLASSES:
                return new AttributeInfoInnerClasses(attributeNameIndex, attributeLength);
            case AttributeTypeEnum.LINE_NUMBER_TABLE:
                return new AttributeInfoLineNumberTable(attributeNameIndex, attributeLength);
            case AttributeTypeEnum.LOCAL_VARIABLE_TABLE:
                return new AttributeInfoLocalVariableTable(attributeNameIndex, attributeLength);
            case AttributeTypeEnum.STACK_MAP_TABLE:
                return new AttributeInfoStackMapTable(attributeNameIndex, attributeLength);
            case AttributeTypeEnum.SIGNATURE:
                return new AttributeInfoSignature(attributeNameIndex, attributeLength);
            case AttributeTypeEnum.SOURCE_FILE:
                return new AttributeInfoSourceFile(attributeNameIndex, attributeLength);
            case AttributeTypeEnum.SOURCE_DEBUG_EXTENSION:
                return new AttributeInfoSourceDebugExtension(attributeNameIndex, attributeLength);
            case AttributeTypeEnum.SYNTHETIC:
                return new AttributeInfoSynthetic(attributeNameIndex, attributeLength);
            case AttributeTypeEnum.LOCAL_VARIABLE_TYPE_TABLE:
                return new AttributeInfoLocalVariableTypeTable(attributeNameIndex, attributeLength);
            case AttributeTypeEnum.RUNTIME_VISIBLE_ANNOTATIONS:
                return new AttributeInfoRuntimeVisibleAnnotations(attributeNameIndex, attributeLength);
            case AttributeTypeEnum.RUNTIME_INVISIBLE_ANNOTATIONS:
                return new AttributeInfoRuntimeInvisibleAnnotations(attributeNameIndex, attributeLength);
            case AttributeTypeEnum.RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS:
                return new AttributeInfoRunVisibleParameterAnnotations(attributeNameIndex, attributeLength);
            case AttributeTypeEnum.RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS:
                return new AttributeInfoRuntimeInvisibleParameterAnnotations(attributeNameIndex, attributeLength);
            case AttributeTypeEnum.ANNOTATION_DEFAULT:
                return new AttributeInfoAnnotationDefault(attributeNameIndex, attributeLength);
            case AttributeTypeEnum.BOOTSTRAP_METHODS:
                return new AttributeInfoBootstrapMethods(attributeNameIndex, attributeLength);
            case AttributeTypeEnum.RUNTIME_VISIBLE_TYPE_ANNOTATIONS:
                return new AttributeInfoRuntimeVisibleTypeAnnotations(attributeNameIndex, attributeLength);
            case AttributeTypeEnum.RUNTIME_INVISIBLE_TYPE_ANNOTATIONS:
                return new AttributeInfoRuntimeInvisibleTypeAnnotations(attributeNameIndex, attributeLength);
            case AttributeTypeEnum.METHOD_PARAMETERS:
                return new AttributeInfoMethodParameters(attributeNameIndex, attributeLength);
            //TODO 未写完所有 case,jdk9 & jdk11 不做实现
            default:
//                throw new JvmException(EmClassLoadErr.JVM_VERSION_ERROR, "UnKnown Attribute Info");
                return new AttributeInfoUnparsed(attributeNameIndex, attributeLength);
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
            attributeInfo.readInfo(byteCodeFile, attributeLength, constantPool);
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
    protected abstract void readInfo(ByteCodeFile byteCodeFile, int attributeLength, ConstantPool constantPool) throws Exception;
}
