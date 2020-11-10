package classLoadSystem.analyzer.constant.attribute.attributeImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.entities.Parameter;

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
}
