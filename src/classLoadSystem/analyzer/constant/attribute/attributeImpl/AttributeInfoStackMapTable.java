package classLoadSystem.analyzer.constant.attribute.attributeImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.entities.frame.StackMapFrame;

/**
 * @author 22454
 */
public class AttributeInfoStackMapTable extends AttributeInfo {
    private int numberOfEntries;
    private StackMapFrame[] stackMapFrameEntries;

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        this.numberOfEntries = byteCodeFile.readTwoUint();
        this.stackMapFrameEntries=StackMapFrame.readStackMapFrames(byteCodeFile,numberOfEntries);
        //TODO stackMapFrameEntries 未初始化
    }


}