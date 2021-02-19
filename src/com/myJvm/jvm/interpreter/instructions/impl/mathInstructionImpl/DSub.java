package com.myJvm.jvm.interpreter.instructions.impl.mathInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class DSub extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("dsub");
        OperandStack operandStack = frame.getOperandStack();
        double doubleVal1 = operandStack.popDouble();
        double doubleVal2 = operandStack.popLong();
        double subResult = doubleVal1 - doubleVal2;
        operandStack.pushDouble(subResult);
    }
}
