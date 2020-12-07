package jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.stackMapTableDependentFrame.frameImpl;

import jvm.classLoadSystem.analyzer.ByteCodeFile;
import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.stackMapTableDependentFrame.Frame;

/**
 * @author 22454
 */
public class SameFrameExtended implements Frame {
    private int offsetDelta;

    @Override
    public void read(ByteCodeFile byteCodeFile, int frameType) {
        try {
            this.offsetDelta = byteCodeFile.readTwoUint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "OffsetDelta: " + offsetDelta + "\n";
    }
}
