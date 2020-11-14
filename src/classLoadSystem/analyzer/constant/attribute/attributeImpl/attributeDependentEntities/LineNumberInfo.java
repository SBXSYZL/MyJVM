package classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities;

/**
 * @author 22454
 */
public class LineNumberInfo {
    private int startPc;
    private int lineNumber;

    public LineNumberInfo(int startPc, int lineNumber) {
        this.startPc = startPc;
        this.lineNumber = lineNumber;
    }

    public int getStartPc() {
        return startPc;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("StartPc: ").append(startPc).append("\n")
                .append("LineNumber: ").append(lineNumber).append("\n");
        return builder.toString();
    }
}
