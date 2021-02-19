package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;
import com.myJvm.jvm.loadcore.analyzer.constant.ConstantPool;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.AttributeInfo;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.LocalVariableTypeInfo;

/**
 * @author 22454
 */
public class AttributeInfoLocalVariableTypeTable extends AttributeInfo {
    private int localVariableTypeTableLength;
    private LocalVariableTypeInfo[] localVariableTypeTable;
    private ConstantPool constantPool;

    public AttributeInfoLocalVariableTypeTable(int attributeNameIndex, int attributeLength) {
        super(attributeNameIndex, attributeLength);
    }

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, int attributeLength, ConstantPool constantPool) throws Exception {
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
