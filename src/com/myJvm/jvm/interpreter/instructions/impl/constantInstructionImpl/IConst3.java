package com.myJvm.jvm.interpreter.instructions.impl.constantInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.log.MyLog;

@MyInstruction
public class IConst3 extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("iconst_3");
        frame.getOperandStack().pushInteger(3);
    }
}
