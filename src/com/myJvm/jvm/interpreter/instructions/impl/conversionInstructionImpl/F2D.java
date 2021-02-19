package com.myJvm.jvm.interpreter.instructions.impl.conversionInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class F2D extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("f2d");
        OperandStack operandStack = frame.getOperandStack();
        float value = operandStack.popFloat();
        operandStack.pushDouble((double) value);
    }
}
