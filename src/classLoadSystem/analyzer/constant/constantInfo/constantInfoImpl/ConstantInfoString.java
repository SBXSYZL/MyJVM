package classLoadSystem.analyzer.constant.constantInfo.constantInfoImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.constantInfo.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoString implements ConstantInfo {
    private static final Integer TAG = STRING_TAG;
    private int index;
    private ConstantPool constantPool;

    @Override
    public void setInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) {
        this.index = byteCodeFile.readTwoUint();
        this.constantPool = constantPool;
    }

    @Override
    public int numOfIndex() {
        return 1;
    }

    @Override
    public int getTag() {
        return TAG;
    }

    public String getString() throws Exception {
        return constantPool.getUtf8(index);
    }

    @Override
    public String toString() {
        return "ConstantInfoString{" +
                "index=" + index +
                ", constantPool=" + constantPool +
                '}';
    }
}
