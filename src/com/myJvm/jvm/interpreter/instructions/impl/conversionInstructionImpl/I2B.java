package com.myJvm.jvm.interpreter.instructions.impl.conversionInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class I2B extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("i2b");
        OperandStack operandStack = frame.getOperandStack();
        int value = operandStack.popInteger();
        int result = (byte) value;
        operandStack.pushInteger(result);
    }
}
