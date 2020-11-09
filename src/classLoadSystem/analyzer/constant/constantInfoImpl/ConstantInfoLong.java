package classLoadSystem.analyzer.constant.constantInfoImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoLong implements ConstantInfo {
    private static final Integer TAG = LONG_TAG;
    private Long bytes;

    @Override
    public void setInfo(ByteCodeFile byteCodeFile) {
        this.bytes = byteCodeFile.readLong();
    }

    @Override
    public int getTag() {
        return TAG;
    }
}
