package classLoadSystem.analyzer.constant.attribute.attributeImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.LineNumberInfo;

/**
 * @author 22454
 */
public class AttributeInfoLineNumberTable implements AttributeInfo {
    private int lineNumberTableLength;
    private LineNumberInfo[] lineNumberTable;
    private ConstantPool constantPool;

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        this.constantPool = constantPool;
        this.lineNumberTableLength = byteCodeFile.readTwoUint();
        this.lineNumberTable = new LineNumberInfo[this.lineNumberTableLength];
        for (int i = 0; i < this.lineNumberTableLength; i++) {
            lineNumberTable[i] = new LineNumberInfo(byteCodeFile.readTwoUint(), byteCodeFile.readTwoUint());
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
