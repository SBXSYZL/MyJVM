package com.myJvm.jvm.interpreter.instructions.impl.constantInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.log.MyLog;

@MyInstruction
public class DConst1 extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("dconst_1");
        frame.getOperandStack().pushDouble(1.0d);
    }
}
