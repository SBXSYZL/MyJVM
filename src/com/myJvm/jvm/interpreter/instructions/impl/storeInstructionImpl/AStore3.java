package com.myJvm.jvm.interpreter.instructions.impl.storeInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.shared.heap.info.MyObject;
import com.myJvm.log.MyLog;

@MyInstruction
public class AStore3 extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("astore_3");
        MyObject myObject = (MyObject) frame.getOperandStack().popRef();
        frame.getLocalVariableTable().putRef(3, myObject);
    }
}
