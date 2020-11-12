package classLoadSystem.analyzer.constant.attribute.attributeImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.entities.LocalVariableInfo;

/**
 * @author 22454
 */
public class AttributeInfoLocalVariableTable extends AttributeInfo {
    private int localVariableTableLength;
    private LocalVariableInfo[] localVariableTable;
    private ConstantPool constantPool;

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        this.constantPool = constantPool;
        this.localVariableTableLength = byteCodeFile.readTwoUint();
        this.localVariableTable = new LocalVariableInfo[this.localVariableTableLength];
        for (int i = 0; i < this.localVariableTableLength; i++) {
            this.localVariableTable[i] = new LocalVariableInfo(
                    byteCodeFile.readTwoUint(),
                    byteCodeFile.readTwoUint(),
                    byteCodeFile.readTwoUint(),
                    byteCodeFile.readTwoUint(),
                    byteCodeFile.readTwoUint()
            );
        }
    }
}
