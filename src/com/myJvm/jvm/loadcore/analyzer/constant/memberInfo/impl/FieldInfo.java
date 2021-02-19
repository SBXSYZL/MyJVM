package com.myJvm.jvm.loadcore.analyzer.constant.memberInfo.impl;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;
import com.myJvm.jvm.loadcore.analyzer.constant.ConstantPool;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.AttributeInfo;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.AttributeInfoConstantValue;
import com.myJvm.jvm.loadcore.analyzer.constant.memberInfo.MemberInfo;
import com.myJvm.jvm.runtime.common.AccessPermission;

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
        builder.append(AccessPermission.getFieldAccessFlagString(accessFlag));
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
