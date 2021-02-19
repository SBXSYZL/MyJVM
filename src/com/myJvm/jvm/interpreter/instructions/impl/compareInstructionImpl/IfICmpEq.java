package com.myJvm.jvm.interpreter.instructions.impl.compareInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionShortOperandReader;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class IfICmpEq extends InstructionShortOperandReader {
    @Override
    public void exec() {
        MyLog.command("if_icmpeq");
        OperandStack operandStack = frame.getOperandStack();
        int offset = this.value;
        int value1 = operandStack.popInteger();
        int value2 = operandStack.popInteger();
        if (value2 == value1) {
            int pc = frame.getThread().getPc();
            int nextPc = pc + offset;
            frame.setNextPc(nextPc);
        }
    }
}
