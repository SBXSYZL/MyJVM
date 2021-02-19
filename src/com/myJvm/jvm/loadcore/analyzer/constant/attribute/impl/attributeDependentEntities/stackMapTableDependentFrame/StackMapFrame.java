package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.stackMapTableDependentFrame;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;

/**
 * @author 22454
 */
public class StackMapFrame {
    private Frame frame;

    public StackMapFrame(ByteCodeFile byteCodeFile) {
        try {
            frame = Frame.createFrame(byteCodeFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static StackMapFrame[] readStackMapFrames(ByteCodeFile byteCodeFile, int numberOfEntries) {
        StackMapFrame[] stackMapFrames = new StackMapFrame[numberOfEntries];
        for (int i = 0; i < numberOfEntries; i++) {
            stackMapFrames[i] = new StackMapFrame(byteCodeFile);
        }
        return stackMapFrames;
    }

    @Override
    public String toString() {
        return frame.toString();
    }
}
