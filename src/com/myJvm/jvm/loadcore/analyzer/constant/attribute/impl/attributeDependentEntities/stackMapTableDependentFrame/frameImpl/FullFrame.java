package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.stackMapTableDependentFrame.frameImpl;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.stackMapTableDependentFrame.Frame;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.stackMapTableDependentFrame.frameDependent.VerificationTypeInfo;

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
