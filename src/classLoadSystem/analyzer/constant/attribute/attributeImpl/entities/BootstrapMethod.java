package classLoadSystem.analyzer.constant.attribute.attributeImpl.entities;

/**
 * @author 22454
 */
public class BootstrapMethod {
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
