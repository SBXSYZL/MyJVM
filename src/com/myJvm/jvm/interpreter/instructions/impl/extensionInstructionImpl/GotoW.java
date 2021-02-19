package com.myJvm.jvm.interpreter.instructions.impl.extensionInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionIntegerOperandReader;
import com.myJvm.log.MyLog;

@MyInstruction
public class GotoW extends InstructionIntegerOperandReader {
    @Override
    public void exec() {
        MyLog.command("goto_w");
        int offset = this.value;
        int pc = frame.getThread().getPc();
        int nextPc = pc + offset;
        frame.setNextPc(nextPc);
    }
}
