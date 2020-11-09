package classLoadSystem.analyzer.constant.attributeImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.AttributeInfo;
import classLoadSystem.analyzer.constant.ConstantPool;

/**
 * @author 22454
 */
public class AttributeInfoCode extends AttributeInfo {
    private int maxStack;
    private int maxLocals;
    private long codeLength;
    private byte[] code;
    private int exceptionTableLength;
    private ExceptionInfo[] exceptionTable;
    private int attributeCount;
    private AttributeInfo[] attributes;


    @Override
    public void readInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        maxStack = byteCodeFile.readTwoUint();
        maxLocals = byteCodeFile.readTwoUint();
        codeLength = byteCodeFile.readFourUint();
        code = byteCodeFile.getByteByCnt((int) codeLength);
        exceptionTableLength = byteCodeFile.readTwoUint();
        exceptionTable = new ExceptionInfo[exceptionTableLength];
        for (int i = 0; i < exceptionTableLength; i++) {
            exceptionTable[i] = new ExceptionInfo(
                    byteCodeFile.readTwoUint(),
                    byteCodeFile.readTwoUint(),
                    byteCodeFile.readTwoUint(),
                    byteCodeFile.readTwoUint());
        }
        this.attributeCount = byteCodeFile.readTwoUint();
        this.attributes = AttributeInfo.readAttributes(byteCodeFile, attributeCount, constantPool);

    }

    private static class ExceptionInfo {
        private int startPC;
        private int endPC;
        private int handlerPC;
        private int catchType;

        public ExceptionInfo(int startPC, int endPC, int handlerPC, int catchType) {
            this.startPC = startPC;
            this.endPC = endPC;
            this.handlerPC = handlerPC;
            this.catchType = catchType;
        }

        public int getStartPC() {
            return startPC;
        }

        public int getEndPC() {
            return endPC;
        }

        public int getHandlerPC() {
            return handlerPC;
        }

        public int getCatchType() {
            return catchType;
        }
    }
}
