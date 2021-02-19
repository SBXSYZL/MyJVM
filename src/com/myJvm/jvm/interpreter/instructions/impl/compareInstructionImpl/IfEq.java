package com.myJvm.jvm.interpreter.instructions.impl.compareInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionShortOperandReader;
import com.myJvm.log.MyLog;

@MyInstruction
public class IfEq extends InstructionShortOperandReader {
    @Override
    public void exec() {
        MyLog.command("ifeq");
        Integer value = frame.getOperandStack().popInteger();
        int offset = this.value;
        if (value == 0) {
            int pc = frame.getThread().getPc();
            int nextPc = pc + offset;
            frame.setNextPc(nextPc);
        }
    }
}
