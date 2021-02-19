package com.myJvm.jvm.interpreter.instructions.impl.mathInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class IAnd extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("iand");
        OperandStack operandStack = frame.getOperandStack();
        int value1 = operandStack.popInteger();
        int value2 = operandStack.popInteger();
        int result = value1 & value2;
        operandStack.pushInteger(result);
    }
}
