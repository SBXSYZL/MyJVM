package classLoadSystem.analyzer.constant.constantInfo.constantInfoImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.constantInfo.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoDouble implements ConstantInfo {
    private static final Integer TAG = DOUBLE_TAG;
    private Double bytes;
    private ConstantPool constantPool;

    @Override
    public void setInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) {
        this.bytes = byteCodeFile.readDouble();
        this.constantPool = constantPool;
    }

    public Double getDouble() {
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
        return "ConstantInfoDouble{" +
                "bytes=" + bytes +
                ", constantPool=" + constantPool +
                '}';
    }
}
