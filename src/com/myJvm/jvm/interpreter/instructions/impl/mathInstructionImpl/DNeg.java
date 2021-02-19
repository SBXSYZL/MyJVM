package com.myJvm.jvm.interpreter.instructions.impl.mathInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class DNeg extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("dneg");
        OperandStack operandStack = frame.getOperandStack();
        double val = operandStack.popDouble();
        val = -val;
        operandStack.pushDouble(val);
    }
}
