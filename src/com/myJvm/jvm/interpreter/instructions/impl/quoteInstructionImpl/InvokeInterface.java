package com.myJvm.jvm.interpreter.instructions.impl.quoteInstructionImpl;

import com.myJvm.jvm.interpreter.CodeReader;
import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionShortOperandReader;
import com.myJvm.jvm.interpreter.instructions.logic.MethodInvokeLogic;
import com.myJvm.jvm.runtime.shared.heap.RuntimeConstantPool;
import com.myJvm.jvm.runtime.shared.heap.info.MyMethod;
import com.myJvm.jvm.runtime.shared.heap.info.MyObject;
import com.myJvm.jvm.runtime.shared.heap.info.ref.MyInterfaceMethodRef;
import com.myJvm.jvm.runtime.shared.heap.info.ref.MyMethodRef;
import com.myJvm.log.MyLog;

@MyInstruction
public class InvokeInterface extends InstructionShortOperandReader {
    @Override
    public void readValue(CodeReader reader) {
        super.readValue(reader);
        reader.readByte();
        reader.readByte();
    }

    @Override
    public void exec() {
        MyLog.command("invokeinterface");
        int index = this.value;
        RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getRuntimeConstantPool();
        MyInterfaceMethodRef methodRef = (MyInterfaceMethodRef) runtimeConstantPool.getConstants(index);
        MyMethod method = methodRef.resolveInterfaceMethod();
        if (method.isStatic() || method.isPrivate()) {
            throw new IncompatibleClassChangeError();
        }
        MyObject refFromTop = (MyObject) frame.getOperandStack().getRefFromTop(method.getArgsCount() - 1);
        if (refFromTop == null) {
            throw new NullPointerException();
        }
        if (!refFromTop.getClazz().isImplements(methodRef.getClazz())) {
            throw new IncompatibleClassChangeError();
        }
        MyMethod invokedMethod = MyMethodRef.lookupMethod(refFromTop.getClazz(), methodRef.getMethodName(), methodRef.getDescription());
        if (null == invokedMethod || invokedMethod.isAbstract()) {
            throw new AbstractMethodError();
        }
        if (!invokedMethod.isPublic()) {
            throw new IllegalAccessError();
        }
        MethodInvokeLogic.invokeMethod(frame, invokedMethod);

    }
}
