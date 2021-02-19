package com.myJvm.jvm.interpreter.instructions.impl.loadInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.shared.heap.info.MyObject;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class SALoad extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyLog.command("saload");
        OperandStack operandStack = frame.getOperandStack();
        Integer index = operandStack.popInteger();
        MyObject ref = (MyObject) operandStack.popRef();
        if (ref == null) {
            throw new RuntimeException("Failed To Exec Instruction: iaLoad");
        }
        short[] shortArray = ref.getShortArray();
        operandStack.pushInteger((int) shortArray[index]);
    }
}
