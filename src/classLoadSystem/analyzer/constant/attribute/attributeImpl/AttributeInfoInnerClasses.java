package classLoadSystem.analyzer.constant.attribute.attributeImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import classLoadSystem.analyzer.constant.ConstantPool;

/**
 * @author 22454
 */
public class AttributeInfoInnerClasses extends AttributeInfo {
    private int numberOfClasses;
    private InnerClassesInfo[] innerClasses;
    private ConstantPool constantPool;

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        this.constantPool = constantPool;
        this.numberOfClasses = byteCodeFile.readTwoUint();
        this.innerClasses = new InnerClassesInfo[this.numberOfClasses];
        for (int i = 0; i < this.numberOfClasses; i++) {
            this.innerClasses[i] = new InnerClassesInfo(
                    byteCodeFile.readTwoUint(),
                    byteCodeFile.readTwoUint(),
                    byteCodeFile.readTwoUint(),
                    byteCodeFile.readTwoUint()
            );
        }

    }

    public static class InnerClassesInfo {
        private int innerClassInfoIndex;
        private int outerClassInfoIndex;
        private int innerNameIndex;
        private int innerClassAccessFlags;

        public InnerClassesInfo(int innerClassInfoIndex, int outerClassInfoIndex, int innerNameIndex, int innerClassAccessFlags) {
            this.innerClassInfoIndex = innerClassInfoIndex;
            this.outerClassInfoIndex = outerClassInfoIndex;
            this.innerNameIndex = innerNameIndex;
            this.innerClassAccessFlags = innerClassAccessFlags;
        }

        public int getInnerClassInfoIndex() {
            return innerClassInfoIndex;
        }

        public int getOuterClassInfoIndex() {
            return outerClassInfoIndex;
        }

        public int getInnerNameIndex() {
            return innerNameIndex;
        }

        public int getInnerClassAccessFlags() {
            return innerClassAccessFlags;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("\tInnerClassInfoIndex: ").append(innerClassInfoIndex).append("\n")
                    .append("\tOuterClassInfoIndex: ").append(outerClassInfoIndex).append("\n")
                    .append("\tInnerNameIndex: ").append(innerNameIndex).append("\n")
                    .append("\tInnerClassAccessFlags: ").append(innerClassAccessFlags).append("\n");
            return builder.toString();
        }
    }
}
