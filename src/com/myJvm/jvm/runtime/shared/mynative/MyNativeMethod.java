package com.myJvm.jvm.runtime.shared.mynative;

import com.myJvm.jvm.runtime.threaddependent.StackFrame;
import com.myJvm.log.MyLog;

import java.lang.reflect.Method;

/**
 * @author 22454
 */
public final class MyNativeMethod {
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
