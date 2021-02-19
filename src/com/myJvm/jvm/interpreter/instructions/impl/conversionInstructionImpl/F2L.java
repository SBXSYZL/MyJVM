package com.myJvm.jvm.interpreter.instructions.impl.conversionInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class F2L extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("f2l");
        OperandStack operandStack = frame.getOperandStack();
        float value = operandStack.popFloat();
        long result = (long) value;
        operandStack.pushLong(result);
    }
}
