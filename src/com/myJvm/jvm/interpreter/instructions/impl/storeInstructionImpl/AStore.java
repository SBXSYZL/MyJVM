package com.myJvm.jvm.interpreter.instructions.impl.storeInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionByteOperandReader;
import com.myJvm.jvm.runtime.shared.heap.info.MyObject;
import com.myJvm.log.MyLog;

@MyInstruction
public class AStore extends InstructionByteOperandReader {
    @Override
    public void exec() {
        MyLog.command("astore");
        int index = this.value;
        MyObject myObject = (MyObject) frame.getOperandStack().popRef();
        frame.getLocalVariableTable().putRef(index, myObject);
    }
}
