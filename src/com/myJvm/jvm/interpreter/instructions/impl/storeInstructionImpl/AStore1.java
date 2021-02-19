package com.myJvm.jvm.interpreter.instructions.impl.storeInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.log.MyLog;

@MyInstruction
public class AStore1 extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("astore_1");
        Object myObject = frame.getOperandStack().popRef();
        frame.getLocalVariableTable().putRef(1, myObject);
    }
}
