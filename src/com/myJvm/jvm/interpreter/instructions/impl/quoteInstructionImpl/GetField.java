package com.myJvm.jvm.interpreter.instructions.impl.quoteInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionShortOperandReader;
import com.myJvm.jvm.runtime.shared.heap.RuntimeConstantPool;
import com.myJvm.jvm.runtime.shared.heap.info.*;
import com.myJvm.jvm.runtime.shared.heap.info.ref.MyFieldRef;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class GetField extends InstructionShortOperandReader {
    @Override
    public void exec() {
        MyLog.command("getfield");
        int index = this.value;
        RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getRuntimeConstantPool();
        MyFieldRef fieldRef = (MyFieldRef) runtimeConstantPool.getConstants(index);
        MyField field = fieldRef.resolveField();
        if (field.isStatic()) {
            throw new IncompatibleClassChangeError();
        }
        OperandStack operandStack = frame.getOperandStack();
        MyObject ref = (MyObject) operandStack.popRef();
        if (null == ref) {
            throw new NullPointerException();
        }
        String descriptor = field.getDescriptor();
        int slotIndex = field.getSlotIndex();

        MyArray fields = ref.getFields();
        switch (descriptor.charAt(0)) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
                operandStack.pushInteger(fields.getInteger(slotIndex));
                break;
            case 'F':
                operandStack.pushFloat(fields.getFloat(slotIndex));
                break;
            case 'J':
                operandStack.pushLong(fields.getLong(slotIndex));
                break;
            case 'D':
                operandStack.pushDouble(fields.getDouble(slotIndex));
                break;
            case 'L':
            case '[':
                operandStack.pushRef(fields.getObjectRef(slotIndex));
                break;
            default:
                break;
        }


    }
}
