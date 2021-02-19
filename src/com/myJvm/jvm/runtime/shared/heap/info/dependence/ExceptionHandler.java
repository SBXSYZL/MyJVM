package com.myJvm.jvm.runtime.shared.heap.info.dependence;

import com.myJvm.jvm.runtime.shared.heap.info.ref.MyClassRef;

/**
 * @author 22454
 */
public class ExceptionHandler {
    private int startPc;
    private int endPc;
    private int handlePc;
    private MyClassRef catchType;

    public ExceptionHandler(int startPc, int endPc, int handlePc, MyClassRef catchType) {
        this.startPc = startPc;
        this.endPc = endPc;
        this.handlePc = handlePc;
        this.catchType = catchType;
    }

    public int getStartPc() {
        return startPc;
    }

    public int getEndPc() {
        return endPc;
    }

    public int getHandlePc() {
        return handlePc;
    }

    public MyClassRef getCatchType() {
        return catchType;
    }
}
