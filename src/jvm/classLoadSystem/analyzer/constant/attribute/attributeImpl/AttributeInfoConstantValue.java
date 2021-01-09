package jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl;

import jvm.classLoadSystem.analyzer.ByteCodeFile;
import jvm.classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import jvm.classLoadSystem.analyzer.constant.ConstantPool;

/**
 * @author 22454
 */
public class AttributeInfoConstantValue extends AttributeInfo {
    private int constantValueIndex;
    private ConstantPool constantPool;

    public AttributeInfoConstantValue(int attributeNameIndex, int attributeLength) {
        super(attributeNameIndex, attributeLength);
    }


    @Override
    public void readInfo(ByteCodeFile byteCodeFile, int attributeLength, ConstantPool constantPool) throws Exception {
        this.constantPool = constantPool;
        this.constantValueIndex = byteCodeFile.readTwoUint();
    }

    public int getConstantValueIndex() {
        return constantValueIndex;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    @Override
    public String toString() {
        return "" + constantValueIndex + "\n";
    }
}
