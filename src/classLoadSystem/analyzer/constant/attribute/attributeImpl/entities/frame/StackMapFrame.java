package classLoadSystem.analyzer.constant.attribute.attributeImpl.entities.frame;

import classLoadSystem.analyzer.ByteCodeFile;

/**
 * @author 22454
 */
public class StackMapFrame {
    private Frame frame;

    public StackMapFrame(ByteCodeFile byteCodeFile) {
        try {
            frame=Frame.createFrame(byteCodeFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static StackMapFrame[] readStackMapFrames(ByteCodeFile byteCodeFile,int frameCount){
        StackMapFrame[] stackMapFrames=new StackMapFrame[frameCount];
        for (int i=0;i<frameCount;i++){
            stackMapFrames[i]=new StackMapFrame(byteCodeFile);
        }
        return stackMapFrames;
    }
}
