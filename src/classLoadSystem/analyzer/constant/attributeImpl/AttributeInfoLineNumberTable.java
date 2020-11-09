package classLoadSystem.analyzer.constant.attributeImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.AttributeInfo;
import classLoadSystem.analyzer.constant.ConstantPool;

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
            this.lineNumberTable[i] = new LineNumberInfo(byteCodeFile.readTwoUint(), byteCodeFile.readTwoUint());
        }
    }

    public int getLineNumber(int pc) {
        for (int i = this.lineNumberTableLength - 1; i >= 0; i--) {
            LineNumberInfo lineNumberInfo = this.lineNumberTable[i];
            if (pc > lineNumberInfo.getStartPc()) {
                return lineNumberInfo.getLineNumber();
            }
        }
        return -1;
    }

    public static class LineNumberInfo {
        private int startPc;
        private int lineNumber;

        public LineNumberInfo(int startPc, int lineNumber) {
            this.startPc = startPc;
            this.lineNumber = lineNumber;
        }

        public int getStartPc() {
            return startPc;
        }

        public int getLineNumber() {
            return lineNumber;
        }
    }
}
