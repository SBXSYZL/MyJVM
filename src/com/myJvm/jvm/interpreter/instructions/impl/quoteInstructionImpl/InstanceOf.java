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
public class InstanceOf extends InstructionShortOperandReader {
    @Override
    public void exec() {
        MyLog.command("instanceof");
        int index = this.value;
        OperandStack operandStack = frame.getOperandStack();
        MyObject ref = (MyObject) operandStack.popRef();
        if (null == ref) {
            operandStack.pushInteger(0);
            return;
        }
        RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getRuntimeConstantPool();
        MyClassRef classRef = (MyClassRef) runtimeConstantPool.getConstants(index);
        try {
            MyClass myClass = classRef.resolvedClass();
            if (ref.isInstanceOf(myClass)) {
                operandStack.pushInteger(1);
            } else {
                operandStack.pushInteger(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
