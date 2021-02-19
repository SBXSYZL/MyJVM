package com.myJvm.jvm.interpreter.instructions.impl.compareInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionShortOperandReader;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class IfACmpNe extends InstructionShortOperandReader {
    @Override
    public void exec() {
        MyLog.command("if_acmpne");
        OperandStack operandStack = frame.getOperandStack();
        int offset = this.value;
        Object ref1 = operandStack.popRef();
        Object ref2 = operandStack.popRef();
        if (!ref1.equals(ref2)) {
            int pc = frame.getThread().getPc();
            int nextPc = pc + offset;
            frame.setNextPc(nextPc);
        }
    }
}
