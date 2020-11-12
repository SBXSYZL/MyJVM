package classLoadSystem.analyzer.constant.memberInfo;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.attribute.AttributeInfo;

/**
 * @author 22454
 */
public class FieldInfo {
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
    public static final int ACC_VOLATILE = 0x0040;
    public static final int ACC_TRANSIENT = 0x0080;
    public static final int ACC_SYNTHETIC = 0x1000;
    public static final int ACC_ENUM = 0x4000;


    protected FieldInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        accessFlag = byteCodeFile.readTwoUint();
        nameIndex = byteCodeFile.readTwoUint();
        descriptorIndex = byteCodeFile.readTwoUint();
        attributesCount = byteCodeFile.readTwoUint();
        this.constantPool = constantPool;
        attributes = AttributeInfo.readAttributes(byteCodeFile, attributesCount, constantPool);
    }


    public static FieldInfo[] readMembers(ByteCodeFile byteCodeFile, ConstantPool constantPool, int memberCount) throws Exception {
        FieldInfo[] memberInfos = new FieldInfo[memberCount];
        for (int i = 0; i < memberCount; i++) {
            memberInfos[i] = new FieldInfo(byteCodeFile, constantPool);
        }
        return memberInfos;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Access Flag: < ");
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
        if ((accessFlag & ACC_VOLATILE) == ACC_VOLATILE) {
            builder.append("volatile ");
        }
        if ((accessFlag & ACC_TRANSIENT) == ACC_TRANSIENT) {
            builder.append("transient ");
        }
        if ((accessFlag & ACC_SYNTHETIC) == ACC_SYNTHETIC) {
            builder.append("synthetic ");
        }
        if ((accessFlag & ACC_ENUM) == ACC_ENUM) {
            builder.append("enum ");
        }
        builder.append(">");
        try {
            builder.append("\tNameIndex = ").append(nameIndex).append("   --->   < ").append(constantPool.getUtf8(nameIndex)).append(" >").append(";\t");
            builder.append("DescriptorIndex = ").append(descriptorIndex).append("   --->   < ").append(constantPool.getUtf8(descriptorIndex)).append(" >").append("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        builder.append("\n");
        return builder.toString();
    }
}
