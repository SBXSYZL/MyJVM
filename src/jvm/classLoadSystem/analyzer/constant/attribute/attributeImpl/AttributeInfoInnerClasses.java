package jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl;

import jvm.classLoadSystem.analyzer.ByteCodeFile;
import jvm.classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import jvm.classLoadSystem.analyzer.constant.ConstantPool;

/**
 * @author 22454
 */
public class AttributeInfoInnerClasses extends AttributeInfo {
    private int numberOfClasses;
    private InnerClassesInfo[] innerClasses;
    private ConstantPool constantPool;

    public AttributeInfoInnerClasses(int attributeNameIndex, int attributeLength) {
        super(attributeNameIndex, attributeLength);
    }

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, int attributeLength, ConstantPool constantPool) throws Exception {
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

    public int getNumberOfClasses() {
        return numberOfClasses;
    }

    public InnerClassesInfo[] getInnerClasses() {
        return innerClasses;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
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
