package com.myJvm.jvm.interpreter.instructions.impl.constantInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.log.MyLog;

@MyInstruction
public class LConst1 extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("lconst_1");
        frame.getOperandStack().pushLong(1L);
    }
}
