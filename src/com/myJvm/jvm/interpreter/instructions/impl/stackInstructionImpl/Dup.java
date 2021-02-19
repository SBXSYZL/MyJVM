package com.myJvm.jvm.interpreter.instructions.impl.stackInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.jvm.runtime.threaddependent.VariableSlot;
import com.myJvm.log.MyLog;

@MyInstruction
public class Dup extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("dup");
        OperandStack operandStack = frame.getOperandStack();
        VariableSlot variableSlot = operandStack.popVariableSlot();
        operandStack.pushVariableSlot(variableSlot);
        operandStack.pushVariableSlot(variableSlot);
    }
}
