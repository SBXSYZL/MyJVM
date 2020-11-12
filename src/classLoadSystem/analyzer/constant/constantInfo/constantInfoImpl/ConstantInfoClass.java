package classLoadSystem.analyzer.constant.constantInfo.constantInfoImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.constantInfo.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoClass implements ConstantInfo {
    private final static Integer TAG = CLASS_TAG;
    private ConstantPool constantPool;
    private int index;

    @Override
    public void setInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) {
        this.constantPool = constantPool;
        index = byteCodeFile.readTwoUint();
    }

    public int getIndex() {
        return index;
    }

    @Override
    public int numOfIndex() {
        return 1;
    }

    @Override
    public int getTag() {
        return TAG;
    }

    public String getClassName() throws Exception {
        return constantPool.getUtf8(index);
    }

    @Override
    public String toString() {
        return "ConstantInfoClass{" +
                "constantPool=" + constantPool +
                ", index=" + index +
                '}';
    }
}
