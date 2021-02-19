package com.myJvm.jvm.interpreter.instructions.impl.loadInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.log.MyLog;

@MyInstruction
public class LLoad3 extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("lload_3");
        Long longVal = frame.getLocalVariableTable().getLong(3);
        frame.getOperandStack().pushLong(longVal);
    }
}
