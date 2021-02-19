package com.myJvm.jvm.interpreter.instructions.impl.mathInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class LNeg extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("lneg");
        OperandStack operandStack = frame.getOperandStack();
        long val = operandStack.popLong();
        val = -val;
        operandStack.pushLong(val);
    }
}
