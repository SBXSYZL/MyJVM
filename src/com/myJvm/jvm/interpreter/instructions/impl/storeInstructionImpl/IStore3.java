package com.myJvm.jvm.interpreter.instructions.impl.storeInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.log.MyLog;

@MyInstruction
public class IStore3 extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("istore_3");
        Integer integer = frame.getOperandStack().popInteger();
        frame.getLocalVariableTable().putInteger(3, integer);
    }
}
