package jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl;

import jvm.classLoadSystem.analyzer.ByteCodeFile;
import jvm.classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import jvm.classLoadSystem.analyzer.constant.ConstantPool;

/**
 * @author 22454
 */
public class AttributeInfoSignature extends AttributeInfo {
    private int signatureIndex;
    private ConstantPool constantPool;

    public AttributeInfoSignature(int attributeNameIndex, int attributeLength) {
        super(attributeNameIndex, attributeLength);
    }

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, int attributeLength, ConstantPool constantPool) throws Exception {
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
