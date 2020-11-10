package classLoadSystem.analyzer.constant.attribute.attributeImpl.entities;

import classLoadSystem.analyzer.ByteCodeFile;

/**
 * @author 22454
 */
public class FullFrame {
    public final static int FULL_FRAME = 255;
    private int frameType = FULL_FRAME;
    private int offsetDelta;
    private int numberOfLocals;
    private VerificationTypeInfo[] locals;
    private int numberOfStackItems;
    private VerificationTypeInfo[] stack;

    public FullFrame(ByteCodeFile byteCodeFile) {
        init(byteCodeFile);

    }

    private void init(ByteCodeFile byteCodeFile) {
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
}
