package classLoadSystem.analyzer.constant.constantInfoImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoMethodHandle implements ConstantInfo {
    private static final Integer TAG = METHOD_HANDLE_TAG;
    /**
     * 取值范围 [1,9]
     */
    private int referenceKind;

    /**
     * 常量池索引
     */
    private int referenceIndex;

    @Override
    public void setInfo(ByteCodeFile byteCodeFile) {
        this.referenceKind = byteCodeFile.readOneUint();
        this.referenceIndex = byteCodeFile.readTwoUint();
    }

    @Override
    public int getTag() {
        return TAG;
    }
}
