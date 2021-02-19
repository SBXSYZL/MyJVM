package com.myJvm.jvm.interpreter.instructions.impl.quoteInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionShortOperandReader;
import com.myJvm.jvm.interpreter.instructions.logic.ClassInitLogic;
import com.myJvm.jvm.interpreter.instructions.logic.MethodInvokeLogic;
import com.myJvm.jvm.runtime.shared.heap.RuntimeConstantPool;
import com.myJvm.jvm.runtime.shared.heap.info.MyClass;
import com.myJvm.jvm.runtime.shared.heap.info.MyMethod;
import com.myJvm.jvm.runtime.shared.heap.info.ref.MyMethodRef;
import com.myJvm.log.MyLog;

@MyInstruction
public class InvokeStatic extends InstructionShortOperandReader {
    @Override
    public void exec() {

        int index = this.value;
        RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getRuntimeConstantPool();
        MyMethodRef methodRef = (MyMethodRef) runtimeConstantPool.getConstants(index);
        MyMethod method = methodRef.resolveMethod();
        MyLog.command("invokestatic: [ "+method.getClazz().getSimpleClassName()+"."+method.getName()+" ]");
        if (!method.isStatic()) {
            throw new IncompatibleClassChangeError();
        }

        MyClass clazz = method.getClazz();
        if (!clazz.isInitStarted()) {
            frame.revertNextPc();
            ClassInitLogic.initClass(frame.getThread(), clazz);
            return;
        }
        MethodInvokeLogic.invokeMethod(frame, method);

    }
}
