package com.myJvm.jvm.interpreter.instructions.impl.storeInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.shared.heap.info.MyObject;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;
import com.myJvm.utils.CheckUtil;

@MyInstruction
public class AAStore extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("aastore");
        OperandStack operandStack = frame.getOperandStack();
        MyObject value = (MyObject) operandStack.popRef();
        Integer index = operandStack.popInteger();
        MyObject ref = (MyObject) operandStack.popRef();
        if (ref == null) {
            throw new RuntimeException("Failed To Exec Instruction: aaStore");
        }
        MyObject[] myObjectArray = ref.getMyObjectArray();
        CheckUtil.checkIndex(myObjectArray.length, index);
        myObjectArray[index] = value;
    }
}
