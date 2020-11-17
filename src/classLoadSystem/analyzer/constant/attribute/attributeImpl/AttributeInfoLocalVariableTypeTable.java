package classLoadSystem.analyzer.constant.attribute.attributeImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.LocalVariableTypeInfo;

/**
 * @author 22454
 */
public class AttributeInfoLocalVariableTypeTable implements AttributeInfo {
    private int localVariableTypeTableLength;
    private LocalVariableTypeInfo[] localVariableTypeTable;
    private ConstantPool constantPool;

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        this.constantPool = constantPool;
        this.localVariableTypeTableLength = byteCodeFile.readTwoUint();
        this.localVariableTypeTable = new LocalVariableTypeInfo[this.localVariableTypeTableLength];
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

    public int getLocalVariableTypeTableLength() {
        return localVariableTypeTableLength;
    }

    public LocalVariableTypeInfo[] getLocalVariableTypeTable() {
        return localVariableTypeTable;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("LocalVariableTypeTableLength: ").append(localVariableTypeTableLength).append("\n")
                .append("LocalVariableTypeTable: ").append("\n");
        for (LocalVariableTypeInfo localVariableTypeInfo : localVariableTypeTable) {
            builder.append("\t").append(localVariableTypeInfo).append("\n");
        }
        return builder.toString();
    }
}
