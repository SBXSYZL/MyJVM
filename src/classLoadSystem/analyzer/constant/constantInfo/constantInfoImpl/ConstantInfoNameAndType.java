package classLoadSystem.analyzer.constant.constantInfo.constantInfoImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.constantInfo.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoNameAndType implements ConstantInfo {
    private static final Integer TAG = NAME_AND_TYPE_TAG;

    private int funcNameIndex;
    private int funcDescIndex;

    @Override
    public void setInfo(ByteCodeFile byteCodeFile) {
        this.funcNameIndex = byteCodeFile.readTwoUint();
        this.funcDescIndex = byteCodeFile.readTwoUint();
    }

    @Override
    public int getTag() {
        return TAG;
    }
}
