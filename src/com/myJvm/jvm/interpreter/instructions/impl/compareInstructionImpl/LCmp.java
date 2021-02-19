package com.myJvm.jvm.interpreter.instructions.impl.compareInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class LCmp extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("l_cmp");
        OperandStack operandStack = frame.getOperandStack();
        long value1 = operandStack.popLong();
        long value2 = operandStack.popLong();
        int result;
        if (value1 > value2) {
            result = 1;
        } else if (value1 == value2) {
            result = 0;
        } else {
            result = -1;
        }
        operandStack.pushInteger(result);
    }
}
