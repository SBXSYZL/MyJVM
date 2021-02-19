package com.myJvm.jvm.interpreter.instructions.impl.quoteInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionShortOperandReader;
import com.myJvm.jvm.interpreter.instructions.logic.MethodInvokeLogic;
import com.myJvm.jvm.runtime.shared.heap.RuntimeConstantPool;
import com.myJvm.jvm.runtime.shared.heap.info.MyClass;
import com.myJvm.jvm.runtime.shared.heap.info.MyMethod;
import com.myJvm.jvm.runtime.shared.heap.info.MyObject;
import com.myJvm.jvm.runtime.shared.heap.info.ref.MyMethodRef;
import com.myJvm.log.MyLog;

/**
 * @author 22454
 */
@MyInstruction
public class InvokeSpecial extends InstructionShortOperandReader {
    @Override
    public void exec() {

        int index = this.value;
        MyClass currentClass = frame.getMethod().getClazz();
        RuntimeConstantPool runtimeConstantPool = currentClass.getRuntimeConstantPool();
        MyMethodRef methodRef = (MyMethodRef) runtimeConstantPool.getConstants(index);
        MyClass clazz = methodRef.resolvedClass();
        MyMethod method = methodRef.resolveMethod();
        MyLog.command("invokespecial: [" + method.getClazz().getSimpleClassName() + "." + method.getName()+" ]");
        if ("<init>".equals(method.getName()) && method.getClazz() != clazz && !clazz.isSubClassOf(method.getClazz())) {
            throw new NoSuchMethodError();
        }
        if (method.isStatic()) {
            throw new IncompatibleClassChangeError();
        }
        MyObject refFromTop = (MyObject) frame.getOperandStack().getRefFromTop(method.getArgsCount() - 1);
        if (refFromTop == null) {
            throw new NullPointerException();
        }
        if (method.isProtected() &&
                method.getClazz().isSubClassOf(currentClass) &&
                !method.getClazz().getPackageName().equals(currentClass.getPackageName()) &&
                refFromTop.getClazz() != currentClass &&
                !refFromTop.getClazz().isSubClassOf(currentClass)
        ) {
            throw new IllegalAccessError();
        }

        if (currentClass.isSuper() &&
                clazz.isSubClassOf(currentClass) &&
                !"<init>".equals(method.getName())
        ) {
            method = MyMethodRef.lookupMethod(currentClass.getSuperClass(), methodRef.getMethodName(), methodRef.getDescriptor());
        }

        if (method.isAbstract()) {
            throw new AbstractMethodError();
        }
        MethodInvokeLogic.invokeMethod(frame, method);

    }
}
