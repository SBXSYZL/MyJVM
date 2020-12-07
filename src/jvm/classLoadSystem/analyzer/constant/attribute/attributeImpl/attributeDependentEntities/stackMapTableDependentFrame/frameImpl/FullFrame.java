package jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.stackMapTableDependentFrame.frameImpl;

import jvm.classLoadSystem.analyzer.ByteCodeFile;
import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.stackMapTableDependentFrame.frameDependent.VerificationTypeInfo;
import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.stackMapTableDependentFrame.Frame;
import log.MyLog;

/**
 * @author 22454
 */
public class FullFrame implements Frame {
    private int offsetDelta;
    private int numberOfLocals;
    private VerificationTypeInfo[] locals;
    private int numberOfStackItems;
    private VerificationTypeInfo[] stack;


    @Override
    public void read(ByteCodeFile byteCodeFile, int frameType) {
        try {
            this.offsetDelta = byteCodeFile.readTwoUint();
            this.numberOfLocals = byteCodeFile.readTwoUint();
            this.locals = VerificationTypeInfo.readVerificationTypeInfos(byteCodeFile, numberOfLocals);
            this.numberOfStackItems = byteCodeFile.readTwoUint();
            this.stack = VerificationTypeInfo.readVerificationTypeInfos(byteCodeFile, numberOfStackItems);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("FULL(255),Offset: ");
        builder.append(offsetDelta).append("\n");
        builder.append("\tLocal verifications:\n");
        for (VerificationTypeInfo verificationTypeInfo : locals) {
            builder.append("\t\t").append(verificationTypeInfo.toString());
        }
        builder.append("Stack verifications:\n");
        for (VerificationTypeInfo verificationTypeInfo : stack) {
            builder.append("\t\t").append(verificationTypeInfo.toString());
        }
        builder.append("\n");
        return builder.toString();
    }
}
