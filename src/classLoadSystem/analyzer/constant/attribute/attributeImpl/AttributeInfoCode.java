package classLoadSystem.analyzer.constant.attribute.attributeImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.entities.*;

/**
 * @author 22454
 */
public class AttributeInfoCode extends AttributeInfo {
    private int maxStack;
    private int maxLocals;
    private long codeLength;
    private byte[] code;
    private int exceptionTableLength;
    private ExceptionInfo[] exceptionTable;
    private int attributeCount;
    private AttributeInfo[] attributes;


    @Override
    public void readInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        maxStack = byteCodeFile.readTwoUint();
        maxLocals = byteCodeFile.readTwoUint();
        codeLength = byteCodeFile.readFourUint();
        code = byteCodeFile.getByteByCnt((int) codeLength);
        exceptionTableLength = byteCodeFile.readTwoUint();
        exceptionTable = new ExceptionInfo[exceptionTableLength];
        for (int i = 0; i < exceptionTableLength; i++) {
            exceptionTable[i] = new ExceptionInfo(
                    byteCodeFile.readTwoUint(),
                    byteCodeFile.readTwoUint(),
                    byteCodeFile.readTwoUint(),
                    byteCodeFile.readTwoUint());
        }
        this.attributeCount = byteCodeFile.readTwoUint();
        this.attributes = AttributeInfo.readAttributes(byteCodeFile, attributeCount, constantPool);

    }


}
