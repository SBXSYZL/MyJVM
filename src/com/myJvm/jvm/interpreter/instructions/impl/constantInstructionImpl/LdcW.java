package com.myJvm.jvm.interpreter.instructions.impl.constantInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionShortOperandReader;
import com.myJvm.jvm.runtime.shared.heap.RuntimeConstantPool;
import com.myJvm.jvm.runtime.shared.heap.info.MyClass;
import com.myJvm.jvm.runtime.shared.heap.info.MyObject;
import com.myJvm.jvm.runtime.shared.heap.info.dependence.StringCache;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class LdcW extends InstructionShortOperandReader {


    @Override
    public void exec() {
        MyLog.command("ldcw");
        int runtimeConstantIndex = value;
        OperandStack operandStack = frame.getOperandStack();
        MyClass clazz = frame.getMethod().getClazz();
        RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getRuntimeConstantPool();
        Object constants = runtimeConstantPool.getConstants(runtimeConstantIndex);
        if (constants instanceof Integer) {
            operandStack.pushInteger((Integer) constants);
            return;
        } else if (constants instanceof Float) {
            operandStack.pushFloat((Float) constants);
            return;
        } else if (constants instanceof String) {
            MyObject string = StringCache.putString(clazz.getClassLoader(), (String) constants);
            operandStack.pushRef(string);
            return;
        }
        throw new RuntimeException("Failed To DO Ldc_W Instruction " + constants.getClass().getName());
    }
}
