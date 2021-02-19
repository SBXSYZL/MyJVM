package com.myJvm.jvm.runtime.shared.mynative.impl;

import com.myJvm.jvm.beancenter.annotations.MyNativeObject;
import com.myJvm.jvm.runtime.shared.mynative.NativeClass;
import com.myJvm.jvm.runtime.threaddependent.StackFrame;
import com.myJvm.log.MyLog;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 22454
 */
@MyNativeObject
public class DoubleNative implements NativeClass {
    private static final String CLASS_NAME = "java/lang/Double";
    private static final ConcurrentHashMap<String, String> NATIVE_METHOD_DESCRIPTOR_MAP = new ConcurrentHashMap<String, String>() {
        {
            put("doubleToRawLongBits", "(D)J");
            put("longBitsToDouble", "(J)D");
            put("registerNatives", "()V");
        }
    };

    @Override
    public void registerNatives(StackFrame frame) {
        MyLog.nativeLog("registerNatives");
        //do nothing
    }

    public void doubleToRawLongBits(StackFrame frame) {
        MyLog.nativeLog("doubleToRawLongBits");
        try {
            double value = frame.getLocalVariableTable().getDouble(0);
            frame.getOperandStack().pushLong((long) value);
        } catch (Exception e) {
            MyLog.error("Double To Raw Long Bits Error");
            e.printStackTrace();
        }
    }

    public void longBitsToDouble(StackFrame frame) {
        MyLog.nativeLog("longBitsToDouble");
        try {
            long value = frame.getLocalVariableTable().getLong(0);
            frame.getOperandStack().pushDouble((double) value);
        } catch (Exception e) {
            MyLog.error("Long Bits To Double Error");
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
