package com.myJvm.jvm.interpreter.instructions.impl.mathInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class ISub extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("isub");
        OperandStack operandStack = frame.getOperandStack();
        Integer integer1 = operandStack.popInteger();
        Integer integer2 = operandStack.popInteger();
        int subResult = integer1 - integer2;
        operandStack.pushInteger(subResult);
    }
}
