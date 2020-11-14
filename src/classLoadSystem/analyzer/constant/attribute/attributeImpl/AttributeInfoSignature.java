package classLoadSystem.analyzer.constant.attribute.attributeImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import classLoadSystem.analyzer.constant.ConstantPool;

/**
 * @author 22454
 */
public class AttributeInfoSignature implements AttributeInfo {
    private int signatureIndex;
    private ConstantPool constantPool;

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        this.constantPool = constantPool;
        this.signatureIndex = byteCodeFile.readTwoUint();
    }

    public int getSignatureIndex() {
        return signatureIndex;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    @Override
    public String toString() {
        try {
            return "SignatureIndex: " + signatureIndex + "\t" + "   --->   " + constantPool.getUtf8(signatureIndex) + "\n";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
