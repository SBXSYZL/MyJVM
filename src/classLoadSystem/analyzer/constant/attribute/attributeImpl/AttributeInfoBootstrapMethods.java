package classLoadSystem.analyzer.constant.attribute.attributeImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.entities.*;

/**
 * @author 22454
 */
public class AttributeInfoBootstrapMethods extends AttributeInfo {
    private int numBootstrapMethods;
    private BootstrapMethod[] bootstrapMethods;
    private ConstantPool constantPool;

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        this.constantPool = constantPool;
        this.numBootstrapMethods = byteCodeFile.readTwoUint();
        this.bootstrapMethods = new BootstrapMethod[this.numBootstrapMethods];
        for (int i = 0; i < this.numBootstrapMethods; i++) {
            int bootstrapMethodRef = byteCodeFile.readTwoUint();
            int numBootstrapArguments = byteCodeFile.readTwoUint();
            int[] bootstrapArguments = new int[numBootstrapArguments];
            for (int j = 0; j < numBootstrapArguments; j++) {
                bootstrapArguments[i] = byteCodeFile.readTwoUint();
            }
            bootstrapMethods[i] = new BootstrapMethod(bootstrapMethodRef, numBootstrapArguments, bootstrapArguments);
        }
    }

}
