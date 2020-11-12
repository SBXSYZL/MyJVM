package classLoadSystem.analyzer.constant.attribute.attributeImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.entities.frame.StackMapFrame;
import log.MyLog;

/**
 * @author 22454
 */
public class AttributeInfoStackMapTable extends AttributeInfo {
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


}
