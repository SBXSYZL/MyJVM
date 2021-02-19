package com.myJvm.jvm.interpreter.instructions.impl.loadInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.log.MyLog;

@MyInstruction
public class LLoad1 extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("lload_1");
        Long longVal = frame.getLocalVariableTable().getLong(1);
        frame.getOperandStack().pushLong(longVal);
    }
}
