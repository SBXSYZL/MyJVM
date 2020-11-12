package classLoadSystem.analyzer.constant.constantInfo.constantInfoImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.constantInfo.ConstantInfo;
import log.MyLog;

import java.util.Map;

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
    private ConstantPool constantPool;

    @Override
    public void setInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) {
        this.constantInfoClassIndex = byteCodeFile.readTwoUint();
        this.constantInfoNameAndTypeIndex = byteCodeFile.readTwoUint();
        this.constantPool = constantPool;
    }

    @Override
    public int numOfIndex() {
        return 2;
    }

    @Override
    public int getTag() {
        return TAG;
    }

    public String getClassName() throws Exception {
        try {
            return constantPool.getClassName(constantInfoClassIndex);
        } catch (Exception e) {
            e.printStackTrace();
            MyLog.error("Get Class Name Fail");
            throw new Exception();
        }
    }

    public Map<String, String> getNameAndType() throws Exception {
        return constantPool.getNameAndType(constantInfoNameAndTypeIndex);
    }

    @Override
    public String toString() {
        return "ConstantInfoFieldRef{" +
                "constantInfoClassIndex=" + constantInfoClassIndex +
                ", constantInfoNameAndTypeIndex=" + constantInfoNameAndTypeIndex +
                ", constantPool=" + constantPool +
                '}';
    }
}
