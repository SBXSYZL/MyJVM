package com.myJvm.jvm.interpreter.instructions.impl.mathInstructionImpl;


import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class DAdd extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("dadd");
        OperandStack operandStack = frame.getOperandStack();
        Double doubleVal1 = operandStack.popDouble();
        Double doubleVal2 = operandStack.popDouble();
        double addResult = doubleVal1 + doubleVal2;
        operandStack.pushDouble(addResult);
    }
}
