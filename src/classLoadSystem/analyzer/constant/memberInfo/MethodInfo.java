package classLoadSystem.analyzer.constant.memberInfo;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import log.MyLog;

import java.util.Arrays;

/**
 * @author 22454
 */
public class MethodInfo {
    private int accessFlag;
    private int nameIndex;
    private int descriptorIndex;
    private int attributesCount;
    private AttributeInfo[] attributes;
    private ConstantPool constantPool;

    public static final int ACC_PUBLIC = 0x0001;
    public static final int ACC_PRIVATE = 0x0002;
    public static final int ACC_PROTECTED = 0x0004;
    public static final int ACC_STATIC = 0x0008;
    public static final int ACC_FINAL = 0x0010;
    public static final int ACC_SYNCHRONIZED = 0x0020;
    public static final int ACC_BRIDGE = 0x00040;
    public static final int ACC_VARARGS = 0x0080;
    public static final int ACC_NATIVE = 0x0100;
    public static final int ACC_ABSTRACT = 0x0400;
    public static final int ACC_STRICT = 0x0800;
    public static final int ACC_SYNTHETIC = 0x1000;

    protected MethodInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        accessFlag = byteCodeFile.readTwoUint();
        nameIndex = byteCodeFile.readTwoUint();
        descriptorIndex = byteCodeFile.readTwoUint();
        attributesCount = byteCodeFile.readTwoUint();
        this.constantPool = constantPool;
        attributes = AttributeInfo.readAttributes(byteCodeFile, attributesCount, constantPool);
    }


    public static MethodInfo[] readMembers(ByteCodeFile byteCodeFile, ConstantPool constantPool, int memberCount) throws Exception {
        MethodInfo[] memberInfos = new MethodInfo[memberCount];
        for (int i = 0; i < memberCount; i++) {
            memberInfos[i] = new MethodInfo(byteCodeFile, constantPool);
            MyLog.print(memberInfos[i].toString());
        }
        return memberInfos;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("\t");
        if ((accessFlag & ACC_PUBLIC) == ACC_PUBLIC) {
            builder.append("public ");
        }
        if ((accessFlag & ACC_PRIVATE) == ACC_PRIVATE) {
            builder.append("private ");
        }
        if ((accessFlag & ACC_PROTECTED) == ACC_PROTECTED) {
            builder.append("protected ");
        }
        if ((accessFlag & ACC_STATIC) == ACC_STATIC) {
            builder.append("static ");
        }
        if ((accessFlag & ACC_FINAL) == ACC_FINAL) {
            builder.append("final ");
        }
        if ((accessFlag & ACC_SYNCHRONIZED) == ACC_SYNCHRONIZED) {
            builder.append("synchronized ");
        }
        if ((accessFlag & ACC_BRIDGE) == ACC_BRIDGE) {
            builder.append("bridge ");
        }
        if ((accessFlag & ACC_VARARGS) == ACC_VARARGS) {
            builder.append("varargs ");
        }
        if ((accessFlag & ACC_NATIVE) == ACC_NATIVE) {
            builder.append("native ");
        }
        if ((accessFlag & ACC_ABSTRACT) == ACC_ABSTRACT) {
            builder.append("abstract ");
        }
        if ((accessFlag & ACC_STRICT) == ACC_STRICT) {
            builder.append("strict ");
        }
        if ((accessFlag & ACC_SYNTHETIC) == ACC_SYNTHETIC) {
            builder.append("synthetic ");
        }
        try {
            builder.append(constantPool.getUtf8(nameIndex)).append("  ")
                    .append(constantPool.getUtf8(descriptorIndex)).append("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
