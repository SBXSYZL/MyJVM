package classLoadSystem.analyzer.constant.attribute.attributeImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.attribute.AttributeInfo;

/**
 * 高版本可能有一些无法识别的属性，用它记录
 *
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

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public byte[] getInfo() {
        return info;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        this.constantPool = constantPool;
        this.info = byteCodeFile.getByteByCnt(this.length);
    }
}
