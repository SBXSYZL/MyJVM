package com.myJvm.jvm.interpreter.instructions.impl.constantInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.log.MyLog;

@MyInstruction
public class FConst2 extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("fconst_2");
        frame.getOperandStack().pushFloat(2.0f);
    }
}
