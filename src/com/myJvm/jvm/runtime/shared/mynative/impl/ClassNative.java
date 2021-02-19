package com.myJvm.jvm.runtime.shared.mynative.impl;

import com.myJvm.jvm.beancenter.annotations.MyNativeObject;
import com.myJvm.jvm.loadcore.loader.MyClassLoader;
import com.myJvm.jvm.runtime.shared.heap.info.MyClass;
import com.myJvm.jvm.runtime.shared.heap.info.MyObject;
import com.myJvm.jvm.runtime.shared.heap.info.dependence.StringCache;
import com.myJvm.jvm.runtime.shared.mynative.NativeClass;
import com.myJvm.jvm.runtime.threaddependent.LocalVariableTable;
import com.myJvm.jvm.runtime.threaddependent.OperandStack;
import com.myJvm.jvm.runtime.threaddependent.StackFrame;
import com.myJvm.log.MyLog;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 22454
 */
@MyNativeObject
public class ClassNative implements NativeClass {
    private static final String CLASS_NAME = "java/lang/Class";
    private static final ConcurrentHashMap<String, String> NATIVE_METHOD_DESCRIPTOR_MAP = new ConcurrentHashMap<String, String>() {
        {
            put("getPrimitiveClass", "(Ljava/lang/String;)Ljava/lang/Class;");
            put("getName", "()Ljava/lang/String;");
            put("desiredAssertionStatus", "(Ljava/lang/Class;)Z");
            put("registerNatives", "()V");
        }
    };
    @Override
    public void registerNatives(StackFrame frame) {
        MyLog.nativeLog("registerNatives");
        //do nothing
    }

    public void getPrimitiveClass(StackFrame frame) {
        try {
            MyLog.nativeLog("getPrimitiveClass");
            MyObject ref = (MyObject) frame.getLocalVariableTable().getRef(0);
            String className = StringCache.getString(ref);
            MyClassLoader loader = frame.getMethod().getClazz().getClassLoader();
            MyObject reflectClass = loader.findClass(className).getReflectClass();
            frame.getOperandStack().pushRef(reflectClass);
        } catch (Exception e) {
            MyLog.error("Failed To Get Base Class");
            e.printStackTrace();
        }
    }

    public void getName(StackFrame frame) {
        MyLog.nativeLog("getName");
        try {
            MyObject thisObject = (MyObject) frame.getLocalVariableTable().getThis();
            MyClass clazz = (MyClass) thisObject.getExtra();

            String javaName = clazz.getJavaName();
            MyObject string = StringCache.putString(clazz.getClassLoader(), javaName);
            frame.getOperandStack().pushRef(string);
        } catch (Exception e) {
            MyLog.error("Failed To Get Name");
            e.printStackTrace();
        }

    }

    public void desiredAssertionStatus(StackFrame frame) {
        MyLog.nativeLog("desiredAssertionStatus");
        try {
            frame.getOperandStack().pushBoolean(false);
        } catch (Exception e) {
            MyLog.error("Failed To Desired Assertion Status");
            e.printStackTrace();
        }

    }

    public void isInterface(StackFrame frame) {
        MyLog.nativeLog("isInterface");
        try {
            LocalVariableTable localVariableTable = frame.getLocalVariableTable();
            MyObject thisObject = (MyObject) localVariableTable.getThis();
            MyClass clazz = (MyClass) thisObject.getExtra();
            OperandStack operandStack = frame.getOperandStack();
            operandStack.pushBoolean(clazz.isInterface());
        } catch (Exception e) {
            MyLog.error("Determine Whether The Class Interface Fails");
            e.printStackTrace();
        }

    }

    public void isPrimitive(StackFrame frame) {
        MyLog.nativeLog("isPrimitive");
        try {
            LocalVariableTable localVariableTable = frame.getLocalVariableTable();
            MyObject thisObject = (MyObject) localVariableTable.getThis();
            MyClass clazz = (MyClass) thisObject.getExtra();
            OperandStack operandStack = frame.getOperandStack();
            operandStack.pushBoolean(clazz.isPrimitiveClass());
        } catch (Exception e) {
            MyLog.error("Determine Whether The Class Is BaseClass Failed");
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
