package classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities;

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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("StartPc: ").append(startPc).append("\n")
                .append("Length: ").append(length).append("\n")
                .append("NameIndex: ").append(nameIndex).append("\n")
                .append("DescriptorIndex: ").append(descriptorIndex).append("\n")
                .append("Index: ").append(index).append("\n");
        return builder.toString();
    }
}
