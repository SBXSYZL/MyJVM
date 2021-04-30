package com.myJvm.jvm.interpreter.instructions.impl.reservedInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionWithoutOperands;
import com.myJvm.jvm.runtime.shared.heap.info.MyMethod;
import com.myJvm.jvm.runtime.shared.mynative.MyNativeMethod;
import com.myJvm.jvm.runtime.shared.mynative.NativeMethodTable;
import com.myJvm.log.MyLog;

@MyInstruction
public class InvokeNative extends InstructionWithoutOperands {
    @Override
    public void exec() {
        MyMethod method = frame.getMethod();
        String className = method.getClazz().getClassName();
        String methodName = method.getName();
        String methodDescriptor = method.getDescriptor();
        MyLog.command("invokenative: [ "+method.getClazz().getSimpleClassName()+"."+methodName+" ]");
        MyNativeMethod nativeMethod = NativeMethodTable.findNativeMethod(className, methodName, methodDescriptor);
        if (null == nativeMethod) {
            String methodInfo = className + "." + methodName + methodDescriptor;
            throw new UnsatisfiedLinkError(methodInfo);
        }
        nativeMethod.invoke(frame);
    }
}
