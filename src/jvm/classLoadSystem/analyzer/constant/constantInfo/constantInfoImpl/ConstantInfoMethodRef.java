package jvm.classLoadSystem.analyzer.constant.constantInfo.constantInfoImpl;

import jvm.classLoadSystem.analyzer.ByteCodeFile;
import jvm.classLoadSystem.analyzer.constant.ConstantPool;
import jvm.classLoadSystem.analyzer.constant.constantInfo.ConstantInfo;

import java.util.Map;

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

    public String getClassName() throws Exception {
        return this.constantPool.getClassName(constantInfoClassIndex);
    }

    public Map<String, String> getNameAndDescriptor() throws Exception {
        return constantPool.getNameAndType(this.constantInfoNameAndTypeIndex);
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
