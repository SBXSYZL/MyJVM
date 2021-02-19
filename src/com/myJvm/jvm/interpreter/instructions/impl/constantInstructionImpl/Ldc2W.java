package com.myJvm.jvm.interpreter.instructions.impl.constantInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionShortOperandReader;
import com.myJvm.jvm.runtime.shared.heap.RuntimeConstantPool;
import com.myJvm.jvm.runtime.shared.heap.info.MyClass;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class Ldc2W extends InstructionShortOperandReader {


    @Override
    public void exec() {
        MyLog.command("ldc2w");
        int runtimeConstantIndex = this.value;
        OperandStack operandStack = frame.getOperandStack();
        MyClass clazz = frame.getMethod().getClazz();
        RuntimeConstantPool runtimeConstantPool = clazz.getRuntimeConstantPool();
        Object constants = runtimeConstantPool.getConstants(runtimeConstantIndex);
        if (constants instanceof Long) {
            operandStack.pushLong((Long) constants);
            return;
        } else if (constants instanceof Double) {
            operandStack.pushDouble((Double) constants);
            return;
        }
        throw new RuntimeException("Failed To Do Ldc2_W Instruction " + constants.getClass().getName());
    }
}
