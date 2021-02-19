package com.myJvm.jvm.interpreter.instructions.impl.mathInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class INeg extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("ineg");
        OperandStack operandStack = frame.getOperandStack();
        Integer val = operandStack.popInteger();
        val = -val;
        operandStack.pushInteger(val);
    }
}
