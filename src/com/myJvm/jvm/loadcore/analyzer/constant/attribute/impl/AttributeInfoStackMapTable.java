package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;
import com.myJvm.jvm.loadcore.analyzer.constant.ConstantPool;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.AttributeInfo;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.stackMapTableDependentFrame.StackMapFrame;

/**
 * @author 22454
 */
public class AttributeInfoStackMapTable extends AttributeInfo {
    private int numberOfEntries;
    private StackMapFrame[] stackMapFrameEntries;
    private ConstantPool constantPool;

    public AttributeInfoStackMapTable(int attributeNameIndex, int attributeLength) {
        super(attributeNameIndex, attributeLength);
    }

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, int attributeLength, ConstantPool constantPool) throws Exception {
        this.constantPool = constantPool;
        this.numberOfEntries = byteCodeFile.readTwoUint();
//        MyLog.debug("number of entries : " + numberOfEntries);
        this.stackMapFrameEntries = StackMapFrame.readStackMapFrames(byteCodeFile, numberOfEntries);
    }

    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    public StackMapFrame[] getStackMapFrameEntries() {
        return stackMapFrameEntries;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("NumberOfEntries: ").append(numberOfEntries).append("\n")
                .append("StackMapFrameEntries: ").append("\n");
        for (StackMapFrame stackMapFrameEntry : stackMapFrameEntries) {
            builder.append("\t").append(stackMapFrameEntry).append("\n");
        }
        return builder.toString();
    }
}
