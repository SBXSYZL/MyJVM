package com.myJvm.jvm.loadcore.analyzer.constant;

import com.myJvm.exception.EmClassLoadErr;
import com.myJvm.exception.JvmException;
import com.myJvm.jvm.loadcore.analyzer.constant.constantInfo.ConstantInfo;
import com.myJvm.jvm.loadcore.analyzer.constant.constantInfo.impl.*;
import com.myJvm.log.MyLog;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 22454
 */
public final class ConstantPool {
    private ConstantInfo[] pool;
    private final int constantPoolCount;
    private String className;

    public ConstantPool(int constantPoolCount) {
        this.constantPoolCount = constantPoolCount;
        pool = new ConstantInfo[constantPoolCount];

    }

    public void put(int index, ConstantInfo constantInfo) {
        pool[index] = constantInfo;
    }

    public ConstantInfo get(int index) {
        return pool[index];
    }

    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * 根据 index 获取常量池中的一个 utf8 字符串
     */
    public String getUtf8(int index) throws Exception {
        try {
            ConstantInfoUtf8 constantInfoUtf8 = (ConstantInfoUtf8) pool[index];
            return constantInfoUtf8 == null ? "" : constantInfoUtf8.getString();
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof ClassCastException) {
                MyLog.error("ConstantInfo Type Cast Error");
                throw new JvmException(EmClassLoadErr.CONSTANT_INFO_TYPE_CAST_ERROR);
            }
            throw new JvmException(EmClassLoadErr.CONSTANT_POOL_DO_NOT_EXISTS_THIS_ELEMENT, "Class: [ " + className + " ] --> The element with index [ " + index + " ] does not exist in the constant pool");
        }
    }

    public String constantPoolToStringForShow() throws Exception {
        StringBuilder builder = new StringBuilder("----------------------------------------------------------")
                .append("\n")
                .append("Constant pool:\n");
        int index = 1;
        for (int i = 1; i < this.constantPoolCount; i++) {
            ConstantInfo constantInfo = pool[i];
            if (constantInfo != null) {
                String tag = ConstantInfo.findTag(constantInfo);
                tag = tag.substring(0, tag.length() - 4);
                tag = tag.substring(0, 1).toUpperCase().concat(tag.substring(1).toLowerCase());
                builder.append("\t#")
                        .append(i)
                        .append(" = ")
                        .append(tag)
                        .append("        ");
                switch (constantInfo.getTag()) {
                    case ConstantInfo.UTF8_TAG:
                        builder.append(((ConstantInfoUtf8) constantInfo).getString());
                        break;
                    case ConstantInfo.INTEGER_TAG:
                        builder.append(((ConstantInfoInteger) constantInfo).getInteger());
                        break;
                    case ConstantInfo.LONG_TAG:
                        builder.append(((ConstantInfoLong) constantInfo).getLong());
                        break;
                    case ConstantInfo.FLOAT_TAG:
                        builder.append(((ConstantInfoFloat) constantInfo).getFloat());
                        break;
                    case ConstantInfo.DOUBLE_TAG:
                        builder.append(((ConstantInfoDouble) constantInfo).getDouble());
                        break;
                    case ConstantInfo.CLASS_TAG:
                        builder.append(((ConstantInfoClass) constantInfo).getClassName());
                        break;
                    case ConstantInfo.STRING_TAG:
                        builder.append(((ConstantInfoString) constantInfo).getString());
                        break;
                    case ConstantInfo.FIELD_REF_TAG:
                        builder.append(((ConstantInfoFieldRef) constantInfo).getNameAndType());
                        break;
                    case ConstantInfo.METHOD_REF_TAG:
                        builder.append(((ConstantInfoMethodRef) constantInfo).getTag());
                        break;
                    case ConstantInfo.INTERFACE_METHOD_REF_TAG:
                        builder.append(((ConstantInfoInterfaceMethodRef) constantInfo).getNameAndType());
                        break;
                    case ConstantInfo.NAME_AND_TYPE_TAG:
                        builder.append("#").append(((ConstantInfoNameAndType) constantInfo).getFuncNameIndex());
                        break;
                    case ConstantInfo.METHOD_HANDLE_TAG:
                        builder.append(((ConstantInfoMethodHandle) constantInfo).getTag());
                        break;
                    case ConstantInfo.METHOD_TYPE_TAG:
                        builder.append(((ConstantInfoMethodType) constantInfo).getTag());
                        break;
                    case ConstantInfo.DYNAMIC_TAG:
                        builder.append(((ConstantInfoDynamic) constantInfo).getNameAndType());
                        break;
                    default:
                        break;
                }
                builder.append("\n");
            } else {
                builder.append("\t#").append(i).append(" = 续上一个数字型常量").append("\n");
            }
        }
        builder.append("----------------------------------------------------------\n");
        return builder.toString();
    }

    public String getClassName(int index) throws Exception {
        ConstantInfoClass constantInfoClass = null;
        try {
            constantInfoClass = (ConstantInfoClass) pool[index];
            return getUtf8(constantInfoClass.getIndex());
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof ClassCastException) {
                MyLog.error(constantInfoClass.getClass() + " Can not Cast to ConstantInfoClass");
                throw new JvmException(EmClassLoadErr.CONSTANT_INFO_TYPE_CAST_ERROR);
            }
            throw new JvmException(EmClassLoadErr.CONSTANT_POOL_DO_NOT_EXISTS_THIS_ELEMENT, "Class: [ " + className + " ] --> The element with index [ " + index + " ] does not exist in the constant pool");
        }
    }

    public Map<String, String> getNameAndType(int nameAndTypeIndex) throws Exception {
        try {
            ConstantInfoNameAndType constantInfoNameAndType = (ConstantInfoNameAndType) pool[nameAndTypeIndex];
            Map<String, String> map = new HashMap<>(2);
            map.put("name", getUtf8(constantInfoNameAndType.getFuncNameIndex()));
            map.put("_type", getUtf8(constantInfoNameAndType.getFuncDescIndex()));
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof ClassCastException) {
                MyLog.error("ConstantInfo Type Cast Error");
                throw new JvmException(EmClassLoadErr.CONSTANT_INFO_TYPE_CAST_ERROR);
            }
            throw new JvmException(EmClassLoadErr.CONSTANT_POOL_DO_NOT_EXISTS_THIS_ELEMENT, "Class: [ " + className + " ] --> The element with index [ " + nameAndTypeIndex + " ] does not exist in the constant pool");
        }


    }

    public int getConstantPoolCount() {
        return constantPoolCount;
    }

    public ConstantInfo[] getAllConstantInfo() {
        return pool;
    }

    public String getClassName() {
        return className;
    }
}
