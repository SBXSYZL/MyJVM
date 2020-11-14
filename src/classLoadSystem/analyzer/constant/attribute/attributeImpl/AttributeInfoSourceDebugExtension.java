package classLoadSystem.analyzer.constant.attribute.attributeImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import classLoadSystem.analyzer.constant.ConstantPool;

/**
 * @author 22454
 */

public class AttributeInfoSourceDebugExtension implements AttributeInfo {
    private int debugExtensionLength;
    private int[] debugExtension;//TODO 可能有错，类型
    private ConstantPool constantPool;

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        this.constantPool = constantPool;
        debugExtensionLength = byteCodeFile.readOneUint();
        debugExtension = new int[this.debugExtensionLength];
        for (int i = 0; i < this.debugExtensionLength; i++) {
            debugExtension[i] = byteCodeFile.readOneUint();
        }

    }
}
