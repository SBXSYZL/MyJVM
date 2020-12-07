package jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl;

import jvm.classLoadSystem.analyzer.ByteCodeFile;
import jvm.classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import jvm.classLoadSystem.analyzer.constant.ConstantPool;
import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.*;

/**
 * @author 22454
 */
public class AttributeInfoBootstrapMethods implements AttributeInfo {
    private int numBootstrapMethods;
    private BootstrapMethod[] bootstrapMethods;
    private ConstantPool constantPool;

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        this.constantPool = constantPool;
        this.numBootstrapMethods = byteCodeFile.readTwoUint();
        this.bootstrapMethods = new BootstrapMethod[this.numBootstrapMethods];
        for (int i = 0; i < this.numBootstrapMethods; i++) {

            bootstrapMethods[i] = new BootstrapMethod(byteCodeFile);
        }
    }

    public int getNumBootstrapMethods() {
        return numBootstrapMethods;
    }

    public BootstrapMethod[] getBootstrapMethods() {
        return bootstrapMethods;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\t").append("BootstrapMethods: \n")
                .append("\t\t").append("Number Of BootstrapMethods: ").append(numBootstrapMethods).append("\n")
                .append("\t\t").append("BootstrapMethods: ").append("\n");
        for (BootstrapMethod bootstrapMethod : bootstrapMethods) {
            builder.append("\t\t\t").append("BootstrapMethod: \n")
                    .append("\t\t\t\t").append(bootstrapMethod).append("\n");
        }
        return builder.toString();
    }
}
