package com.myJvm.jvm.interpreter.instructions.impl.constantInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.log.MyLog;

/**
 * @author 22454
 */
@MyInstruction
public class Nop extends InstructionWithoutOperands {

    @Override
    public void exec() {
        MyLog.command("nop");
    }
}
