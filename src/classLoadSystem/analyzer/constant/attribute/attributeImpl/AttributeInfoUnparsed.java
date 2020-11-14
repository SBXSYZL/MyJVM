package classLoadSystem.analyzer.constant.attribute.attributeImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.attribute.AttributeInfo;

/**
 * @author 22454
 */
public class AttributeInfoUnparsed implements AttributeInfo {
    private String name;
    private int length;
    private byte[] info;
    private ConstantPool constantPool;

    public AttributeInfoUnparsed(String name, int length) {
        this.name = name;
        this.length = length;
    }

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        this.constantPool = constantPool;
        this.info = byteCodeFile.getByteByCnt(this.length);
    }
}
