package jvm.classLoadSystem.analyzer.constant.constantInfo.constantInfoImpl;

import jvm.classLoadSystem.analyzer.ByteCodeFile;
import jvm.classLoadSystem.analyzer.constant.ConstantPool;
import jvm.classLoadSystem.analyzer.constant.constantInfo.ConstantInfo;

import java.util.Map;

/**
 * @author 22454
 */
public class ConstantInfoInvokeDynamic implements ConstantInfo {
    private static final Integer TAG = INVOKE_DYNAMIC_TAG;
    private int bootstrapMethodAttrIndex;
    private int nameAndTypeIndex;
    private ConstantPool constantPool;

    @Override
    public void setInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) {
        this.bootstrapMethodAttrIndex = byteCodeFile.readTwoUint();
        this.nameAndTypeIndex = byteCodeFile.readTwoUint();
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

    public Map<String, String> getNameAndType() throws Exception {
        return constantPool.getNameAndType(
                nameAndTypeIndex
        );
    }

    @Override
    public String toString() {
        return "ConstantInfoInvokeDynamic{" +
                "bootstrapMethodAttrIndex=" + bootstrapMethodAttrIndex +
                ", nameAndTypeIndex=" + nameAndTypeIndex +
                ", constantPool=" + constantPool +
                '}';
    }
}
