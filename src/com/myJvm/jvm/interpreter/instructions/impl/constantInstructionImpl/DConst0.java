package com.myJvm.jvm.interpreter.instructions.impl.constantInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.log.MyLog;

@MyInstruction
public class DConst0 extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("dconst_0");
        frame.getOperandStack().pushDouble(0.0d);
    }
}
