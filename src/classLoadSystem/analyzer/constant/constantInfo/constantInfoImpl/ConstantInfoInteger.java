package classLoadSystem.analyzer.constant.constantInfo.constantInfoImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.constantInfo.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoInteger implements ConstantInfo {
    private static final Integer TAG = INTEGER_TAG;
    private int bytes;

    @Override
    public void setInfo(ByteCodeFile byteCodeFile) {
        this.bytes = byteCodeFile.readInteger();
    }

    public Integer getBytes() {
        return bytes;
    }

    @Override
    public int getTag() {
        return TAG;
    }
}
