package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.stackMapTableDependentFrame.frameImpl;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.stackMapTableDependentFrame.Frame;

/**
 * @author 22454
 */
public class ChopFrame implements Frame {
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
