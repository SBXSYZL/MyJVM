package jvm.runtimeDataArea.shared.myNative.myNativeClass;

import jvm.BeanCenter.NativeObject;
import jvm.runtimeDataArea.threadDependent.StackFrame;
import log.MyLog;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 22454
 */
@NativeObject
public class FloatNative implements NativeClass {
    private static final String CLASS_NAME = "java/lang/Float";
    private static final ConcurrentHashMap<String, String> NATIVE_METHOD_DESCRIPTOR_MAP = new ConcurrentHashMap<String, String>() {
        {
            put("floatToRawIntBits", "(F)I");
            put("intBitsToFloat", "(I)F");
        }
    };

    public void floatToRawIntegerBits(StackFrame frame) {
        try {
            float value = frame.getLocalVariableTable().getFloat(0);
            frame.getOperandStack().pushInteger((int) value);
        } catch (Exception e) {
            MyLog.error("Float To Raw Integer Bits Error");
            e.printStackTrace();
        }
    }

    public void integerBitsToFloat(StackFrame frame) {
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
