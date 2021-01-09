package jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.stackMapTableDependentFrame;

import jvm.classLoadSystem.analyzer.ByteCodeFile;
import log.MyLog;

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
