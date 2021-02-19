package com.myJvm.jvm.interpreter.instructions.impl.loadInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionByteOperandReader;
import com.myJvm.log.MyLog;

@MyInstruction
public class LLoad extends InstructionByteOperandReader {

    @Override
    public void exec() {
        MyLog.command("lload");
        int localVariableIndex = this.value;
        Long longValue = frame.getLocalVariableTable().getLong(localVariableIndex);
        frame.getOperandStack().pushLong(longValue);
    }
}
