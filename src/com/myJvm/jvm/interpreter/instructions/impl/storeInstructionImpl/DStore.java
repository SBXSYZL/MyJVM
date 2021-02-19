package com.myJvm.jvm.interpreter.instructions.impl.storeInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionByteOperandReader;
import com.myJvm.log.MyLog;

@MyInstruction
public class DStore extends InstructionByteOperandReader {
    @Override
    public void exec() {
        MyLog.command("dstore");
        int index = this.value;
        Double doubleVal = frame.getOperandStack().popDouble();
        frame.getLocalVariableTable().putDouble(index, doubleVal);
    }
}
