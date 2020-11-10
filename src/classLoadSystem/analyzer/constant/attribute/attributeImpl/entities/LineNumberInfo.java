package classLoadSystem.analyzer.constant.attribute.attributeImpl.entities;

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
}
