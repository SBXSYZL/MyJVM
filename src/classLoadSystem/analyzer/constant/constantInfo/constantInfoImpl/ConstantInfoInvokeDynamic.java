package classLoadSystem.analyzer.constant.constantInfo.constantInfoImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.constantInfo.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoInvokeDynamic implements ConstantInfo {
    private static final Integer TAG = INVOKE_DYNAMIC_TAG;
    private int bootstrapMethodAttrIndex;
    private int nameAndTypeIndex;

    @Override
    public void setInfo(ByteCodeFile byteCodeFile) {
        this.bootstrapMethodAttrIndex = byteCodeFile.readTwoUint();
        this.nameAndTypeIndex = byteCodeFile.readTwoUint();
    }

    @Override
    public int getTag() {
        return TAG;
    }
}
