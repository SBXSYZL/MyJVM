package com.myJvm.jvm.interpreter.instructions.impl.loadInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.log.MyLog;

@MyInstruction
public class ILoad2 extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("iload_2");
        Integer integer = frame.getLocalVariableTable().getInteger(2);
        frame.getOperandStack().pushInteger(integer);
    }
}
