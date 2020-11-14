package classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities;

/**
 * @author 22454
 */
public class ExceptionInfo {
    private int startPc;
    private int endPc;
    private int handlerPc;
    private int catchType;

    public ExceptionInfo(int startPc, int endPc, int handlerPc, int catchType) {

        this.startPc = startPc;
        this.endPc = endPc;
        this.handlerPc = handlerPc;
        this.catchType = catchType;
    }

    public int getStartPc() {
        return startPc;
    }

    public int getEndPc() {
        return endPc;
    }

    public int getHandlerPc() {
        return handlerPc;
    }

    public int getCatchType() {
        return catchType;
    }
}