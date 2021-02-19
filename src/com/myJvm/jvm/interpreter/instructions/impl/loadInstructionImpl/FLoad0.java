package com.myJvm.jvm.interpreter.instructions.impl.loadInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.log.MyLog;

@MyInstruction
public class FLoad0 extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("fload_0");
        Float floatVal = frame.getLocalVariableTable().getFloat(0);
        frame.getOperandStack().pushFloat(floatVal);
    }
}
