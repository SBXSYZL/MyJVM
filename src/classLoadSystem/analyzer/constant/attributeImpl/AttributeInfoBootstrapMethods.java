package classLoadSystem.analyzer.constant.attributeImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.AttributeInfo;
import classLoadSystem.analyzer.constant.ConstantPool;

/**
 * @author 22454
 */
public class AttributeInfoBootstrapMethods extends AttributeInfo {
    private int numBootstrapMethods;
    private BootstrapMethod[] bootstrapMethods;

    @Override
    public void readInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        this.numBootstrapMethods = byteCodeFile.readTwoUint();
        bootstrapMethods = new BootstrapMethod[this.numBootstrapMethods];
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

    public static class BootstrapMethod {
        private int bootstrapMethodRef;
        private int numBootstrapArguments;
        private int[] bootstrapArguments;

        public BootstrapMethod(int bootstrapMethodRef, int numBootstrapArguments, int[] bootstrapArguments) {
            this.bootstrapMethodRef = bootstrapMethodRef;
            this.numBootstrapArguments = numBootstrapArguments;
            this.bootstrapArguments = bootstrapArguments;
        }

        public int getBootstrapMethodRef() {
            return bootstrapMethodRef;
        }

        public int getNumBootstrapArguments() {
            return numBootstrapArguments;
        }

        public int[] getBootstrapArguments() {
            return bootstrapArguments;
        }
    }
}
