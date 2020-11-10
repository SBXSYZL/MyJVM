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

    protected FieldInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        accessFlag = byteCodeFile.readTwoUint();
        nameIndex = byteCodeFile.readTwoUint();
        descriptorIndex = byteCodeFile.readTwoUint();
        attributesCount = byteCodeFile.readTwoUint();
        attributes = AttributeInfo.readAttributes(byteCodeFile, attributesCount, constantPool);
    }


    public static FieldInfo[] readMembers(ByteCodeFile byteCodeFile, ConstantPool constantPool, int memberCount) throws Exception {
        FieldInfo[] memberInfos = new FieldInfo[memberCount];
        for (int i = 0; i < memberCount; i++) {
            memberInfos[i] = new FieldInfo(byteCodeFile, constantPool);
        }
        return memberInfos;
    }
}
