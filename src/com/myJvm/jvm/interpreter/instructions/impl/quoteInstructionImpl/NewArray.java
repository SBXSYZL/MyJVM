package com.myJvm.jvm.interpreter.instructions.impl.quoteInstructionImpl;

import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionByteOperandReader;
import com.myJvm.jvm.loadcore.loader.MyClassLoader;
import com.myJvm.jvm.runtime.shared.heap.info.MyClass;
import com.myJvm.jvm.runtime.shared.heap.info.MyObject;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.log.MyLog;

@MyInstruction
public class NewArray extends InstructionByteOperandReader {
    @Override
    public void exec() {
        MyLog.command("newarray");
        int type = this.value;
        OperandStack operandStack = frame.getOperandStack();
        Integer count = operandStack.popInteger();
        if (count < 0) {
            throw new NegativeArraySizeException();
        }
        MyClassLoader classLoader = frame.getMethod().getClazz().getClassLoader();
        //TODO 希望这里的强转不要出错,照道理不会
        MyClass arrayClass = getPrimitiveArrayClass(classLoader, (byte) type);
        MyObject arrayObject = arrayClass.createArray(count);
        operandStack.pushRef(arrayObject);

    }

    private MyClass getPrimitiveArrayClass(MyClassLoader classLoader, byte type) {
        try {
            switch (type) {
                case ArrayType.T_BOOLEAN:
                    return classLoader.loadClass("[Z");
                case ArrayType.T_CHAR:
                    return classLoader.loadClass("[C");
                case ArrayType.T_FLOAT:
                    return classLoader.loadClass("[F");
                case ArrayType.T_DOUBLE:
                    return classLoader.loadClass("[D");
                case ArrayType.T_BYTE:
                    return classLoader.loadClass("[B");
                case ArrayType.T_SHORT:
                    return classLoader.loadClass("[S");
                case ArrayType.T_INT:
                    return classLoader.loadClass("[I");
                case ArrayType.T_LONG:
                    return classLoader.loadClass("[J");
                default:
                    throw new RuntimeException("Invalid aType");
            }
        } catch (Exception e) {
            e.printStackTrace();
            MyLog.error("Failed To New Array");
            throw new RuntimeException(e);
        }

    }

    static class ArrayType {
        static final byte T_BOOLEAN = 4;
        static final byte T_CHAR = 5;
        static final byte T_FLOAT = 6;
        static final byte T_DOUBLE = 7;
        static final byte T_BYTE = 8;
        static final byte T_SHORT = 9;
        static final byte T_INT = 10;
        static final byte T_LONG = 11;
    }
}
