package com.myJvm.jvm.interpreter.instructions;

import com.myJvm.jvm.interpreter.CodeReader;
import com.myJvm.jvm.runtime.threaddependent.StackFrame;

/**
 * @author 22454
 */
public interface Instruction {
    void exec();

    void updateStackFrame(StackFrame frame, CodeReader reader);
}
