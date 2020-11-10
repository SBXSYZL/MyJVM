package classLoadSystem.analyzer.constant.attribute.attributeImpl.entities;

/**
 * @author 22454
 */
public class Annotation {
    private int typeIndex;
    private int numElementValuePairs;
    private ElementValuePair[] elementValuePairs;

    public Annotation(int typeIndex, int numElementValuePairs, ElementValuePair[] elementValuePairs) {
        this.typeIndex = typeIndex;
        this.numElementValuePairs = numElementValuePairs;
        this.elementValuePairs = elementValuePairs;
    }
}
