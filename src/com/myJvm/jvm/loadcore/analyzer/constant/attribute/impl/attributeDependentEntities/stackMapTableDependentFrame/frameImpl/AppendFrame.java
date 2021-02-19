package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.stackMapTableDependentFrame.frameImpl;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.stackMapTableDependentFrame.Frame;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.stackMapTableDependentFrame.frameDependent.VerificationTypeInfo;

/**
 * @author 22454
 */
public class AppendFrame implements Frame {
    private int offsetDelta;
    private VerificationTypeInfo[] locals;


    @Override
    public void read(ByteCodeFile byteCodeFile, int frameType) {
        try {
            this.offsetDelta = byteCodeFile.readTwoUint();
            locals = VerificationTypeInfo.readVerificationTypeInfos(byteCodeFile, frameType - 251);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "OffsetDelta: " + offsetDelta + "\n";
    }
}
