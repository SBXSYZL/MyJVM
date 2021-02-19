package com.myJvm.jvm.interpreter.instructions.impl.stackInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.log.MyLog;

@MyInstruction
public class Pop extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("pop");
        frame.getOperandStack().popVariableSlot();
    }
}
