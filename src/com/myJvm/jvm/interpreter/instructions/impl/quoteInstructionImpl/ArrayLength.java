package com.myJvm.jvm.interpreter.instructions.impl.quoteInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.shared.heap.info.MyObject;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class ArrayLength extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("arraylength");
        OperandStack operandStack = frame.getOperandStack();
        MyObject arrayRef = (MyObject) operandStack.popRef();
        if (null == arrayRef) {
            throw new NullPointerException();
        }
        int arrayLength = arrayRef.getArrayLength();
        operandStack.pushInteger(arrayLength);
    }
}
