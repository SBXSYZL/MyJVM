package classLoadSystem.analyzer.constant;

import classLoadSystem.analyzer.ByteCodeFile;

/**
 * @author 22454
 */
public class MemberInfo {
    private int accessFlag;
    private int nameIndex;
    private int descriptorIndex;
    private int attributesCount;
    private AttributeInfo[] attributes;

    protected MemberInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        accessFlag = byteCodeFile.readTwoUint();
        nameIndex = byteCodeFile.readTwoUint();
        descriptorIndex = byteCodeFile.readTwoUint();
        attributesCount = byteCodeFile.readTwoUint();
        attributes = new AttributeInfo[attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            AttributeInfo attribute = AttributeInfo.readAttribute(byteCodeFile, constantPool);
            attributes[i] = attribute;
        }
    }


    public static MemberInfo[] readMembers(ByteCodeFile byteCodeFile, ConstantPool constantPool, int memberCount) throws Exception {
        MemberInfo[] memberInfos = new MemberInfo[memberCount];
        for (int i = 0; i < memberCount; i++) {
            memberInfos[i] = new MemberInfo(byteCodeFile, constantPool);
        }
        return memberInfos;
    }
}
