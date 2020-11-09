package classLoadSystem.analyzer.constant.attributeImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.AttributeInfo;
import classLoadSystem.analyzer.constant.ConstantPool;

/**
 * @author 22454
 */
public class AttributeInfoExceptions extends AttributeInfo {
    private int numberOfExceptions;
    private int[] exceptionIndexTable;

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        this.numberOfExceptions = byteCodeFile.readTwoUint();
        for (int i = 0; i < numberOfExceptions; i++) {
            exceptionIndexTable[i] = byteCodeFile.readTwoUint();
        }
    }

    public int[] getExceptionIndexTable() {
        return exceptionIndexTable;
    }
}
