package com.myJvm.jvm.interpreter.instructions.impl.extensionInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.Instruction;
import com.myJvm.jvm.interpreter.instructions.InstructionEnum;
import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionByteOperandReader;

@MyInstruction
public class Wide extends InstructionByteOperandReader {
    @Override
    public void exec() {
        byte opCode = (byte) this.value;
        Instruction instruction = null;
        switch (opCode) {
            case InstructionEnum.I_LOAD:
//TODO
            default:
                break;
        }
        if (instruction != null) {
            instruction.exec();
        }
    }
}
