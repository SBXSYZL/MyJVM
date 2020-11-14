package classLoadSystem.analyzer.constant.memberInfo.memberInfoImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import classLoadSystem.analyzer.constant.memberInfo.MemberInfo;
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
            MyLog.print(memberInfos[i].toString());
        }
        return memberInfos;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("\t");
        builder.append(MemberInfo.getAccessFlagString(accessFlag));
        try {
            builder.append(constantPool.getUtf8(nameIndex)).append("  ")
                    .append(constantPool.getUtf8(descriptorIndex)).append("\n");
            for (AttributeInfo attribute : attributes) {
                builder.append("\t").append(attribute).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
