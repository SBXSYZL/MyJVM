package classLoadSystem.analyzer.constant.attribute;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.*;
import log.MyLog;

/**
 * @author 22454
 */
public abstract class AttributeInfo {

    /**
     * 根据信息创建出属性对象
     *
     * @param attributeNameIndex 属性名下标
     * @return 属性对象
     * @throws Exception ex
     */
    static AttributeInfo createAttribute(int attributeNameIndex, int attributeLength, ConstantPool constantPool) throws Exception {
        String attributeName = constantPool.getUtf8(attributeNameIndex);
        MyLog.warn("Attribute Name : " + attributeName);
        switch (attributeName) {
            case AttributeTypeEnum.CODE:
                return new AttributeInfoCode();
            case AttributeTypeEnum.EXCEPTIONS:
                return new AttributeInfoExceptions();
            case AttributeTypeEnum.LINE_NUMBER_TABLE:
                return new AttributeInfoLineNumberTable();
            case AttributeTypeEnum.LOCAL_VARIABLE_TABLE:
                return new AttributeInfoLocalVariableTable();
            case AttributeTypeEnum.LOCAL_VARIABLE_TYPE_TABLE:
                return new AttributeInfoLocalVariableTypeTable();
            case AttributeTypeEnum.SOURCE_FILE:
                return new AttributeInfoSourceFile();
            case AttributeTypeEnum.SOURCE_DEBUG_EXTENSION:
                return new AttributeInfoSourceDebugExtension();
            case AttributeTypeEnum.CONSTANT_VALUE:
                return new AttributeInfoConstantValue();
            case AttributeTypeEnum.INNER_CLASSES:
                return new AttributeInfoInnerClasses();
            case AttributeTypeEnum.DEPRECATED:
                return new AttributeInfoDeprecated();
            case AttributeTypeEnum.SYNTHETIC:
                return new AttributeInfoSynthetic();
            case AttributeTypeEnum.STACK_MAP_TABLE:
                return new AttributeInfoStackMapTable();
            case AttributeTypeEnum.SIGNATURE:
                return new AttributeInfoSignature();
            case AttributeTypeEnum.BOOTSTRAP_METHODS:
                return new AttributeInfoBootstrapMethods();
            case AttributeTypeEnum.METHOD_PARAMETERS:
                return new AttributeInfoMethodParameters();
            default:
                return new AttributeInfoUnparsed(attributeName, attributeLength);
//                MyLog.error("maybe is this throw exception");
//                throw new Exception("maybe is this throw exception");
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
    public static AttributeInfo readAttribute(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        try {
            int attributeNameIndex = byteCodeFile.readTwoUint();
            MyLog.debug("AttributeNameIndex : " + attributeNameIndex);
            int attributeLength = byteCodeFile.readInteger();
            AttributeInfo attributeInfo = createAttribute(attributeNameIndex, attributeLength, constantPool);
            attributeInfo.readInfo(byteCodeFile, constantPool);
            return attributeInfo;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Read Attribute Fail");
        }
    }

    /**
     * 读取属性信息
     *
     * @param byteCodeFile 字节码文件
     * @param constantPool 常量池
     * @throws Exception ex
     */
    public abstract void readInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception;
}
