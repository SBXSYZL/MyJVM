package com.myJvm.jvm.interpreter.instructions.impl.mathInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class LUShR extends InstructionWithoutOperands {
    @Override
    public void exec() {
        //TODO 可能有BUG
        MyLog.command("lushr");
        OperandStack operandStack = frame.getOperandStack();
        int cnt = operandStack.popInteger();
        cnt &= 0x3f;
        long value = operandStack.popLong();
        long result = value >> cnt;
        operandStack.pushLong(result);
    }
}
