package com.myJvm.jvm.interpreter.instructions.impl.mathInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class LAdd extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("ladd");
        OperandStack operandStack = frame.getOperandStack();
        Long longVal1 = operandStack.popLong();
        Long longVal2 = operandStack.popLong();
        long addResult = longVal1 + longVal2;
        operandStack.pushLong(addResult);
    }
}
