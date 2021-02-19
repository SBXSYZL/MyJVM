package com.myJvm.jvm.interpreter.instructions.impl.controlInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.MyThread;
import com.myJvm.jvm.runtime.threaddependent.StackFrame;
import com.myJvm.log.MyLog;

@MyInstruction
public class LReturn extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("lreturn");
        MyThread thread = frame.getThread();
        StackFrame currentFrame = thread.popStackFrame();
        StackFrame invokeFrame = thread.getStackTopFrame();
        long value = currentFrame.getOperandStack().popLong();
        invokeFrame.getOperandStack().pushLong(value);
    }
}
