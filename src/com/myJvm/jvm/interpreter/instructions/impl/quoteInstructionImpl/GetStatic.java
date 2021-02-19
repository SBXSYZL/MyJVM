package com.myJvm.jvm.interpreter.instructions.impl.quoteInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionShortOperandReader;
import com.myJvm.jvm.interpreter.instructions.logic.ClassInitLogic;
import com.myJvm.jvm.runtime.shared.heap.RuntimeConstantPool;
import com.myJvm.jvm.runtime.shared.heap.info.MyArray;
import com.myJvm.jvm.runtime.shared.heap.info.MyClass;
import com.myJvm.jvm.runtime.shared.heap.info.MyField;
import com.myJvm.jvm.runtime.shared.heap.info.ref.MyFieldRef;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

/**
 * @author 22454
 */
@MyInstruction
public class GetStatic extends InstructionShortOperandReader {
    @Override
    public void exec() {
        MyLog.command("getstatic");
        int index = this.value;
        RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getRuntimeConstantPool();
        MyFieldRef ref = (MyFieldRef) runtimeConstantPool.getConstants(index);
        MyField field = ref.resolveField();
        MyClass clazz = field.getMyClass();
        if (!clazz.isInitStarted()) {
            frame.revertNextPc();
            ClassInitLogic.initClass(frame.getThread(), clazz);
            return;
        }
        if (!field.isStatic()) {
            throw new IncompatibleClassChangeError();
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
                operandStack.pushInteger(staticVariables.getInteger(slotIndex));
                break;
            case 'F':
                operandStack.pushFloat(staticVariables.getFloat(slotIndex));
                break;
            case 'J':
                operandStack.pushLong(staticVariables.getLong(slotIndex));
                break;

            case 'D':
                operandStack.pushDouble(staticVariables.getDouble(slotIndex));
                break;
            case 'L':
            case '[':
                operandStack.pushRef(staticVariables.getObjectRef(slotIndex));
                break;
            default:
                break;
        }

    }
}
