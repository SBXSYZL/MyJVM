package com.myJvm.jvm.interpreter.instructions.impl.mathInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class FRem extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("frem");
        OperandStack operandStack = frame.getOperandStack();
        float val1 = operandStack.popFloat();
        float val2 = operandStack.popFloat();
        float remResult = val1 % val2;
        operandStack.pushFloat(remResult);
    }
}
