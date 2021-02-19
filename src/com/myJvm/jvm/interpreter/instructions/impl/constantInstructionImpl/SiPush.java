package com.myJvm.jvm.interpreter.instructions.impl.constantInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionShortOperandReader;
import com.myJvm.log.MyLog;

@MyInstruction
public class SiPush extends InstructionShortOperandReader {

    @Override
    public void exec() {
        MyLog.command("sipush");
        frame.getOperandStack().pushInteger(value);
    }
}
