package jvm.interpreter.instructions.InstructionExecutorImpl;

import jvm.classLoadSystem.classLoaderImpl.MyClassLoader;
import jvm.interpreter.CodeReader;
import jvm.interpreter.instructions.logic.ClassInitLogic;
import jvm.interpreter.instructions.InstructionEnum;
import jvm.interpreter.instructions.InstructionExecutor;
import jvm.interpreter.instructions.logic.MethodInvokeLogic;
import jvm.runtimeDataArea.MyThread;
import jvm.runtimeDataArea.shared.heap.RuntimeConstantPool;
import jvm.runtimeDataArea.shared.heap.info.*;
import jvm.runtimeDataArea.shared.heap.info.dependence.StringCache;
import jvm.runtimeDataArea.shared.heap.info.ref.MyClassRef;
import jvm.runtimeDataArea.shared.heap.info.ref.MyFieldRef;
import jvm.runtimeDataArea.shared.heap.info.ref.MyInterfaceMethodRef;
import jvm.runtimeDataArea.shared.heap.info.ref.MyMethodRef;
import jvm.runtimeDataArea.shared.myNative.myNativeClass.ThrowableNative;
import jvm.runtimeDataArea.threadDependent.OperandStack;
import jvm.runtimeDataArea.threadDependent.StackFrame;
import log.MyLog;

import java.util.Stack;

/**
 * @author 22454
 */
public class QuoteInstructionExecutor implements InstructionExecutor {
    public boolean isQuoteInstruction(int operatorCode) {
        return operatorCode >= InstructionEnum.GET_STATIC && operatorCode <= InstructionEnum.MONITOR_EXIT;
    }

    @Override
    public void execute(int operatorCode, StackFrame frame, CodeReader reader) {
        switch (operatorCode) {
            case InstructionEnum.GET_STATIC:
                getStatic(frame, reader);
                break;
            case InstructionEnum.PUT_STATIC:
                putStatic(frame, reader);
                break;
            case InstructionEnum.GET_FIELD:
                getField(frame, reader);
                break;
            case InstructionEnum.PUT_FIELD:
                putField(frame, reader);
                break;
            case InstructionEnum.INVOKE_VIRTUAL:
                invokeVirtual(frame, reader);
                break;
            case InstructionEnum.INVOKE_SPECIAL:
                invokeSpecial(frame, reader);
                break;
            case InstructionEnum.INVOKE_STATIC:
                invokeStatic(frame, reader);
                break;
            case InstructionEnum.INVOKE_INTERFACE:
                invokeInterface(frame, reader);
                break;
            case InstructionEnum.NEW:
                newObject(frame, reader);
                break;
            case InstructionEnum.NEW_ARRAY:
                newArray(frame, reader);
                break;
            case InstructionEnum.A_NEW_ARRAY:
                aNewArray(frame, reader);
                break;
            case InstructionEnum.ARRAY_LENGTH:
                arrayLength(frame, reader);
                break;
            case InstructionEnum.A_THROW:
                aThrow(frame, reader);
                break;
            case InstructionEnum.CHECK_CAST:
                checkCast(frame, reader);
                break;
            case InstructionEnum.INSTANCE_OF:
                instanceOf(frame, reader);
                break;
            default:
                throw new RuntimeException("Operator Code: " + operatorCode + " Never Found Instruction");
        }
    }

