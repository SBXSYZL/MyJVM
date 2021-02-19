package com.myJvm.jvm.interpreter.instructions.impl.loadInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.log.MyLog;

@MyInstruction
public class LLoad0 extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("lload_0");
        Long longVal = frame.getLocalVariableTable().getLong(0);
        frame.getOperandStack().pushLong(longVal);
    }
}
