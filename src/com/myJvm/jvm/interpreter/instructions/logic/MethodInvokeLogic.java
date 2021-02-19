package com.myJvm.jvm.interpreter.instructions.logic;

import com.myJvm.jvm.runtime.MyThread;
import com.myJvm.jvm.runtime.shared.heap.info.MyMethod;
import com.myJvm.jvm.runtime.threaddependent.StackFrame;
import com.myJvm.jvm.runtime.threaddependent.VariableSlot;

/**
 * @author 22454
 */
public class MethodInvokeLogic {

    public static void invokeMethod(StackFrame invokeFrame, MyMethod method) {
        MyThread thread = invokeFrame.getThread();
        StackFrame newStackFrame = thread.createNewStackFrame(method);
        thread.pushStackFrame(newStackFrame);
        int argsCount = method.getArgsCount();
        if (argsCount > 0) {
            for (int i = argsCount - 1; i >= 0; i--) {
                VariableSlot variableSlot = invokeFrame.getOperandStack().popVariableSlot();
                newStackFrame.getLocalVariableTable().putSlot(i, variableSlot);
            }
        }
    }
}
