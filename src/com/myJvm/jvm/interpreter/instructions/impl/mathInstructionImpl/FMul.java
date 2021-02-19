package com.myJvm.jvm.interpreter.instructions.impl.mathInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class FMul extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("fmul");
        OperandStack operandStack = frame.getOperandStack();
        float val1 = operandStack.popFloat();
        float val2 = operandStack.popFloat();
        float mulResult = val1 * val2;
        operandStack.pushFloat(mulResult);
    }
}
