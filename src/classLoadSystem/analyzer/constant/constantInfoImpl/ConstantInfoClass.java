package classLoadSystem.analyzer.constant.constantInfoImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoClass implements ConstantInfo {
    private final static Integer TAG = CLASS_TAG;
    private int index;

    @Override
    public void setInfo(ByteCodeFile byteCodeFile) {
        index = byteCodeFile.readTwoUint();
    }

    @Override
    public int getTag() {
        return TAG;
    }
}
