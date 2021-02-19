package com.myJvm.jvm.interpreter.instructions.impl.mathInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class FSub extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("fsub");
        OperandStack operandStack = frame.getOperandStack();
        float floatVal1 = operandStack.popFloat();
        float floatVal2 = operandStack.popFloat();
        float subResult = floatVal1 - floatVal2;
        operandStack.pushFloat(subResult);
    }
}
