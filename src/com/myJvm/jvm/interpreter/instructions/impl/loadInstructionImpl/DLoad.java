package com.myJvm.jvm.interpreter.instructions.impl.loadInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionByteOperandReader;
import com.myJvm.log.MyLog;

@MyInstruction
public class DLoad extends InstructionByteOperandReader {
    @Override
    public void exec() {
        MyLog.command("dload");
        int localVariableIndex = this.value;
        Double val = frame.getLocalVariableTable().getDouble(localVariableIndex);
        frame.getOperandStack().pushDouble(val);
    }
}
