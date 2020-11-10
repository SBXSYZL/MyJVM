package classLoadSystem.analyzer.constant.constantInfo.constantInfoImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.constantInfo.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoString implements ConstantInfo {
    private static final Integer TAG = STRING_TAG;
    private int index;

    @Override
    public void setInfo(ByteCodeFile byteCodeFile) {
        this.index = byteCodeFile.readTwoUint();
    }

    @Override
    public int getTag() {
        return TAG;
    }
}
