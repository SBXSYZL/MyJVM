package classLoadSystem.analyzer.constant.constantInfo.constantInfoImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.constantInfo.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoDouble implements ConstantInfo {
    private static final Integer TAG = DOUBLE_TAG;
    private Double bytes;

    @Override
    public void setInfo(ByteCodeFile byteCodeFile) {
        this.bytes = byteCodeFile.readDouble();
    }

    public Double getBytes() {
        return bytes;
    }

    @Override
    public int getTag() {
        return TAG;
    }
}
