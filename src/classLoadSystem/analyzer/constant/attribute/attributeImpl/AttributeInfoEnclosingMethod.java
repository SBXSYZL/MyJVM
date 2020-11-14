package classLoadSystem.analyzer.constant.attribute.attributeImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.attribute.AttributeInfo;

/**
 * @author 22454
 */
public class AttributeInfoEnclosingMethod implements AttributeInfo {
    private int classIndex;
    private int methodIndex;
    private ConstantPool constantPool;

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        this.classIndex = byteCodeFile.readTwoUint();
        this.methodIndex = byteCodeFile.readTwoUint();
        this.constantPool = constantPool;
    }

    public int getClassIndex() {
        return classIndex;
    }

    public int getMethodIndex() {
        return methodIndex;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    @Override
    public String toString() {
        try {
            StringBuilder builder = new StringBuilder();
            builder.append("Class Index: ").append(classIndex).append("   --->    ").append(constantPool.getClassName(classIndex)).append("\n")
                    .append("Method Index: ").append(methodIndex).append("\n");
            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
