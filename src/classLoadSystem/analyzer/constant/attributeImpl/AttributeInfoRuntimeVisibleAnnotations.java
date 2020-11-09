package classLoadSystem.analyzer.constant.attributeImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.AttributeInfo;
import classLoadSystem.analyzer.constant.ConstantPool;

/**
 * @author 22454
 */
public class AttributeInfoRuntimeVisibleAnnotations extends AttributeInfo {
    private int numAnnotations;


    @Override
    public void readInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {

    }

    public static class Annotation {
        private int typeIndex;
        private int numElementValuePairs;
        private ElementValuePair[] elementValuePairs;

        public Annotation(int typeIndex, int numElementValuePairs, ElementValuePair[] elementValuePairs) {
            this.typeIndex = typeIndex;
            this.numElementValuePairs = numElementValuePairs;
            this.elementValuePairs = elementValuePairs;
        }
    }

    public static class ElementValuePair {
        private int key;
        private int value;

        public ElementValuePair(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static class ElementValue {

    }
}
