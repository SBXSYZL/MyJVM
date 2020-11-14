package classLoadSystem.analyzer.constant.attribute.attributeImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import classLoadSystem.analyzer.constant.ConstantPool;

/**
 * @author 22454
 */
public class AttributeInfoInnerClasses implements AttributeInfo {
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
                    byteCodeFile.readTwoUint(),
                    this.constantPool
            );
        }

    }

    public static class InnerClassesInfo {
        private int innerClassInfoIndex;
        private int outerClassInfoIndex;
        private int innerNameIndex;
        private int innerClassAccessFlags;
        private ConstantPool constantPool;

        public InnerClassesInfo(int innerClassInfoIndex, int outerClassInfoIndex, int innerNameIndex, int innerClassAccessFlags, ConstantPool constantPool) {
            this.innerClassInfoIndex = innerClassInfoIndex;
            this.outerClassInfoIndex = outerClassInfoIndex;
            this.innerNameIndex = innerNameIndex;
            this.innerClassAccessFlags = innerClassAccessFlags;
            this.constantPool = constantPool;
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
            try {
                return "InnerClassInfoIndex: " + innerClassInfoIndex + "\n" +
                        "\tOuterClassInfoIndex: " + outerClassInfoIndex + "\n" +
                        "\tInnerNameIndex: " + innerNameIndex + "\n" +
                        "\tInnerClassAccessFlags: " + innerClassAccessFlags + "\n";
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("NumberOfClasses: ").append(numberOfClasses).append("\n")
                .append("InnerClasses: \n");
        for (InnerClassesInfo innerClass : innerClasses) {
            builder.append("\t").append(innerClass).append("\n");
        }
        return builder.toString();
    }
}
