package classLoadSystem.analyzer.constant.attribute.attributeImpl.entities;

/**
 * @author 22454
 */
public class ExceptionInfo {
    private int startPC;
    private int endPC;
    private int handlerPC;
    private int catchType;

    public ExceptionInfo(int startPC, int endPC, int handlerPC, int catchType) {
        this.startPC = startPC;
        this.endPC = endPC;
        this.handlerPC = handlerPC;
        this.catchType = catchType;
    }

    public int getStartPC() {
        return startPC;
    }

    public int getEndPC() {
        return endPC;
    }

    public int getHandlerPC() {
        return handlerPC;
    }

    public int getCatchType() {
        return catchType;
    }
}