package com.myJvm.jvm.interpreter.instructions.impl.quoteInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionShortOperandReader;
import com.myJvm.jvm.interpreter.instructions.logic.MethodInvokeLogic;
import com.myJvm.jvm.runtime.shared.heap.RuntimeConstantPool;
import com.myJvm.jvm.runtime.shared.heap.info.*;
import com.myJvm.jvm.runtime.shared.heap.info.dependence.StringCache;
import com.myJvm.jvm.runtime.shared.heap.info.ref.MyMethodRef;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

/**
 * @author 22454
 */
@MyInstruction
public class InvokeVirtual extends InstructionShortOperandReader {
    @Override
    public void exec() {

        int index = this.value;
        MyClass currentClass = frame.getMethod().getClazz();
        RuntimeConstantPool runtimeConstantPool = currentClass.getRuntimeConstantPool();
        MyMethodRef methodRef = (MyMethodRef) runtimeConstantPool.getConstants(index);
        MyMethod method = methodRef.resolveMethod();
        MyLog.command("invokevirtual: [ " + method.getClazz().getSimpleClassName() + "." + method.getName() + " ]");
        if (method.isStatic()) {
            throw new IncompatibleClassChangeError();
        }
        MyObject refFromTop = (MyObject) frame.getOperandStack().getRefFromTop(method.getArgsCount() - 1);
        if (refFromTop == null) {
            if ("println".equals(methodRef.getMethodName())) {
                printLn(frame.getOperandStack(), methodRef.getDescriptor());
                return;
            }
            throw new NullPointerException("Unsupported Virtual [ " + methodRef.getMethodName() + " ]");
        }

        if (method.isProtected() &&
                method.getClazz().isSubClassOf(currentClass) &&
                !method.getClazz().getPackageName().equals(currentClass.getPackageName()) &&
                refFromTop.getClazz() != currentClass &&
                !refFromTop.getClazz().isSubClassOf(currentClass)
        ) {
            throw new IllegalAccessError();
        }

        MyMethod methodBeInvoke = MyMethodRef.lookupMethod(refFromTop.getClazz(), methodRef.getMethodName(), methodRef.getDescriptor());
        if (methodBeInvoke == null || methodBeInvoke.isAbstract()) {
            throw new AbstractMethodError();
        }
        MethodInvokeLogic.invokeMethod(frame, methodBeInvoke);
    }

    private void printLn(OperandStack stack, String descriptor) {
        String myVMStart = "terminal: ";
        switch (descriptor) {
            case "(Z)V":
                System.out.println(myVMStart + (stack.popInteger() != 0));
                break;
            case "(C)V":
            case "(I)V":
            case "(B)V":
            case "(S)V":
                System.out.println(myVMStart + stack.popInteger());
                break;
            case "(F)V":
                System.out.println(myVMStart + stack.popFloat());
                break;
            case "(J)V":
                System.out.println(myVMStart + stack.popLong());
                break;
            case "(D)V":
                System.out.println(myVMStart + stack.popDouble());
                break;
            case "(Ljava/lang/String;)V":
                MyObject str = (MyObject) stack.popRef();
                String string = StringCache.getString(str);
                System.out.println(myVMStart + string);
                break;
            default:
                System.out.println(myVMStart + descriptor);
                break;
        }
        stack.popRef();
    }
}
