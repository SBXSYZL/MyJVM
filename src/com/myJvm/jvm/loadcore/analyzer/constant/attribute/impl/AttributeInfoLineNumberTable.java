package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;
import com.myJvm.jvm.loadcore.analyzer.constant.ConstantPool;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.AttributeInfo;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.LineNumberInfo;

/**
 * @author 22454
 */
public class AttributeInfoLineNumberTable extends AttributeInfo {
    private int lineNumberTableLength;
    private LineNumberInfo[] lineNumberTable;
    private ConstantPool constantPool;

    public AttributeInfoLineNumberTable(int attributeNameIndex, int attributeLength) {
        super(attributeNameIndex, attributeLength);
    }

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, int attributeLength, ConstantPool constantPool) throws Exception {
        this.constantPool = constantPool;
        this.lineNumberTableLength = byteCodeFile.readTwoUint();
        this.lineNumberTable = new LineNumberInfo[this.lineNumberTableLength];
        for (int i = 0; i < this.lineNumberTableLength; i++) {
            lineNumberTable[i] = new LineNumberInfo(
                    byteCodeFile.readTwoUint(),
                    byteCodeFile.readTwoUint()
            );
        }
    }

    public int getLineNumber(int pc) {
        for (int i = this.lineNumberTableLength - 1; i >= 0; i--) {
            LineNumberInfo lineNumberInfo = lineNumberTable[i];
            if (pc > lineNumberInfo.getStartPc()) {
                return lineNumberInfo.getLineNumber();
            }
        }
        return -1;
    }

    public int getLineNumberTableLength() {
        return lineNumberTableLength;
    }

    public LineNumberInfo[] getLineNumberTable() {
        return lineNumberTable;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("LineNumberTableLength: ").append(lineNumberTableLength).append("\n")
                .append("LineNumberTable: \n");
        for (LineNumberInfo lineNumberInfo : lineNumberTable) {
            builder.append("\t").append(lineNumberInfo).append("\n");
        }
        return builder.toString();
    }
}
