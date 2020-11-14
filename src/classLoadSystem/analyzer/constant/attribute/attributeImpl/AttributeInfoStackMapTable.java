package classLoadSystem.analyzer.constant.attribute.attributeImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.stackMapTableDependentFrame.StackMapFrame;
import log.MyLog;

/**
 * @author 22454
 */
public class AttributeInfoStackMapTable implements AttributeInfo {
    private int numberOfEntries;
    private StackMapFrame[] stackMapFrameEntries;
    private ConstantPool constantPool;

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        this.constantPool = constantPool;
        this.numberOfEntries = byteCodeFile.readTwoUint();
        MyLog.debug("number of entries : " + numberOfEntries);
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
