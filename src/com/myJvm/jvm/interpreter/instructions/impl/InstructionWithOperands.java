package com.myJvm.jvm.interpreter.instructions.impl;

import com.myJvm.jvm.interpreter.CodeReader;
import com.myJvm.jvm.interpreter.instructions.Instruction;
import com.myJvm.jvm.runtime.threaddependent.StackFrame;

/**
 * @author 22454
 */
public abstract class InstructionWithOperands implements Instruction {
    protected int value;
    protected StackFrame frame;
    protected CodeReader reader;

    public abstract void readValue(CodeReader reader);

    @Override
    public void updateStackFrame(StackFrame frame, CodeReader reader) {
        this.frame = frame;
        this.reader = reader;
    }
}
