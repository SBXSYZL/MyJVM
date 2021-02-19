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
public class FloatNative implements NativeClass {
    private static final String CLASS_NAME = "java/lang/Float";
    private static final ConcurrentHashMap<String, String> NATIVE_METHOD_DESCRIPTOR_MAP = new ConcurrentHashMap<String, String>() {
        {
            put("floatToRawIntBits", "(F)I");
            put("intBitsToFloat", "(I)F");
            put("registerNatives", "()V");
        }
    };

    @Override
    public void registerNatives(StackFrame frame) {
        MyLog.nativeLog("registerNatives");
        //do nothing
    }

    public void floatToRawIntegerBits(StackFrame frame) {
        MyLog.nativeLog("floatToRawIntegerBits");
        try {
            float value = frame.getLocalVariableTable().getFloat(0);
            frame.getOperandStack().pushInteger((int) value);
        } catch (Exception e) {
            MyLog.error("Float To Raw Integer Bits Error");
            e.printStackTrace();
        }
    }

    public void integerBitsToFloat(StackFrame frame) {
        MyLog.nativeLog("integerBitsToFloat");
        try {
            int value = frame.getLocalVariableTable().getInteger(0);
            frame.getOperandStack().pushInteger(value);
        } catch (Exception e) {
            MyLog.error("Integer Bits To Float Error");
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
