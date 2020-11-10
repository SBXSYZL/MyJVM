package classLoadSystem.analyzer.constant.attribute.attributeImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.attribute.AttributeInfo;
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
