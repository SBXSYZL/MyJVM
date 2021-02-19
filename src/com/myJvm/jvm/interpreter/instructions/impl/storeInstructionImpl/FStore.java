package com.myJvm.jvm.interpreter.instructions.impl.storeInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionByteOperandReader;
import com.myJvm.log.MyLog;

@MyInstruction
public class FStore extends InstructionByteOperandReader {
    @Override
    public void exec() {
        MyLog.command("fstore");
        int index = this.value;
        Float floatVal = frame.getOperandStack().popFloat();
        frame.getLocalVariableTable().putFloat(index, floatVal);
    }
}
