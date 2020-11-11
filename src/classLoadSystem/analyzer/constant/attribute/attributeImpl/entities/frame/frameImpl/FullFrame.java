package classLoadSystem.analyzer.constant.attribute.attributeImpl.entities.frame.frameImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.entities.VerificationTypeInfo;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.entities.frame.Frame;

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

}
