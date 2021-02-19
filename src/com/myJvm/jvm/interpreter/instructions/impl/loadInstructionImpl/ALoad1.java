package com.myJvm.jvm.interpreter.instructions.impl.loadInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.shared.heap.info.MyObject;
import com.myJvm.log.MyLog;

@MyInstruction
public class ALoad1 extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("aload_1");
        MyObject ref = (MyObject) frame.getLocalVariableTable().getRef(1);
        frame.getOperandStack().pushRef(ref);
    }
}
