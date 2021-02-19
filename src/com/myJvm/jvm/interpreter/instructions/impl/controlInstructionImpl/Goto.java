package com.myJvm.jvm.interpreter.instructions.impl.controlInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionShortOperandReader;
import com.myJvm.log.MyLog;

@MyInstruction
public class Goto extends InstructionShortOperandReader {
    @Override
    public void exec() {
        int offset = this.value;
        int pc = frame.getThread().getPc();
        int nextPc = pc + offset;
        MyLog.command("goto " + nextPc);
        frame.setNextPc(nextPc);
    }
}
