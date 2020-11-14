package classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities;

/**
 * @author 22454
 */
public class LocalVariableTypeInfo {
    private int startPc;
    private int length;
    private int nameIndex;
    private int signatureIndex;
    private int index;

    public LocalVariableTypeInfo(int startPc, int length, int nameIndex, int signatureIndex, int index) {
        this.startPc = startPc;
        this.length = length;
        this.nameIndex = nameIndex;
        this.signatureIndex = signatureIndex;
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

    public int getSignatureIndex() {
        return signatureIndex;
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
                .append("SignatureIndex: ").append(signatureIndex).append("\n")
                .append("Index: ").append(index).append("\n");
        return builder.toString();
    }
}