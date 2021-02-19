package com.myJvm.jvm.interpreter.instructions.impl.mathInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class DRem extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("drem");
        OperandStack operandStack = frame.getOperandStack();
        double val1 = operandStack.popDouble();
        double val2 = operandStack.popDouble();
        double remResult = val1 % val2;
        operandStack.pushDouble(remResult);
    }
}
