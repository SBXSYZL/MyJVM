package classLoadSystem.analyzer.constant.memberInfo;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.memberInfo.memberInfoImpl.FieldInfo;

public interface MemberInfo {

    int ACC_PUBLIC_COMMON = 0x0001;
    int ACC_PRIVATE_COMMON = 0x0002;
    int ACC_PROTECTED_COMMON = 0x0004;
    int ACC_STATIC_COMMON = 0x0008;
    int ACC_FINAL_COMMON = 0x0010;
    int ACC_SYNTHETIC_COMMON = 0x1000;

    int ACC_VOLATILE_FIELD = 0x0040;
    int ACC_TRANSIENT_FIELD = 0x0080;
    int ACC_ENUM_FIELD = 0x4000;


    int ACC_SYNCHRONIZED_METHOD = 0x0020;
    int ACC_BRIDGE_METHOD = 0x00040;
    int ACC_VARARGS_METHOD = 0x0080;
    int ACC_NATIVE_METHOD = 0x0100;
    int ACC_ABSTRACT_METHOD = 0x0400;
    int ACC_STRICT_METHOD = 0x0800;

    /**
     * 根据 flag值获取响应访问权限字符串
     *
     * @param accessFlag 访问 flag
     * @return 访问权限字符串
     */
    static String getAccessFlagString(int accessFlag) {
        StringBuilder builder = new StringBuilder();
        if ((accessFlag & ACC_PUBLIC_COMMON) == ACC_PUBLIC_COMMON) {
            builder.append("public ");
        }
        if ((accessFlag & ACC_PRIVATE_COMMON) == ACC_PRIVATE_COMMON) {
            builder.append("private ");
        }
        if ((accessFlag & ACC_PROTECTED_COMMON) == ACC_PROTECTED_COMMON) {
            builder.append("protected ");
        }
        if ((accessFlag & ACC_STATIC_COMMON) == ACC_STATIC_COMMON) {
            builder.append("static ");
        }
        if ((accessFlag & ACC_FINAL_COMMON) == ACC_FINAL_COMMON) {
            builder.append("final ");
        }
        if ((accessFlag & ACC_SYNTHETIC_COMMON) == ACC_SYNTHETIC_COMMON) {
            builder.append("synthetic ");
        }
        if ((accessFlag & ACC_VOLATILE_FIELD) == ACC_VOLATILE_FIELD) {
            builder.append("volatile ");
        }
        if ((accessFlag & ACC_TRANSIENT_FIELD) == ACC_TRANSIENT_FIELD) {
            builder.append("transient ");
        }
        if ((accessFlag & ACC_ENUM_FIELD) == ACC_ENUM_FIELD) {
            builder.append("enum ");
        }

        if ((accessFlag & ACC_SYNCHRONIZED_METHOD) == ACC_SYNCHRONIZED_METHOD) {
            builder.append("synchronized ");
        }
        if ((accessFlag & ACC_BRIDGE_METHOD) == ACC_BRIDGE_METHOD) {
            builder.append("bridge ");
        }
        if ((accessFlag & ACC_VARARGS_METHOD) == ACC_VARARGS_METHOD) {
            builder.append("varargs ");
        }
        if ((accessFlag & ACC_NATIVE_METHOD) == ACC_NATIVE_METHOD) {
            builder.append("native ");
        }
        if ((accessFlag & ACC_ABSTRACT_METHOD) == ACC_ABSTRACT_METHOD) {
            builder.append("abstract ");
        }
        if ((accessFlag & ACC_STRICT_METHOD) == ACC_STRICT_METHOD) {
            builder.append("strict ");
        }
        return builder.toString();

    }
}
