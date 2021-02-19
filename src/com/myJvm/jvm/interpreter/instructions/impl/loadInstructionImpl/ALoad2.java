package com.myJvm.jvm.interpreter.instructions.impl.loadInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.shared.heap.info.MyObject;
import com.myJvm.log.MyLog;

@MyInstruction
public class ALoad2 extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("aload_2");
        MyObject ref = (MyObject) frame.getLocalVariableTable().getRef(2);
        frame.getOperandStack().pushRef(ref);
    }
}
