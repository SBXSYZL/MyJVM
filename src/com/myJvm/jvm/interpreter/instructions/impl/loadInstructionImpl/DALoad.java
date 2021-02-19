package com.myJvm.jvm.interpreter.instructions.impl.loadInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.shared.heap.info.MyObject;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;
import com.myJvm.utils.CheckUtil;

@MyInstruction
public class DALoad extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("daload");
        OperandStack operandStack = frame.getOperandStack();
        Integer index = operandStack.popInteger();
        MyObject ref = (MyObject) operandStack.popRef();
        if (ref == null) {
            throw new RuntimeException("Failed To Exec Instruction: iaLoad");
        }
        double[] doubleArray = ref.getDoubleArray();
        CheckUtil.checkIndex(doubleArray.length, index);
        operandStack.pushDouble(doubleArray[index]);
    }
}
