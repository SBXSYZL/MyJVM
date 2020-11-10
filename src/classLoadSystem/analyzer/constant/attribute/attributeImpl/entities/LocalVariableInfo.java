package classLoadSystem.analyzer.constant.attribute.attributeImpl.entities;

/**
 * @author 22454
 */
public class LocalVariableInfo {
    private int startPc;
    private int length;
    private int nameIndex;
    private int descriptorIndex;
    private int index;

    public LocalVariableInfo(int startPc, int length, int nameIndex, int descriptorIndex, int index) {
        this.startPc = startPc;
        this.length = length;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.index = index;
    }

    public int getStartPc() {
        return startPc;
    }

    public int getLength() {
        return length;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public int getIndex() {
        return index;
    }
}
