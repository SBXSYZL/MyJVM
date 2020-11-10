package classLoadSystem.analyzer.constant.attribute.attributeImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import classLoadSystem.analyzer.constant.ConstantPool;

/**
 * @author 22454
 */

public class AttributeInfoSourceDebugExtension extends AttributeInfo {
    private byte debugExtensionLength;
    private String[] debugExtension;

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        debugExtensionLength = byteCodeFile.readOneUint();

    }
}
