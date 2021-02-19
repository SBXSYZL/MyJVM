package com.myJvm.jvm.interpreter.instructions.impl.mathInstructionImpl;

import com.myJvm.jvm.interpreter.CodeReader;
import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionByteOperandReader;
import com.myJvm.jvm.runtime.threaddependent.LocalVariableTable;
import com.myJvm.log.MyLog;

@MyInstruction
public class IInc extends InstructionByteOperandReader {
    private int incValue;

    @Override
    public void readValue(CodeReader reader) {
        super.readValue(reader);
        incValue = readOneByte(reader);
    }

    @Override
    public void exec() {
        MyLog.command("iinc");
        int index = this.value;
        LocalVariableTable localVariableTable = frame.getLocalVariableTable();
        int value = localVariableTable.getInteger(index);
        value += incValue;
        localVariableTable.putInteger(index, value);
    }
}
