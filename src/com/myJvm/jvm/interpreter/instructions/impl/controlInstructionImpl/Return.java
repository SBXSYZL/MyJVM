package com.myJvm.jvm.interpreter.instructions.impl.controlInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.log.MyLog;

@MyInstruction
public class Return extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("return");
        frame.getThread().popStackFrame();
    }
}
