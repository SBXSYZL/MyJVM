package classLoadSystem.analyzer.constant.constantInfoImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoModule implements ConstantInfo {
    private static final Integer TAG = MODULE_TAG;
    private int nameIndex;

    @Override
    public void setInfo(ByteCodeFile byteCodeFile) {
        this.nameIndex = byteCodeFile.readTwoUint();
    }

    @Override
    public int getTag() {
        return TAG;
    }
}
