package com.myJvm.jvm.interpreter.instructions.impl.compareInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class DCmpG extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("dcmpg");
        OperandStack operandStack = frame.getOperandStack();
        double value1 = operandStack.popDouble();
        double value2 = operandStack.popDouble();

        if (value1 > value2) {
            operandStack.pushInteger(1);
            return;
        } else if ((value1 - value2) < 1e-6) {
            operandStack.pushInteger(0);
            return;
        } else if (value1 < value2) {
            operandStack.pushInteger(-1);
            return;
        }
        operandStack.pushInteger(1);
    }
}
