package jvm.classLoadSystem.analyzer.constant.memberInfo.memberInfoImpl;

import jvm.classLoadSystem.analyzer.ByteCodeFile;
import jvm.classLoadSystem.analyzer.constant.ConstantPool;
import jvm.classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.AttributeInfoConstantValue;
import jvm.classLoadSystem.analyzer.constant.memberInfo.MemberInfo;
import jvm.runtimeDataArea.common.AccessPermission;

/**
 * @author 22454
 */
public class FieldInfo implements MemberInfo {
    private int accessFlag;
    private int nameIndex;
    private int descriptorIndex;
    private int attributesCount;
    private AttributeInfo[] attributes;
    private ConstantPool constantPool;


    public FieldInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
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
        StringBuilder builder = new StringBuilder("\tAccess Flag: < ");
        builder.append(AccessPermission.getAccessFlagString(accessFlag));
        builder.append(">");
        try {
            builder.append("\tNameIndex = ").append(nameIndex).append("   --->   < ").append(constantPool.getUtf8(nameIndex)).append(" >").append(";\t");
            builder.append("DescriptorIndex = ").append(descriptorIndex).append("   --->   < ").append(constantPool.getUtf8(descriptorIndex)).append(" >").append("\n");
            for (AttributeInfo attribute : attributes) {
                builder.append("\t").append(attribute).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        builder.append("\n");
        return builder.toString();
    }

    public int getAccessFlag() {
        return accessFlag;
    }


    public String getName() throws Exception {
        return this.constantPool.getUtf8(this.nameIndex);
    }


    public String getDescriptor() throws Exception {
        return this.constantPool.getUtf8(this.descriptorIndex);
    }


    public int getAttributesCount() {
        return attributesCount;
    }

    public AttributeInfo[] getAttributes() {
        return attributes;
    }


    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public AttributeInfoConstantValue getConstantValueAttribute() {
        for (AttributeInfo attribute : attributes) {
            if (attribute instanceof AttributeInfoConstantValue) {
                return (AttributeInfoConstantValue) attribute;
            }
        }
        return null;
    }

}
