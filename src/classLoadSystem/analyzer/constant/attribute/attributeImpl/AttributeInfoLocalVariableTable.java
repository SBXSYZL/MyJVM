package classLoadSystem.analyzer.constant.attribute.attributeImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.LocalVariableInfo;

/**
 * @author 22454
 */
public class AttributeInfoLocalVariableTable implements AttributeInfo {
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

    public int getLocalVariableTableLength() {
        return localVariableTableLength;
    }

    public LocalVariableInfo[] getLocalVariableTable() {
        return localVariableTable;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("LocalVariableTableLength: ").append(localVariableTableLength).append("\n")
                .append("LocalVariableTable: \n");
        for (LocalVariableInfo localVariableInfo : localVariableTable) {
            builder.append("\t").append(localVariableInfo).append("\n");
        }
        return builder.toString();
    }
}
