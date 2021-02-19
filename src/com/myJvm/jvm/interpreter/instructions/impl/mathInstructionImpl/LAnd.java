package com.myJvm.jvm.interpreter.instructions.impl.mathInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class LAnd extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("land");
        OperandStack operandStack = frame.getOperandStack();
        long value1 = operandStack.popLong();
        long value2 = operandStack.popLong();
        long result = value1 & value2;
        operandStack.pushLong(result);
    }
}
