package com.myJvm.jvm.interpreter.instructions.impl.loadInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionByteOperandReader;
import com.myJvm.log.MyLog;

@MyInstruction
public class FLoad extends InstructionByteOperandReader {

    @Override
    public void exec() {
        MyLog.command("fload");
        int localVariableIndex = this.value;
        Float aFloat = frame.getLocalVariableTable().getFloat(localVariableIndex);
        frame.getOperandStack().pushFloat(aFloat);
    }
}
