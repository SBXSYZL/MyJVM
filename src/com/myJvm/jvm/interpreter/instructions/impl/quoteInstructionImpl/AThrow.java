package com.myJvm.jvm.interpreter.instructions.impl.quoteInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.MyThread;
import com.myJvm.jvm.runtime.shared.heap.info.MyObject;
import com.myJvm.jvm.runtime.shared.heap.info.dependence.StringCache;
import com.myJvm.jvm.runtime.shared.mynative.impl.ThrowableNative;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.jvm.runtime.threaddependent.StackFrame;
import com.myJvm.log.MyLog;

@MyInstruction
public class AThrow extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("athrow");
        MyObject exception = (MyObject) frame.getOperandStack().popRef();
        if (null == exception) {
            throw new NullPointerException();
        }
        MyThread thread = frame.getThread();
        if (!findAndGotoExceptionHandler(thread, exception)) {
            handleUncaughtException(thread, exception);
        }
    }

    private void handleUncaughtException(MyThread thread, MyObject exception) {
        thread.clearStack();
        MyObject jMsg = exception.getRefVariable("detailMessage", "Ljava/lang/String;");
        String msg = StringCache.getString(jMsg);
        System.out.println(exception.getClazz().getJavaName() + ": " + msg);
        Object extra = exception.getExtra();
        ThrowableNative[] throwableNatives = (ThrowableNative[]) extra;
        for (ThrowableNative throwableNative : throwableNatives) {
            System.out.println(throwableNative.getString());
        }

    }

    private boolean findAndGotoExceptionHandler(MyThread thread, MyObject exception) {
        do {
            StackFrame frame = thread.getStackTopFrame();
            int pc = frame.getNextPc() - 1;
            int handlerPc = frame.getMethod().findExceptionHandler(exception.getClazz(), pc);
            if (handlerPc > 0) {
                OperandStack operandStack = frame.getOperandStack();
                operandStack.clear();
                operandStack.pushRef(exception);
                frame.setNextPc(handlerPc);
                return true;
            }
            thread.popStackFrame();
        } while (!thread.stackEmpty());
        return false;
    }
}
