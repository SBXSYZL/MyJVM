package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;
import com.myJvm.jvm.loadcore.analyzer.constant.ConstantPool;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.AttributeInfo;
import com.myJvm.log.MyLog;

/**
 * @author 22454
 */
public class AttributeInfoExceptions extends AttributeInfo {
    private int numberOfExceptions;
    private int[] exceptionIndexTable;
    private ConstantPool constantPool;

    public AttributeInfoExceptions(int attributeNameIndex, int attributeLength) {
        super(attributeNameIndex, attributeLength);
    }

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, int attributeLength, ConstantPool constantPool) throws Exception {
        this.constantPool = constantPool;
        this.numberOfExceptions = byteCodeFile.readTwoUint();
        this.exceptionIndexTable = new int[this.numberOfExceptions];
        for (int i = 0; i < numberOfExceptions; i++) {
            exceptionIndexTable[i] = byteCodeFile.readTwoUint();
        }
    }

    public int[] getExceptionIndexTable() {
        return exceptionIndexTable;
    }

    public int getNumberOfExceptions() {
        return numberOfExceptions;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\tNumber Of Exceptions: ").append(numberOfExceptions).append("\n")
                .append("\tExceptions:\n");
        for (int index : exceptionIndexTable) {
            try {
                MyLog.debug("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx " + index);
                builder.append("\t").append(index).append("   --->   < ").append(constantPool.getClassName(index)).append(" >");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        builder.append("\n");
        return builder.toString();
    }
}
