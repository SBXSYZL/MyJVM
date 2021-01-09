package jvm.interpreter.instructions.logic;

import jvm.runtimeDataArea.MyThread;
import jvm.runtimeDataArea.shared.heap.info.MyClass;
import jvm.runtimeDataArea.shared.heap.info.MyMethod;
import jvm.runtimeDataArea.threadDependent.StackFrame;

/**
 * @author 22454
 */
public class ClassInitLogic {
    public static void initClass(MyThread thread, MyClass clazz) {
        clazz.setInitStarted(true);
        scheduleClassInit(thread, clazz);
        initSuperClass(thread, clazz);
    }

    private static void scheduleClassInit(MyThread thread, MyClass clazz) {
        MyMethod constructor = clazz.getClassInitMethod();
        if (null == constructor) {
            return;
        }
        StackFrame newStackFrame = thread.createNewStackFrame(constructor);
        thread.pushStackFrame(newStackFrame);
    }

    private static void initSuperClass(MyThread thread, MyClass clazz) {
        if (clazz.isInterface()) {
            return;
        }
        MyClass superClass = clazz.getSuperClass();
        if (superClass != null && !superClass.isInitStarted()) {
            initClass(thread, superClass);
        }
    }
}
