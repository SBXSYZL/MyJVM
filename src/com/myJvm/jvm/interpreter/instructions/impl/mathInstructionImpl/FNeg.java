package com.myJvm.jvm.interpreter.instructions.impl.mathInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class FNeg extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("fneg");
        OperandStack operandStack = frame.getOperandStack();
        float val = operandStack.popFloat();
        val = -val;
        operandStack.pushFloat(val);
    }
}
