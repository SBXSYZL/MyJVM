package jvm.runtimeDataArea.shared.myNative;

import jvm.runtimeDataArea.shared.heap.info.MyMethod;
import jvm.runtimeDataArea.shared.heap.info.MyObject;
import jvm.runtimeDataArea.threadDependent.StackFrame;
import log.MyLog;

import java.lang.reflect.Method;

/**
 * @author 22454
 */
public class MyNativeMethod {
    private String methodName;
    private Object object;

    public MyNativeMethod(String methodName, Object object) {
        this.methodName = methodName;
        this.object = object;
    }

    public void invoke(StackFrame frame) {
        try {
            Method method = object.getClass().getMethod(methodName, frame.getClass());
            method.invoke(object, frame);
        } catch (Exception e) {
            MyLog.error("Failed To Invoke Native Method.");
            e.printStackTrace();
        }
    }
}
