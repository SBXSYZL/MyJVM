package com.myJvm.jvm.interpreter.instructions.impl.storeInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionByteOperandReader;
import com.myJvm.log.MyLog;

@MyInstruction
public class LStore extends InstructionByteOperandReader {
    @Override
    public void exec() {
        MyLog.command("lstore");
        int index = this.value;
        Long longVal = frame.getOperandStack().popLong();
        frame.getLocalVariableTable().putLong(index, longVal);
    }
}
