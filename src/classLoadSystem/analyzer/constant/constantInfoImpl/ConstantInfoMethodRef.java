package classLoadSystem.analyzer.constant.constantInfoImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoMethodRef implements ConstantInfo {
    private static final Integer TAG = METHOD_REF_TAG;
    private int constantInfoNameAndTypeIndex;
    private int constantInfoClassIndex;

    @Override
    public void setInfo(ByteCodeFile byteCodeFile) {
        this.constantInfoClassIndex = byteCodeFile.readTwoUint();
        this.constantInfoNameAndTypeIndex = byteCodeFile.readTwoUint();
    }

    @Override
    public int getTag() {
        return TAG;
    }
}
