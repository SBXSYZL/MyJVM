package com.myJvm.jvm.interpreter.instructions.impl.mathInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class LSub extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("lsub");
        OperandStack operandStack = frame.getOperandStack();
        Long longVal1 = operandStack.popLong();
        Long longVal2 = operandStack.popLong();
        long subResult = longVal1 - longVal2;
        operandStack.pushLong(subResult);
    }
}
