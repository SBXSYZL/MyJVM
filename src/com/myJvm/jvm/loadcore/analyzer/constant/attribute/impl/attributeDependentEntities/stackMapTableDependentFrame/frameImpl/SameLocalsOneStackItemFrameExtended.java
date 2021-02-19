package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.stackMapTableDependentFrame.frameImpl;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.stackMapTableDependentFrame.Frame;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.stackMapTableDependentFrame.frameDependent.VerificationTypeInfo;

/**
 * @author 22454
 */
public class SameLocalsOneStackItemFrameExtended implements Frame {
    private int offsetDelta;
    private VerificationTypeInfo[] stack;

    @Override
    public void read(ByteCodeFile byteCodeFile, int frameType) {
        try {

            this.offsetDelta = byteCodeFile.readTwoUint();
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
