package jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.stackMapTableDependentFrame.frameImpl;

import jvm.classLoadSystem.analyzer.ByteCodeFile;
import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.stackMapTableDependentFrame.frameDependent.VerificationTypeInfo;
import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.stackMapTableDependentFrame.Frame;

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
