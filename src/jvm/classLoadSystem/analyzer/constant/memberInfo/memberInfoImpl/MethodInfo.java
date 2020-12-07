package jvm.classLoadSystem.analyzer.constant.memberInfo.memberInfoImpl;

import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.AttributeInfoCode;
import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.AttributeInfoLineNumberTable;
import jvm.runtimeDataArea.common.AccessPermission;
import jvm.classLoadSystem.analyzer.ByteCodeFile;
import jvm.classLoadSystem.analyzer.constant.ConstantPool;
import jvm.classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import jvm.classLoadSystem.analyzer.constant.memberInfo.MemberInfo;
import log.MyLog;

/**
 * @author 22454
 */
public class MethodInfo implements MemberInfo {
    private int accessFlag;
    private int nameIndex;
    private int descriptorIndex;
    private int attributesCount;
    private AttributeInfo[] attributes;
    private ConstantPool constantPool;


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
//            MyLog.print(memberInfos[i].toString());
        }
        return memberInfos;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("\t");
        builder.append("---------------------------START--------------------------------------\n");
        builder.append(AccessPermission.getAccessFlagString(accessFlag));
        try {
            builder.append("Function Name: ");
            builder.append(constantPool.getUtf8(nameIndex)).append("  ")
                    .append("   Descriptor: ")
                    .append(constantPool.getUtf8(descriptorIndex)).append("\n");
            for (AttributeInfo attribute : attributes) {
                builder.append("\t").append(attribute).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        builder.append("----------------------------END-------------------------------------\n");
        return builder.toString();
    }

    public int getAccessFlag() {
        return accessFlag;
    }

    public void setAccessFlag(int accessFlag) {
        this.accessFlag = accessFlag;
    }

    public String getName() throws Exception {
        return constantPool.getUtf8(this.nameIndex);
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public String getDescriptor() throws Exception {
        return constantPool.getUtf8(this.descriptorIndex);
    }

    public AttributeInfoCode getCodeAttribute() {
        for (AttributeInfo attribute : this.attributes) {
            if (attribute instanceof AttributeInfoCode) {
                return (AttributeInfoCode) attribute;
            }
        }
        return null;
    }


    public void setDescriptorIndex(int descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }

    public int getAttributesCount() {
        return attributesCount;
    }

    public void setAttributesCount(int attributesCount) {
        this.attributesCount = attributesCount;
    }

    public AttributeInfo[] getAttributes() {
        return attributes;
    }

    public void setAttributes(AttributeInfo[] attributes) {
        this.attributes = attributes;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }
}
