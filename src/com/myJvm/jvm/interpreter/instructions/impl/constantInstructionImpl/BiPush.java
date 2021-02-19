package com.myJvm.jvm.interpreter.instructions.impl.constantInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionByteOperandReader;
import com.myJvm.log.MyLog;

@MyInstruction
public class BiPush extends InstructionByteOperandReader {

    @Override
    public void exec() {
        MyLog.command("bipush");
        frame.getOperandStack().pushInteger(value);
    }

}
