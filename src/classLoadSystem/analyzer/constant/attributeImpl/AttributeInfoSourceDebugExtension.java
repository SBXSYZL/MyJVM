package classLoadSystem.analyzer.constant.attributeImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.AttributeInfo;
import classLoadSystem.analyzer.constant.ConstantPool;

/**
 * @author 22454
 */
@Deprecated
public class AttributeInfoSourceDebugExtension extends AttributeInfo {
    private byte debugExtensionLength;
    private String[] debugExtension;

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        debugExtensionLength = byteCodeFile.readOneUint();

    }
}
