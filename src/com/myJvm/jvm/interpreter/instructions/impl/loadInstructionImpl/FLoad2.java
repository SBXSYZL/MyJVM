package com.myJvm.jvm.interpreter.instructions.impl.loadInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.log.MyLog;

@MyInstruction
public class FLoad2 extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("fload_2");
        Float floatVal = frame.getLocalVariableTable().getFloat(2);
        frame.getOperandStack().pushFloat(floatVal);
    }
}
