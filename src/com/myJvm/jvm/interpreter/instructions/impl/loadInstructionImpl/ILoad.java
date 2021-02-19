package com.myJvm.jvm.interpreter.instructions.impl.loadInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionByteOperandReader;
import com.myJvm.log.MyLog;

@MyInstruction
public class ILoad extends InstructionByteOperandReader {


    @Override
    public void exec() {
        MyLog.command("iload");
        int localVariableIndex = this.value;
        Integer integer = frame.getLocalVariableTable().getInteger(localVariableIndex);
        frame.getOperandStack().pushInteger(integer);
    }
}
