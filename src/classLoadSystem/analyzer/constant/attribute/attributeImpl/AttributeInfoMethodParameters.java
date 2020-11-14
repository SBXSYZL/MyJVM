package classLoadSystem.analyzer.constant.attribute.attributeImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.Parameter;

/**
 * @author 22454
 */
public class AttributeInfoMethodParameters implements AttributeInfo {
    private int parametersCount;
    private Parameter[] parameters;
    private ConstantPool constantPool;

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        this.constantPool = constantPool;
        this.parametersCount = byteCodeFile.readOneUint();
        this.parameters = new Parameter[this.parametersCount];
        for (int i = 0; i < this.parametersCount; i++) {
            this.parameters[i] = new Parameter(byteCodeFile.readTwoUint(), byteCodeFile.readTwoUint());
        }
    }

    public int getParametersCount() {
        return parametersCount;
    }

    public Parameter[] getParameters() {
        return parameters;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ParametersCount: ").append(parametersCount).append("\n")
                .append("Parameters: ").append("\n");
        for (Parameter parameter : parameters) {
            builder.append("\t").append(parameter).append("\n");
        }
        return builder.toString();
    }
}
