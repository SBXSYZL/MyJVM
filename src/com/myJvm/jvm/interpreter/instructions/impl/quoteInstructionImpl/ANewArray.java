package com.myJvm.jvm.interpreter.instructions.impl.quoteInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionShortOperandReader;
import com.myJvm.jvm.runtime.shared.heap.RuntimeConstantPool;
import com.myJvm.jvm.runtime.shared.heap.info.MyClass;
import com.myJvm.jvm.runtime.shared.heap.info.MyObject;
import com.myJvm.jvm.runtime.shared.heap.info.ref.MyClassRef;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class ANewArray extends InstructionShortOperandReader {
    @Override
    public void exec() {
        MyLog.command("anewarray");
        try {
            int index = this.value;
            RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getRuntimeConstantPool();
            MyClassRef classRef = (MyClassRef) runtimeConstantPool.getConstants(index);
            MyClass componentClass = classRef.resolvedClass();
            OperandStack operandStack = frame.getOperandStack();
            int count = operandStack.popInteger();
            if (count < 0) {
                throw new NegativeArraySizeException();
            }

            MyClass arrayClass = componentClass.getArrayClass();
            MyObject array = arrayClass.createArray(count);
            operandStack.pushRef(array);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
