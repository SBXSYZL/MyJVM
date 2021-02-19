package com.myJvm.jvm.interpreter.instructions.impl.loadInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.log.MyLog;

@MyInstruction
public class ILoad3 extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("iload_3");
        Integer integer = frame.getLocalVariableTable().getInteger(3);
        frame.getOperandStack().pushInteger(integer);
    }
}
