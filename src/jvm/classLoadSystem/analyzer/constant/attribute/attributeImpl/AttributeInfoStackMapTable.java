package jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl;

import jvm.classLoadSystem.analyzer.ByteCodeFile;
import jvm.classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import jvm.classLoadSystem.analyzer.constant.ConstantPool;
import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.stackMapTableDependentFrame.StackMapFrame;
import log.MyLog;

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
