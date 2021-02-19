package com.myJvm.jvm.interpreter.instructions.impl.conversionInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class D2I extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("d2i");
        OperandStack operandStack = frame.getOperandStack();
        double value = operandStack.popDouble();
        int result = (int) value;
        operandStack.pushInteger(result);
    }
}
