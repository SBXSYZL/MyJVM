package com.myJvm.jvm.interpreter.instructions.impl.loadInstructionImpl;


import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.log.MyLog;

@MyInstruction
public class LLoad2 extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("lload_2");
        Long longVal = frame.getLocalVariableTable().getLong(2);
        frame.getOperandStack().pushLong(longVal);
    }
}
