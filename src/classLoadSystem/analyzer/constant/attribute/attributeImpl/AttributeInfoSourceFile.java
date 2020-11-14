package classLoadSystem.analyzer.constant.attribute.attributeImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import classLoadSystem.analyzer.constant.ConstantPool;

/**
 * @author 22454
 */
public class AttributeInfoSourceFile implements AttributeInfo {
    private int sourceFileIndex;
    private ConstantPool constantPool;

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        this.constantPool = constantPool;
        this.sourceFileIndex = byteCodeFile.readTwoUint();
    }

    public int getSourceFileIndex() {
        return sourceFileIndex;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    @Override
    public String toString() {
        try {
            return "SourceFileIndex: " + sourceFileIndex + "\t" + "   --->   " + constantPool.getUtf8(sourceFileIndex) + "\n";
        } catch (Exception e) {
            e.printStackTrace();
            return "\n";
        }
    }
}
