package jvm.classLoadSystem.analyzer.constant.constantInfo.constantInfoImpl;

import jvm.classLoadSystem.analyzer.ByteCodeFile;
import jvm.classLoadSystem.analyzer.constant.ConstantPool;
import jvm.classLoadSystem.analyzer.constant.constantInfo.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoUtf8 implements ConstantInfo {
    private static final Integer TAG = UTF8_TAG;
    private int length;
    private String bytes;
    private ConstantPool constantPool;

    @Override
    public void setInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) {
        int length = byteCodeFile.readTwoUint();
        byte[] bytes = byteCodeFile.getByteByCnt(length);
        this.length = length;
        this.bytes = new String(bytes);
        this.constantPool = constantPool;
    }

    public String getString() {
        return bytes;
    }

    @Override
    public int numOfIndex() {
        return 0;
    }

    @Override
    public int getTag() {
        return TAG;
    }

    @Override
    public String toString() {
        return "ConstantInfoUtf8{" +
                "length=" + length +
                ", bytes='" + bytes + '\'' +
                ", constantPool=" + constantPool +
                '}';
    }
}
