package com.myJvm.jvm.interpreter.instructions.impl.quoteInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionShortOperandReader;
import com.myJvm.jvm.runtime.shared.heap.RuntimeConstantPool;
import com.myJvm.jvm.runtime.shared.heap.info.*;
import com.myJvm.jvm.runtime.shared.heap.info.ref.MyFieldRef;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class PutField extends InstructionShortOperandReader {
    @Override
    public void exec() {

        int index = this.value;
        MyMethod currentMethod = frame.getMethod();
        MyClass currentMethodClazz = currentMethod.getClazz();
        RuntimeConstantPool runtimeConstantPool = currentMethodClazz.getRuntimeConstantPool();
        MyFieldRef fieldRef = (MyFieldRef) runtimeConstantPool.getConstants(index);
        MyField field = fieldRef.resolveField();
        MyLog.command("putfield: [ " + field.getMyClass().getSimpleClassName() + "." + field.getName() + " ]");
        if (field.isStatic()) {
            throw new IncompatibleClassChangeError();
        }
        if (field.isFinal()) {
            if (currentMethodClazz != field.getMyClass() || "<init>".equals(currentMethod.getName())) {
                throw new IllegalAccessError();
            }
        }
        String descriptor = field.getDescriptor();
        int slotIndex = field.getSlotIndex();
        OperandStack operandStack = frame.getOperandStack();
        switch (descriptor.charAt(0)) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
                int integerValue = operandStack.popInteger();
                MyObject refInt = (MyObject) operandStack.popRef();
                if (null == refInt) {
                    throw new NullPointerException();
                }
                refInt.getFields().setInteger(slotIndex, integerValue);
                break;
            case 'F':
                float floatValue = operandStack.popFloat();
                MyObject refFloat = (MyObject) operandStack.popRef();
                if (null == refFloat) {
                    throw new NullPointerException();
                }
                refFloat.getFields().setFloat(slotIndex, floatValue);
                break;
            case 'J':
                long longValue = operandStack.popLong();
                MyObject refLong = (MyObject) operandStack.popRef();
                if (null == refLong) {
                    throw new NullPointerException();
                }
                refLong.getFields().setLong(slotIndex, longValue);
                break;
            case 'D':
                double doubleValue = operandStack.popDouble();
                MyObject refDouble = (MyObject) operandStack.popRef();
                if (refDouble == null) {
                    throw new NullPointerException();
                }
                refDouble.getFields().setDouble(slotIndex, doubleValue);
                break;
            case 'L':
            case '[':
                MyObject value = (MyObject) operandStack.popRef();
                MyObject ref = (MyObject) operandStack.popRef();
                if (ref == null) {
                    throw new NullPointerException();
                }
                ref.getFields().setObjectRef(slotIndex, value);
                break;
            default:
                break;
        }

    }
}
