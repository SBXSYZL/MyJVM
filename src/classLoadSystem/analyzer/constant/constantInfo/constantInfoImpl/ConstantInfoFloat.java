package classLoadSystem.analyzer.constant.constantInfo.constantInfoImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.constantInfo.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoFloat implements ConstantInfo {
    private static final Integer TAG = FLOAT_TAG;
    private Float bytes;

    @Override
    public void setInfo(ByteCodeFile byteCodeFile) {
        this.bytes = byteCodeFile.readFloat();
    }

    public Float getBytes() {
        return bytes;
    }

    @Override
    public int getTag() {
        return TAG;
    }
}
