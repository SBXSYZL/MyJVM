package com.myJvm.jvm.interpreter.instructions.impl.loadInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.log.MyLog;

@MyInstruction
public class DLoad0 extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("dload_0");
        Double doubleVal = frame.getLocalVariableTable().getDouble(0);
        frame.getOperandStack().pushDouble(doubleVal);
    }
}
