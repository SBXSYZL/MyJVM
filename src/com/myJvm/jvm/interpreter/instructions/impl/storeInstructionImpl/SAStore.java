package com.myJvm.jvm.interpreter.instructions.impl.storeInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.shared.heap.info.MyObject;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;
import com.myJvm.utils.CheckUtil;

@MyInstruction
public class SAStore extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("sastore");
        OperandStack operandStack = frame.getOperandStack();
        int value = operandStack.popInteger();
        Integer index = operandStack.popInteger();
        MyObject ref = (MyObject) operandStack.popRef();
        if (ref == null) {
            throw new RuntimeException("Failed To Exec Instruction: saStore");
        }
        short[] shortArray = ref.getShortArray();
        CheckUtil.checkIndex(shortArray.length, index);
        shortArray[index] = (short) value;
    }
}
