package jvm.runtimeDataArea.shared.myNative.myNativeClass;

import jvm.BeanCenter.NativeObject;
import jvm.runtimeDataArea.threadDependent.StackFrame;
import log.MyLog;

import javax.print.attribute.standard.MediaSize;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 22454
 */
@NativeObject
public class DoubleNative implements NativeClass {
    private static final String CLASS_NAME = "java/lang/Double";
    private static final ConcurrentHashMap<String, String> NATIVE_METHOD_DESCRIPTOR_MAP = new ConcurrentHashMap<String, String>() {
        {
            put("doubleToRawLongBits", "(D)J");
            put("longBitsToDouble", "(J)D");
        }
    };

    public void doubleToRawLongBits(StackFrame frame) {
        try {
            double value = frame.getLocalVariableTable().getDouble(0);
            frame.getOperandStack().pushLong((long) value);
        } catch (Exception e) {
            MyLog.error("Double To Raw Long Bits Error");
            e.printStackTrace();
        }
    }

    public void longBitsToDouble(StackFrame frame) {
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
