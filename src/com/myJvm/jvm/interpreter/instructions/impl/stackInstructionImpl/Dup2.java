package com.myJvm.jvm.interpreter.instructions.impl.stackInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.jvm.runtime.threaddependent.VariableSlot;
import com.myJvm.log.MyLog;

@MyInstruction
public class Dup2 extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("dup2");
        OperandStack operandStack = frame.getOperandStack();
        VariableSlot variableSlot1 = operandStack.popVariableSlot();
        VariableSlot variableSlot2 = operandStack.popVariableSlot();
        operandStack.pushVariableSlot(variableSlot2);
        operandStack.pushVariableSlot(variableSlot1);
        operandStack.pushVariableSlot(variableSlot2);
        operandStack.pushVariableSlot(variableSlot1);
    }
}
