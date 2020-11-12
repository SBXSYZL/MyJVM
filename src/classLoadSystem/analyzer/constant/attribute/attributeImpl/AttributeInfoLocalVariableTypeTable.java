package classLoadSystem.analyzer.constant.attribute.attributeImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.entities.LocalVariableTypeInfo;

/**
 * @author 22454
 */
public class AttributeInfoLocalVariableTypeTable extends AttributeInfo {
    private int localVariableTypeTableLength;
    private LocalVariableTypeInfo[] localVariableTypeTable;
    private ConstantPool constantPool;

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        this.constantPool = constantPool;
        this.localVariableTypeTableLength = byteCodeFile.readTwoUint();
        for (int i = 0; i < this.localVariableTypeTableLength; i++) {
            this.localVariableTypeTable[i] = new LocalVariableTypeInfo(
                    byteCodeFile.readTwoUint(),
                    byteCodeFile.readTwoUint(),
                    byteCodeFile.readTwoUint(),
                    byteCodeFile.readTwoUint(),
                    byteCodeFile.readTwoUint()
            );
        }
    }
}
