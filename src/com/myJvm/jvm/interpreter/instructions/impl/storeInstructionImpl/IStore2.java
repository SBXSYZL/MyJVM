package com.myJvm.jvm.interpreter.instructions.impl.storeInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.log.MyLog;

@MyInstruction
public class IStore2 extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("istore_2");
        Integer integer = frame.getOperandStack().popInteger();
        frame.getLocalVariableTable().putInteger(2, integer);
    }
}
