package com.myJvm.jvm.interpreter.instructions.impl.conversionInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class L2F extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("l2f");
        OperandStack operandStack = frame.getOperandStack();
        long value = operandStack.popLong();
        float result = (float) value;
        operandStack.pushFloat(result);
    }
}
