package classLoadSystem.analyzer.constant.attribute.attributeImpl.entities.frame.frameImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.entities.frame.Frame;

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
}
