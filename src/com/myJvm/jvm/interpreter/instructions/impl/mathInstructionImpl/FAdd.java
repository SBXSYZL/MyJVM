package com.myJvm.jvm.interpreter.instructions.impl.mathInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class FAdd extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("fadd");
        OperandStack operandStack = frame.getOperandStack();
        Float floatVal1 = operandStack.popFloat();
        Float floatVal2 = operandStack.popFloat();
        float addResult = floatVal1 + floatVal2;
        operandStack.pushFloat(addResult);
    }
}
