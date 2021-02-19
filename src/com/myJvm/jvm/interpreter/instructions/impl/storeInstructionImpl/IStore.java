package com.myJvm.jvm.interpreter.instructions.impl.storeInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionByteOperandReader;
import com.myJvm.log.MyLog;

@MyInstruction
public class IStore extends InstructionByteOperandReader {
    @Override
    public void exec() {
        MyLog.command("istore");
        int index = this.value;
        Integer integer = frame.getOperandStack().popInteger();
        frame.getLocalVariableTable().putInteger(index, integer);
    }
}
