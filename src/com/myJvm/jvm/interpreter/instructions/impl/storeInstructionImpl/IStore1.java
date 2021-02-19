package com.myJvm.jvm.interpreter.instructions.impl.storeInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.log.MyLog;

@MyInstruction
public class IStore1 extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("istore_1");
        Integer integer = frame.getOperandStack().popInteger();
        frame.getLocalVariableTable().putInteger(1, integer);
    }
}
