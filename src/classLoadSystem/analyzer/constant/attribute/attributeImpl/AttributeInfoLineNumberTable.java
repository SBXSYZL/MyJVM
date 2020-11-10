package classLoadSystem.analyzer.constant.attribute.attributeImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.entities.LineNumberInfo;

/**
 * @author 22454
 */
public class AttributeInfoLineNumberTable extends AttributeInfo {
    private int lineNumberTableLength;
    private LineNumberInfo[] lineNumberTable;

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
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
}
