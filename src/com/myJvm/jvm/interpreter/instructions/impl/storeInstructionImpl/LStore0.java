package com.myJvm.jvm.interpreter.instructions.impl.storeInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.log.MyLog;

@MyInstruction
public class LStore0 extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("lstore_0");
        Long longVal = frame.getOperandStack().popLong();
        frame.getLocalVariableTable().putLong(0, longVal);
    }
}
