package com.myJvm.jvm.interpreter.instructions.impl.stackInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class Pop2 extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("pop2");
        OperandStack operandStack = frame.getOperandStack();
        operandStack.popVariableSlot();
        operandStack.popVariableSlot();
    }
}
