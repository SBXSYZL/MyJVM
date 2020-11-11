package classLoadSystem.analyzer.constant.attribute.attributeImpl.entities.frame.frameImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.entities.VerificationTypeInfo;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.entities.frame.Frame;

/**
 * @author 22454
 */
public class SameLocalsOneStackItemFrame implements Frame {
    private VerificationTypeInfo[] stack;


    @Override
    public void read(ByteCodeFile byteCodeFile, int frameType) {
        try {
            stack = VerificationTypeInfo.readVerificationTypeInfos(byteCodeFile, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
