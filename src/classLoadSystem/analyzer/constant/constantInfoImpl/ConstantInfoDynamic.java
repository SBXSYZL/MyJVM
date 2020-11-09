package classLoadSystem.analyzer.constant.constantInfoImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoDynamic implements ConstantInfo {
    private static final Integer TAG = DYNAMIC_TAG;
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
