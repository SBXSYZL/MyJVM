package com.myJvm.jvm.runtime.shared.mynative.impl;

import com.myJvm.jvm.beancenter.annotations.MyNativeObject;
import com.myJvm.jvm.runtime.shared.heap.info.MyClass;
import com.myJvm.jvm.runtime.shared.heap.info.MyObject;
import com.myJvm.jvm.runtime.shared.mynative.NativeClass;
import com.myJvm.jvm.runtime.threaddependent.StackFrame;
import com.myJvm.log.MyLog;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 22454
 */
@MyNativeObject
public class ObjectNative implements NativeClass {
    private static final String CLASS_NAME = "java/lang/Object";
    private static final ConcurrentHashMap<String, String> NATIVE_METHOD_DESCRIPTOR_MAP = new ConcurrentHashMap<String, String>() {
        {
            put("registerNatives", "()V");
            put("getClazz", "()Ljava/lang/Class;");
            put("hashCode", "()I");
            put("clone", "()Ljava/lang/Object;");
        }
    };

    @Override
    public void registerNatives(StackFrame frame) {
        MyLog.nativeLog("registerNatives");
        //do nothing
    }

    public void getClazz(StackFrame frame) {
        MyLog.nativeLog("getClass");
        try {
            MyObject thisObject = (MyObject) frame.getLocalVariableTable().getThis();
            MyObject reflectClass = thisObject.getClazz().getReflectClass();
            frame.getOperandStack().pushRef(reflectClass);
        } catch (Exception e) {
            MyLog.error("Failed To Get Reflect Class");
            e.printStackTrace();
        }
    }

    public void hashCode(StackFrame frame) {
        MyLog.nativeLog("hashCode");
        try {
            MyObject thisObject = (MyObject) frame.getLocalVariableTable().getThis();
            frame.getOperandStack().pushInteger(thisObject.hashCode());
        } catch (Exception e) {
            MyLog.error("Failed To Get HashCode");
            e.printStackTrace();
        }
    }

    public void clone(StackFrame frame) {
        MyLog.nativeLog("clone");
        try {
            MyObject thisObject = (MyObject) frame.getLocalVariableTable().getThis();
            MyClass cloneable = thisObject.getClazz().getClassLoader().findClass("java/lang/Cloneable");
            if (!thisObject.getClazz().isImplements(cloneable)) {
                throw new CloneNotSupportedException();
            }
            frame.getOperandStack().pushRef(thisObject);
        } catch (Exception e) {
            MyLog.error("Failed To Clone Object");
            e.printStackTrace();
        }
    }

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }

    @Override
    public Map<String, String> getNativeMethodDescriptorMap() {
        return NATIVE_METHOD_DESCRIPTOR_MAP;
    }
}
