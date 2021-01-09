package jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl;

import jvm.classLoadSystem.analyzer.ByteCodeFile;
import jvm.classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import jvm.classLoadSystem.analyzer.constant.ConstantPool;
import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.LocalVariableInfo;

/**
 * @author 22454
 */
public class AttributeInfoLocalVariableTable extends AttributeInfo {
    private int localVariableTableLength;
    private LocalVariableInfo[] localVariableTable;
    private ConstantPool constantPool;

    public AttributeInfoLocalVariableTable(int attributeNameIndex, int attributeLength) {
        super(attributeNameIndex, attributeLength);
    }

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, int attributeLength, ConstantPool constantPool) throws Exception {
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
