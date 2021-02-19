package com.myJvm.jvm.interpreter.instructions.impl.constantInstructionImpl;

import com.myJvm.jvm.interpreter.CodeReader;
import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionByteOperandReader;
import com.myJvm.jvm.runtime.shared.heap.RuntimeConstantPool;
import com.myJvm.jvm.runtime.shared.heap.info.MyClass;
import com.myJvm.jvm.runtime.shared.heap.info.MyObject;
import com.myJvm.jvm.runtime.shared.heap.info.dependence.StringCache;
import com.myJvm.jvm.runtime.shared.heap.info.ref.MyClassRef;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class Ldc extends InstructionByteOperandReader {

    @Override
    public void readValue(CodeReader reader) {
        super.readValue(reader);
    }

    @Override
    public void exec() {
        MyLog.command("ldc");
        int runtimeConstantIndex = this.value;
        OperandStack operandStack = frame.getOperandStack();
        MyClass clazz = frame.getMethod().getClazz();
        RuntimeConstantPool runtimeConstantPool = clazz.getRuntimeConstantPool();
        Object constants = runtimeConstantPool.getConstants(runtimeConstantIndex);
        //如果是 Integer
        if (constants instanceof Integer) {
            operandStack.pushInteger((Integer) constants);
            return;
        }
        //如果是 Float
        else if (constants instanceof Float) {
            operandStack.pushFloat((Float) constants);
            return;
        }
        //如果是 String
        else if (constants instanceof String) {
            MyObject string = StringCache.putString(clazz.getClassLoader(), (String) constants);
            operandStack.pushRef(string);
            return;
        }
        //如果是 MyClassRef
        else if (constants instanceof MyClassRef) {
            MyClassRef classRef = (MyClassRef) constants;
            try {
                MyObject reflectClass = classRef.resolvedClass().getReflectClass();
                operandStack.pushRef(reflectClass);
            } catch (Exception e) {
                MyLog.error("Failed To Get Reflect Class");
                e.printStackTrace();
            }
            return;
        }
        throw new RuntimeException("Failed To Do Ldc Instruction " + constants.getClass().getName());
    }
}
