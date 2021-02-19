package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;

/**
 * @author 22454
 */
public class BootstrapMethod {
    private int bootstrapMethodRef;
    private int numBootstrapArguments;
    private int[] bootstrapArguments;

    public BootstrapMethod(ByteCodeFile byteCodeFile) {
        this.bootstrapMethodRef = byteCodeFile.readTwoUint();
        this.numBootstrapArguments = byteCodeFile.readTwoUint();
        this.bootstrapArguments = new int[numBootstrapArguments];
        for (int i = 0; i < numBootstrapArguments; i++) {
            bootstrapArguments[i] = byteCodeFile.readTwoUint();
        }
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Number Of Bootstrap Arguments: ").append(numBootstrapArguments).append("\n")
                .append("Bootstrap Method Ref: ").append(bootstrapMethodRef).append("\n")
                .append("Bootstrap Arguments: \n");
        for (int bootstrapArgument : bootstrapArguments) {
            builder.append("\t").append(bootstrapArgument).append("\n");
        }
        return builder.toString();
    }
}
