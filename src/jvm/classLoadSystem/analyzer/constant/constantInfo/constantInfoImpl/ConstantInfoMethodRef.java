package jvm.classLoadSystem.analyzer.constant.constantInfo.constantInfoImpl;

import jvm.classLoadSystem.analyzer.ByteCodeFile;
import jvm.classLoadSystem.analyzer.constant.ConstantPool;
import jvm.classLoadSystem.analyzer.constant.constantInfo.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoMethodRef implements ConstantInfo {
    private static final Integer TAG = METHOD_REF_TAG;
    private int constantInfoNameAndTypeIndex;
    private int constantInfoClassIndex;
    private ConstantPool constantPool;

    @Override
    public void setInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) {
        this.constantInfoClassIndex = byteCodeFile.readTwoUint();
        this.constantInfoNameAndTypeIndex = byteCodeFile.readTwoUint();
        this.constantPool = constantPool;
    }

    @Override
    public int numOfIndex() {
        return 2;
    }

    @Override
    public int getTag() {
        return TAG;
    }

    @Override
    public String toString() {
        return "ConstantInfoMethodRef{" +
                "constantInfoNameAndTypeIndex=" + constantInfoNameAndTypeIndex +
                ", constantInfoClassIndex=" + constantInfoClassIndex +
                ", constantPool=" + constantPool +
                '}';
    }
}
