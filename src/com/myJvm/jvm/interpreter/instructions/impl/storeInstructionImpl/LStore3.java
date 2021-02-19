package com.myJvm.jvm.interpreter.instructions.impl.storeInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.log.MyLog;

@MyInstruction
public class LStore3 extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("lstore_3");
        Long longVal = frame.getOperandStack().popLong();
        frame.getLocalVariableTable().putLong(3, longVal);
    }
}
