package com.myJvm.jvm.interpreter.instructions.impl.mathInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class IUShR extends InstructionWithoutOperands {
    @Override
    public void exec() {
        //TODO 可能有BUG
        MyLog.command("iushr");
        OperandStack operandStack = frame.getOperandStack();
        int cnt = operandStack.popInteger();
        cnt &= 0x1f;
        int value = operandStack.popInteger();
        int result = value >> cnt;
        operandStack.pushInteger(result);
    }
}
