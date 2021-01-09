package jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl;

import jvm.classLoadSystem.analyzer.ByteCodeFile;
import jvm.classLoadSystem.analyzer.constant.ConstantPool;
import jvm.classLoadSystem.analyzer.constant.attribute.AttributeInfo;

/**
 * 高版本可能有一些无法识别的属性，用它记录
 *
 * @author 22454
 */
public class AttributeInfoUnparsed extends AttributeInfo {
    private String name;
    private int length;
    private byte[] info;
    private ConstantPool constantPool;

    public AttributeInfoUnparsed(int attributeNameIndex, int attributeLength) {
        super(attributeNameIndex, attributeLength);
    }

//    public AttributeInfoUnparsed(String name, int length) {
//        this.name = name;
//        this.length = length;
//    }


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
    public void readInfo(ByteCodeFile byteCodeFile, int attributeLength, ConstantPool constantPool) throws Exception {
        this.constantPool = constantPool;
        this.info = byteCodeFile.getByteByCnt(this.length);
    }
}
