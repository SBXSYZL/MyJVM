package com.myJvm.jvm.interpreter.instructions.impl.loadInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionByteOperandReader;
import com.myJvm.jvm.runtime.shared.heap.info.MyObject;
import com.myJvm.log.MyLog;

@MyInstruction
public class ALoad extends InstructionByteOperandReader {
    @Override
    public void exec() {
        MyLog.command("aload");
        int localVariableIndex = this.value;
        MyObject ref = (MyObject) frame.getLocalVariableTable().getRef(localVariableIndex);
        frame.getOperandStack().pushRef(ref);
    }
}
