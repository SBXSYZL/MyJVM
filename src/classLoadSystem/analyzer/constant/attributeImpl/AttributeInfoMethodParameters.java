package classLoadSystem.analyzer.constant.attributeImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.AttributeInfo;
import classLoadSystem.analyzer.constant.ConstantPool;

/**
 * @author 22454
 */
public class AttributeInfoMethodParameters extends AttributeInfo {
    private int parametersCount;
    private Parameter[] parameters;

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        this.parametersCount = byteCodeFile.readOneUint();
        this.parameters = new Parameter[this.parametersCount];
        for (int i = 0; i < this.parametersCount; i++) {
            this.parameters[i] = new Parameter(byteCodeFile.readTwoUint(), byteCodeFile.readTwoUint());
        }
    }

    public static class Parameter {
        private int nameIndex;
        private int accessFlags;

        public Parameter(int nameIndex, int accessFlags) {
            this.nameIndex = nameIndex;
            this.accessFlags = accessFlags;
        }

        public int getNameIndex() {
            return nameIndex;
        }

        public int getAccessFlags() {
            return accessFlags;
        }
    }
}
