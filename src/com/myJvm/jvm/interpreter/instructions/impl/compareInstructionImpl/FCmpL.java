package com.myJvm.jvm.interpreter.instructions.impl.compareInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class FCmpL extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("f_cmpL");
        OperandStack operandStack = frame.getOperandStack();
        double value1 = operandStack.popFloat();
        double value2 = operandStack.popFloat();
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
        operandStack.pushInteger(-1);
    }
}
