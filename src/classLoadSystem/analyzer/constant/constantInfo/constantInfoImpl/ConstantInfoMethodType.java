package classLoadSystem.analyzer.constant.constantInfo.constantInfoImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.constantInfo.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoMethodType implements ConstantInfo {
    private static final Integer TAG = METHOD_TYPE_TAG;
    /**
     * 方法的描述符
     */
    private int descriptorIndex;

    @Override
    public void setInfo(ByteCodeFile byteCodeFile) {
        this.descriptorIndex = byteCodeFile.readTwoUint();
    }

    @Override
    public int getTag() {
        return TAG;
    }
}
