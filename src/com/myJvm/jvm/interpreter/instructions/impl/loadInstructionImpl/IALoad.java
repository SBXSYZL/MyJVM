package com.myJvm.jvm.interpreter.instructions.impl.loadInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.shared.heap.info.MyObject;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;
import com.myJvm.utils.CheckUtil;

@MyInstruction
public class IALoad extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("iaload");
        OperandStack operandStack = frame.getOperandStack();
        Integer index = operandStack.popInteger();
        MyObject ref = (MyObject) operandStack.popRef();
        if (ref == null) {
            throw new RuntimeException("Failed To Exec Instruction: iaLoad");
        }
        int[] integerArray = ref.getIntegerArray();
        CheckUtil.checkIndex(integerArray.length, index);
        operandStack.pushInteger(integerArray[index]);
    }
}
