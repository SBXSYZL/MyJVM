package classLoadSystem.analyzer.constant.constantInfo.constantInfoImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.constantInfo.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoFieldRef implements ConstantInfo {
    private static final Integer TAG = FIELD_REF_TAG;
    /**
     * 声明的类的索引
     */
    private int constantInfoClassIndex;
    /**
     * 字段描述符索引
     */
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
