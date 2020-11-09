package classLoadSystem.analyzer.constant.attributeImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.AttributeInfo;
import classLoadSystem.analyzer.constant.ConstantPool;

/**
 * @author 22454
 */
public class AttributeInfoSignature extends AttributeInfo {
    private int signatureIndex;

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        this.signatureIndex = byteCodeFile.readTwoUint();
    }
}
