package classLoadSystem.analyzer.constant.constantInfo.constantInfoImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.constantInfo.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoInterfaceMethodRef implements ConstantInfo {
    private static final Integer TAG = INTERFACE_METHOD_REF_TAG;
    private int constantInfoClassIndex;
    private int constantInfoNameAndTypeIndex;

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
