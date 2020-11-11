package classLoadSystem.analyzer.constant.attribute.attributeImpl.entities.frame;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.entities.frame.frameImpl.*;
import log.MyLog;

/**
 * @author 22454
 */
public interface Frame {

    /**
     * 读取信息
     *
     * @param byteCodeFile 字节码文件
     * @param frameType    frame类型
     */
    void read(ByteCodeFile byteCodeFile, int frameType);

    /**
     * 创建一个 Frame
     *
     * @param byteCodeFile 字节码文件
     * @return Frame实例
     * @throws Exception ex
     */
    static Frame createFrame(ByteCodeFile byteCodeFile) throws Exception {
        int frameType = byteCodeFile.readOneUint();
        MyLog.debug("frameType: "+frameType);
        Frame frame;
        if (frameType <= 63) {
            frame = new SameFrame();
        } else if (frameType <= 127) {
            frame = new SameLocalsOneStackItemFrame();
        } else if (frameType == 247) {
            frame = new SameLocalsOneStackItemFrameExtended();
        } else if (frameType >= 248 && frameType <= 250) {
            frame = new ChopFrame();
        } else if (frameType == 251) {
            frame = new SameFrameExtended();
        } else if (frameType >= 252 && frameType <= 254) {
            frame = new AppendFrame();
        }else if(frameType==255){
            frame=new FullFrame();
        } else {
            throw new Exception("Read Stack Map Frame Fail");
        }
        frame.read(byteCodeFile, frameType);
        return frame;
    }
}
