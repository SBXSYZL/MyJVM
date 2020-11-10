package classLoadSystem.analyzer.constant.constantInfo.constantInfoImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.constantInfo.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoUtf8 implements ConstantInfo {
    private static final Integer TAG = UTF8_TAG;
    private int length;
    private String bytes;

    @Override
    public void setInfo(ByteCodeFile byteCodeFile) {
        int length = byteCodeFile.readTwoUint();
        byte[] bytes = byteCodeFile.getByteByCnt(length);
        this.length = length;
        this.bytes = new String(bytes);
    }

    public String getString() {
        return bytes;
    }

    @Override
    public int getTag() {
        return TAG;
    }
}
