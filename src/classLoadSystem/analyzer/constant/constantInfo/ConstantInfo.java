package classLoadSystem.analyzer.constant.constantInfo;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.constantInfo.constantInfoImpl.*;
import log.MyLog;

import java.lang.reflect.Field;

/**
 * @author 22454
 */
public interface ConstantInfo {
    int UTF8_TAG = 1;
    int INTEGER_TAG = 3;
    int FLOAT_TAG = 4;
    int LONG_TAG = 5;
    int DOUBLE_TAG = 6;
    int CLASS_TAG = 7;
    int STRING_TAG = 8;
    int FIELD_REF_TAG = 9;
    int METHOD_REF_TAG = 10;
    int INTERFACE_METHOD_REF_TAG = 11;

    int NAME_AND_TYPE_TAG = 12;
    int METHOD_HANDLE_TAG = 15;
    int METHOD_TYPE_TAG = 16;
    int DYNAMIC_TAG = 17;
    int INVOKE_DYNAMIC_TAG = 18;
    int MODULE_TAG = 19;
    int PACKAGE_TAG = 20;


    /**
     * 获取 TAG
     *
     * @return TAG
     */
    int getTag();

    /**
     * 有几个 索引 变量
     *
     * @return 索引个数
     */
    @Deprecated
    int numOfIndex();

    /**
     * 设置属性
     *
     * @param byteCodeFile 字节码对象
     * @param constantPool 常量池
     */
    void setInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool);

    /**
     * 根据tag值创建一个ConstantInfo
     *
     * @param tag tag值
     * @return 返回类型
     * @throws Exception ex
     */
    static ConstantInfo createConstantInfo(int tag) throws Exception {
        switch (tag) {
            case UTF8_TAG:
                return new ConstantInfoUtf8();
            case INTEGER_TAG:
                return new ConstantInfoInteger();
            case FLOAT_TAG:
                return new ConstantInfoFloat();
            case LONG_TAG:
                return new ConstantInfoLong();
            case DOUBLE_TAG:
                return new ConstantInfoDouble();
            case CLASS_TAG:
                return new ConstantInfoClass();
            case STRING_TAG:
                return new ConstantInfoString();
            case FIELD_REF_TAG:
                return new ConstantInfoFieldRef();
            case METHOD_REF_TAG:
                return new ConstantInfoMethodRef();
            case INTERFACE_METHOD_REF_TAG:
                return new ConstantInfoInterfaceMethodRef();
            case NAME_AND_TYPE_TAG:
                return new ConstantInfoNameAndType();
            case METHOD_HANDLE_TAG:
                return new ConstantInfoMethodHandle();
            case METHOD_TYPE_TAG:
                return new ConstantInfoMethodType();
            case DYNAMIC_TAG:
                return new ConstantInfoDynamic();
            default:
                MyLog.error("Constant Pool TAG " + tag + " does not exists.");
                throw new Exception("Constant Pool TAG " + tag + " does not exists.");
        }
    }

    /**
     * 打印(调试使用)
     *
     * @param constantInfo x
     * @return x
     */
    static String getString(ConstantInfo constantInfo) {
        Class<? extends ConstantInfo> constantInfoClass = constantInfo.getClass();
        Field[] fields = constantInfoClass.getDeclaredFields();
        StringBuilder builder = new StringBuilder();
        builder.append("TAG: ");
        String tag = findTag(constantInfo);
        builder.append(tag)
                .append("\n\t");
        builder.append("info: \n\t\t");
        for (Field field : fields) {
            try {
                if (!"TAG".equals(field.getName())) {
                    field.setAccessible(true);
                    builder.append(field.getName()).append(" : ");
                    builder.append(field.get(constantInfo));
                    builder.append("  ");
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        builder.append("\n");
        return builder.toString();
    }

    /**
     * 根据TAG 获取 TAG的名字（调试使用）
     */
    static String findTag(ConstantInfo constantInfo) {
        Field[] fields1 = ConstantInfo.class.getFields();
        int tag = constantInfo.getTag();
        for (Field field : fields1) {
            try {
                int anInt = field.getInt(constantInfo);
                if (tag == anInt) {
                    return field.getName();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
