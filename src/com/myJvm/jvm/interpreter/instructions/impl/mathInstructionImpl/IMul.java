package com.myJvm.jvm.interpreter.instructions.impl.mathInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class IMul extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("imul");
        OperandStack operandStack = frame.getOperandStack();
        Integer integerVal1 = operandStack.popInteger();
        Integer integerVal2 = operandStack.popInteger();
        int mulResult = integerVal1 * integerVal2;
        operandStack.pushInteger(mulResult);
    }
}