    private void getStatic(StackFrame frame, CodeReader reader) {
        MyLog.command("getstatic");
        int index = reader.readShort();
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

    private void putStatic(StackFrame frame, CodeReader reader) {
        MyLog.command("putstatic");
        int index = reader.readShort();
        MyMethod currentMethod = frame.getMethod();
        MyClass currentMethodClazz = currentMethod.getClazz();
        RuntimeConstantPool runtimeConstantPool = currentMethodClazz.getRuntimeConstantPool();
        MyFieldRef fieldRef = (MyFieldRef) runtimeConstantPool.getConstants(index);
        MyField field = fieldRef.resolveField();
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

    private void getField(StackFrame frame, CodeReader reader) {
        MyLog.command("getfield");
        int index = reader.readShort();
        RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getRuntimeConstantPool();
        MyFieldRef fieldRef = (MyFieldRef) runtimeConstantPool.getConstants(index);
        MyField field = fieldRef.resolveField();
        if (field.isStatic()) {
            throw new IncompatibleClassChangeError();
        }
        OperandStack operandStack = frame.getOperandStack();
        MyObject ref = operandStack.popRef();
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

    private void putField(StackFrame frame, CodeReader reader) {
        MyLog.command("putfield");
        int index = reader.readShort();
        MyMethod currentMethod = frame.getMethod();
        MyClass currentMethodClazz = currentMethod.getClazz();
        RuntimeConstantPool runtimeConstantPool = currentMethodClazz.getRuntimeConstantPool();
        MyFieldRef fieldRef = (MyFieldRef) runtimeConstantPool.getConstants(index);
        MyField field = fieldRef.resolveField();
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
                MyObject refInt = operandStack.popRef();
                if (null == refInt) {
                    throw new NullPointerException();
                }
                refInt.getFields().setInteger(slotIndex, integerValue);
                break;
            case 'F':
                float floatValue = operandStack.popFloat();
                MyObject refFloat = operandStack.popRef();
                if (null == refFloat) {
                    throw new NullPointerException();
                }
                refFloat.getFields().setFloat(slotIndex, floatValue);
                break;
            case 'J':
                long longValue = operandStack.popLong();
                MyObject refLong = operandStack.popRef();
                if (null == refLong) {
                    throw new NullPointerException();
                }
                refLong.getFields().setLong(slotIndex, longValue);
                break;
            case 'D':
                double doubleValue = operandStack.popDouble();
                MyObject refDouble = operandStack.popRef();
                if (refDouble == null) {
                    throw new NullPointerException();
                }
                refDouble.getFields().setDouble(slotIndex, doubleValue);
                break;
            case 'L':
            case '[':
                MyObject value = operandStack.popRef();
                MyObject ref = operandStack.popRef();
                if (ref == null) {
                    throw new NullPointerException();
                }
                ref.getFields().setObjectRef(slotIndex, value);
                break;
            default:
                break;
        }
    }

    private void invokeVirtual(StackFrame frame, CodeReader reader) {
        MyLog.command("invokevirtual");
        int index = reader.readShort();
        MyClass currentClass = frame.getMethod().getClazz();
        RuntimeConstantPool runtimeConstantPool = currentClass.getRuntimeConstantPool();
        MyMethodRef methodRef = (MyMethodRef) runtimeConstantPool.getConstants(index);
        MyMethod method = methodRef.resolveMethod();
        if (method.isStatic()) {
            throw new IncompatibleClassChangeError();
        }
        MyObject refFromTop = (MyObject) frame.getOperandStack().getRefFromTop(method.getArgsCount());
        if (refFromTop == null) {
            if ("println".equals(methodRef.getMethodName())) {
                println(frame.getOperandStack(), methodRef.getDescriptor());
                return;
            }
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

        MyMethod methodBeInvoke = MyMethodRef.lookupMethod(refFromTop.getClazz(), methodRef.getMethodName(), methodRef.getDescriptor());
        if (methodBeInvoke == null || methodBeInvoke.isAbstract()) {
            throw new AbstractMethodError();
        }
        MethodInvokeLogic.invokeMethod(frame, methodBeInvoke);

    }

    private void invokeSpecial(StackFrame frame, CodeReader reader) {
        MyLog.command("invokespecial");
        int index = reader.readShort();
        MyClass currentClass = frame.getMethod().getClazz();
        RuntimeConstantPool runtimeConstantPool = currentClass.getRuntimeConstantPool();
        MyMethodRef methodRef = (MyMethodRef) runtimeConstantPool.getConstants(index);
        MyClass clazz = methodRef.resolvedClass();
        MyMethod method = methodRef.resolveMethod();
        if ("<init>".equals(method.getName()) && method.getClazz() != clazz) {
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
            MyMethodRef.lookupMethod(currentClass.getSuperClass(), methodRef.getMethodName(), methodRef.getDescriptor());
        }

        if (method.isAbstract()) {
            throw new AbstractMethodError();
        }
        MethodInvokeLogic.invokeMethod(frame, method);
    }

    private void invokeStatic(StackFrame frame, CodeReader reader) {
        MyLog.command("invokestatic");
        int index = reader.readShort();
        RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getRuntimeConstantPool();
        MyMethodRef methodRef = (MyMethodRef) runtimeConstantPool.getConstants(index);
        MyMethod method = methodRef.resolveMethod();

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

    private void invokeInterface(StackFrame frame, CodeReader reader) {
        MyLog.command("invokeinterface");
        int index = reader.readShort();
        reader.readByte();
        reader.readByte();
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

    private void newObject(StackFrame frame, CodeReader reader) {
        MyLog.command("new");
        try {
            int index = reader.readShort();
            RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getRuntimeConstantPool();
            MyClassRef classRef = (MyClassRef) runtimeConstantPool.getConstants(index);
            MyClass myClass = classRef.resolvedClass();
            if (!myClass.isInitStarted()) {
                frame.revertNextPc();
                ClassInitLogic.initClass(frame.getThread(), myClass);
                return;
            }
            if (myClass.isInterface() || myClass.isAbstract()) {
                throw new InstantiationError();
            }
            MyObject object = myClass.newObject();
            frame.getOperandStack().pushRef(object);
        } catch (Exception e) {
            e.printStackTrace();
            MyLog.error(e.getMessage());
        }

    }

    private void newArray(StackFrame frame, CodeReader reader) {
        MyLog.command("newarray");
        byte type = reader.readByte();
        OperandStack operandStack = frame.getOperandStack();
        Integer count = operandStack.popInteger();
        if (count < 0) {
            throw new NegativeArraySizeException();
        }
        MyClassLoader classLoader = frame.getMethod().getClazz().getClassLoader();
        MyClass arrayClass = getPrimitiveArrayClass(classLoader, type);
        MyObject arrayObject = arrayClass.createArray(count);
        operandStack.pushRef(arrayObject);
    }

    private void aNewArray(StackFrame frame, CodeReader reader) {
        MyLog.command("anewarray");
        try {
            int index = reader.readShort();
            RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getRuntimeConstantPool();
            MyClassRef classRef = (MyClassRef) runtimeConstantPool.getConstants(index);
            MyClass componentClass = classRef.resolvedClass();
            OperandStack operandStack = frame.getOperandStack();
            int count = operandStack.popInteger();
            if (count < 0) {
                throw new NegativeArraySizeException();
            }

            MyClass arrayClass = componentClass.getArrayClass();
            MyObject array = arrayClass.createArray(count);
            operandStack.pushRef(array);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void arrayLength(StackFrame frame, CodeReader reader) {
        MyLog.command("arraylength");
        OperandStack operandStack = frame.getOperandStack();
        MyObject arrayRef = operandStack.popRef();
        if (null == arrayRef) {
            throw new NullPointerException();
        }
        int arrayLength = arrayRef.getArrayLength();
        operandStack.pushInteger(arrayLength);
    }

    private void aThrow(StackFrame frame, CodeReader reader) {
        MyLog.command("athrow");
        MyObject exception = frame.getOperandStack().popRef();
        if (null == exception) {
            throw new NullPointerException();
        }
        MyThread thread = frame.getThread();
        if (!findAndGotoExceptionHandler(thread, exception)) {
            handleUncaughtException(thread, exception);
        }

    }

    private void checkCast(StackFrame frame, CodeReader reader) {
        MyLog.command("checkcast");
        int index = reader.readShort();
        OperandStack operandStack = frame.getOperandStack();
        MyObject ref = operandStack.popRef();
        operandStack.pushRef(ref);
        if (null == ref) {
            return;
        }
        RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getRuntimeConstantPool();
        MyClassRef classRef = (MyClassRef) runtimeConstantPool.getConstants(index);
        try {
            MyClass myClass = classRef.resolvedClass();
            if (!ref.isInstanceOf(myClass)) {
                throw new ClassCastException();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void instanceOf(StackFrame frame, CodeReader reader) {
        MyLog.command("instanceof");
        int index = reader.readShort();
        OperandStack operandStack = frame.getOperandStack();
        MyObject ref = operandStack.popRef();
        if (null == ref) {
            operandStack.pushInteger(0);
            return;
        }
        RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getRuntimeConstantPool();
        MyClassRef classRef = (MyClassRef) runtimeConstantPool.getConstants(index);
        try {
            MyClass myClass = classRef.resolvedClass();
            if (ref.isInstanceOf(myClass)) {
                operandStack.pushInteger(1);
            } else {
                operandStack.pushInteger(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void handleUncaughtException(MyThread thread, MyObject exception) {
        thread.clearStack();
        MyObject jMsg = exception.getRefVariable("detailMessage", "Ljava/lang/String;");
        String msg = StringCache.getString(jMsg);
        System.out.println(exception.getClazz().getJavaName() + ": " + msg);
        Object extra = exception.getExtra();
        ThrowableNative[] throwableNatives = (ThrowableNative[]) extra;
        for (ThrowableNative throwableNative : throwableNatives) {
            System.out.println(throwableNative.getString());
        }

    }

    private boolean findAndGotoExceptionHandler(MyThread thread, MyObject exception) {
        do {
            StackFrame frame = thread.getStackTopFrame();
            int pc = frame.getNextPc() - 1;
            int handlerPc = frame.getMethod().findExceptionHandler(exception.getClazz(), pc);
            if (handlerPc > 0) {
                OperandStack operandStack = frame.getOperandStack();
                operandStack.clear();
                operandStack.pushRef(exception);
                frame.setNextPc(handlerPc);
                return true;
            }
            thread.popStackFrame();
        } while (!thread.stackEmpty());
        return false;
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


    private void println(OperandStack stack, String descriptor) {
        String myVMStart = "[MyJVM Said ]: ";
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
                MyObject str = stack.popRef();
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
