package com.myJvm.jvm.interpreter.instructions.impl.quoteInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionShortOperandReader;
import com.myJvm.jvm.interpreter.instructions.logic.ClassInitLogic;
import com.myJvm.jvm.runtime.shared.heap.RuntimeConstantPool;
import com.myJvm.jvm.runtime.shared.heap.info.*;
import com.myJvm.jvm.runtime.shared.heap.info.ref.MyFieldRef;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class PutStatic extends InstructionShortOperandReader {
    @Override
    public void exec() {

        int index = this.value;
        MyMethod currentMethod = frame.getMethod();
        MyClass currentMethodClazz = currentMethod.getClazz();
        RuntimeConstantPool runtimeConstantPool = currentMethodClazz.getRuntimeConstantPool();
        MyFieldRef fieldRef = (MyFieldRef) runtimeConstantPool.getConstants(index);
        MyField field = fieldRef.resolveField();
        MyLog.command("putstatic: [ " + field.getMyClass().getSimpleClassName() + "." + field.getName() + " ]");
        MyClass clazz = field.getMyClass();
        if (!clazz.isInitStarted()) {
            frame.revertNextPc();
            ClassInitLogic.initClass(frame.getThread(), clazz);
            return;
        }
        if (!field.isStatic()) {
            throw new IncompatibleClassChangeError();
        }
        if (field.isFinal()) {
            if (currentMethodClazz != clazz || !"<clinit>".equals(currentMethod.getName())) {
                throw new IllegalAccessError();
            }
        }
        String descriptor = field.getDescriptor();
        int slotIndex = field.getSlotIndex();
        MyArray staticVariables = clazz.getStaticVariables();
        OperandStack operandStack = frame.getOperandStack();
        switch (descriptor.charAt(0)) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
                staticVariables.setInteger(slotIndex, operandStack.popInteger());
                break;
            case 'F':
                staticVariables.setFloat(slotIndex, operandStack.popFloat());
                break;
            case 'J':
                staticVariables.setLong(slotIndex, operandStack.popLong());
                break;
            case 'D':
                staticVariables.setDouble(slotIndex,operandStack.popDouble());
                break;
            case 'L':
            case '[':
                staticVariables.setObjectRef(slotIndex, (MyObject) operandStack.popRef());
                break;
            default:
                break;
        }

    }
}
