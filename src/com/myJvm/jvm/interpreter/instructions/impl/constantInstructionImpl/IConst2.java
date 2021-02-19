package com.myJvm.jvm.interpreter.instructions.impl.constantInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.log.MyLog;

@MyInstruction
public class IConst2 extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("iconst_2");
        frame.getOperandStack().pushInteger(2);
    }
}
