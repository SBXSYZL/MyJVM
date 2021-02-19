package com.myJvm.jvm.interpreter.instructions.impl.storeInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.shared.heap.info.MyObject;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;
import com.myJvm.utils.CheckUtil;

@MyInstruction
public class FAStore extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("fastore");
        OperandStack operandStack = frame.getOperandStack();
        Float value = operandStack.popFloat();
        Integer index = operandStack.popInteger();
        MyObject ref = (MyObject) operandStack.popRef();
        if (ref == null) {
            throw new RuntimeException("Failed To Exec Instruction: faStore");
        }
        float[] floatArray = ref.getFloatArray();
        CheckUtil.checkIndex(floatArray.length, index);
        floatArray[index] = value;
    }
}
