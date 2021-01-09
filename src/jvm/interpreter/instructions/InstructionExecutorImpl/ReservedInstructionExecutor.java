package jvm.interpreter.instructions.InstructionExecutorImpl;

import jvm.interpreter.CodeReader;
import jvm.interpreter.instructions.InstructionEnum;
import jvm.interpreter.instructions.InstructionExecutor;
import jvm.runtimeDataArea.shared.heap.info.MyMethod;
import jvm.runtimeDataArea.shared.myNative.MyNativeMethod;
import jvm.runtimeDataArea.shared.myNative.NativeMethodTable;
import jvm.runtimeDataArea.threadDependent.StackFrame;
import log.MyLog;
import netscape.security.UserTarget;

/**
 * @author 22454
 */
public class ReservedInstructionExecutor implements InstructionExecutor {
    public boolean isReservedInstruction(int operatorCode) {
        return operatorCode == InstructionEnum.BREAK_POINT ||
                operatorCode == InstructionEnum.IMP_DEP_1 ||
                operatorCode == InstructionEnum.IMP_DEP_2;
    }

    @Override
    public void execute(int operatorCode, StackFrame frame, CodeReader reader) {
        invokeNative(frame);
    }

    private void invokeNative(StackFrame frame) {
        MyLog.command("invokenative");
        MyMethod method = frame.getMethod();
        String className = method.getClazz().getClassName();
        String methodName = method.getName();
        String methodDescriptor = method.getDescriptor();
        MyNativeMethod nativeMethod = NativeMethodTable.findNativeMethod(className, methodName, methodDescriptor);
        if (null == nativeMethod) {
            String methodInfo = className + "." + methodName + methodDescriptor;
            throw new UnsatisfiedLinkError(methodInfo);
        }
        nativeMethod.invoke(frame);
    }
}
