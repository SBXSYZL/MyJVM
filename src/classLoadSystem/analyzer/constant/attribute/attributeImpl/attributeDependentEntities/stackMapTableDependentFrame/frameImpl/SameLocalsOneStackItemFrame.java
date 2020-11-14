package classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.stackMapTableDependentFrame.frameImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.stackMapTableDependentFrame.frameDependent.VerificationTypeInfo;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.stackMapTableDependentFrame.Frame;

/**
 * @author 22454
 */
public class SameLocalsOneStackItemFrame implements Frame {
    private VerificationTypeInfo[] stack;


    @Override
    public void read(ByteCodeFile byteCodeFile, int frameType) {
        try {
            stack = VerificationTypeInfo.readVerificationTypeInfos(byteCodeFile, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Stack: \n");
        for (VerificationTypeInfo verificationTypeInfo : stack) {
            builder.append("\t").append(verificationTypeInfo).append("\n");
        }
        return builder.toString();
    }
}
