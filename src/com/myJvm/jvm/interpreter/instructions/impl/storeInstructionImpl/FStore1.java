package com.myJvm.jvm.interpreter.instructions.impl.storeInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.log.MyLog;

@MyInstruction
public class FStore1 extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("fstore_1");
        Float floatVal = frame.getOperandStack().popFloat();
        frame.getLocalVariableTable().putFloat(1, floatVal);
    }
}
