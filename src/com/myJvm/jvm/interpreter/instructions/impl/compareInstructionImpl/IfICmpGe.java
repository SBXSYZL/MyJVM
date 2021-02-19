package com.myJvm.jvm.interpreter.instructions.impl.compareInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionShortOperandReader;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class IfICmpGe extends InstructionShortOperandReader {
    @Override
    public void exec() {

        OperandStack operandStack = frame.getOperandStack();
        int offset = this.value;
        int value1 = operandStack.popInteger();
        int value2 = operandStack.popInteger();

        MyLog.command("if_icmpge  " + value1 + " : " + value2);
        if (value2 >= value1) {
            int pc = frame.getThread().getPc();
            int nextPc = pc + offset;
            frame.setNextPc(nextPc);
        }
    }
}
