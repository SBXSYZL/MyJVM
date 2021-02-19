package com.myJvm.jvm.interpreter.instructions.impl.storeInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.log.MyLog;

@MyInstruction
public class LStore2 extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("lstore_2");
        Long longVal = frame.getOperandStack().popLong();
        frame.getLocalVariableTable().putLong(2, longVal);
    }
}
