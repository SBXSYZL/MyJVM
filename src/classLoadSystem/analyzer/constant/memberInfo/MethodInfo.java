package classLoadSystem.analyzer.constant.memberInfo;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.attribute.AttributeInfo;

/**
 * @author 22454
 */
public class MethodInfo {
    private int accessFlag;
    private int nameIndex;
    private int descriptorIndex;
    private int attributesCount;
    private AttributeInfo[] attributes;

    protected MethodInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        accessFlag = byteCodeFile.readTwoUint();
        nameIndex = byteCodeFile.readTwoUint();
        descriptorIndex = byteCodeFile.readTwoUint();
        attributesCount = byteCodeFile.readTwoUint();
        attributes = AttributeInfo.readAttributes(byteCodeFile, attributesCount, constantPool);
    }


    public static MethodInfo[] readMembers(ByteCodeFile byteCodeFile, ConstantPool constantPool, int memberCount) throws Exception {
        MethodInfo[] memberInfos = new MethodInfo[memberCount];
        for (int i = 0; i < memberCount; i++) {
            memberInfos[i] = new MethodInfo(byteCodeFile, constantPool);
        }
        return memberInfos;
    }
}
