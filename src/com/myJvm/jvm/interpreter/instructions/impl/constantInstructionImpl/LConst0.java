package com.myJvm.jvm.interpreter.instructions.impl.constantInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.log.MyLog;

@MyInstruction
public class LConst0 extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("lconst_0");
        frame.getOperandStack().pushLong(0L);
    }
}
