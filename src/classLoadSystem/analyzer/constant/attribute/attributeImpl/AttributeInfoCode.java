package classLoadSystem.analyzer.constant.attribute.attributeImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.entities.*;
import log.MyLog;

import java.util.Arrays;

/**
 * @author 22454
 */
public class AttributeInfoCode extends AttributeInfo {
    private int maxStack;
    private int maxLocals;
    private int codeLength;
    private byte[] code;
    private int exceptionTableLength;
    private ExceptionInfo[] exceptionTable;
    private int attributeCount;
    private AttributeInfo[] attributes;
    private ConstantPool constantPool;


    @Override
    public void readInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        this.constantPool = constantPool;
        maxStack = byteCodeFile.readTwoUint();
        maxLocals = byteCodeFile.readTwoUint();
        codeLength = byteCodeFile.readInteger();
        code = byteCodeFile.getByteByCnt(codeLength);
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
        MyLog.print(this.toString());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Code: \n");
        builder.append("stack=")
                .append(maxStack)
                .append(", locals=")
                .append(maxLocals)
                .append("\n");
        return builder.toString();

    }
}
