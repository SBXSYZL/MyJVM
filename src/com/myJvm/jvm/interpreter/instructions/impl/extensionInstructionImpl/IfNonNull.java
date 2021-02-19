package com.myJvm.jvm.interpreter.instructions.impl.extensionInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionShortOperandReader;
import com.myJvm.jvm.runtime.shared.heap.info.MyObject;
import com.myJvm.log.MyLog;

@MyInstruction
public class IfNonNull extends InstructionShortOperandReader {
    @Override
    public void exec() {
        MyLog.command("ifnonnull");
        int offset = this.value;
        MyObject ref = (MyObject) frame.getOperandStack().popRef();
        if (ref != null) {
            int pc = frame.getThread().getPc();
            int nextPc = pc + offset;
            frame.setNextPc(nextPc);
        }
    }
}
