package jvm.runtimeDataArea.shared.myNative.myNativeClass;

import jvm.BeanCenter.NativeObject;
import jvm.runtimeDataArea.shared.heap.info.MyClass;
import jvm.runtimeDataArea.shared.heap.info.MyObject;
import jvm.runtimeDataArea.threadDependent.StackFrame;
import log.MyLog;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 22454
 */
@NativeObject
public class ObjectNative implements NativeClass {
    private static final String CLASS_NAME = "java/lang/Object";
    private static final ConcurrentHashMap<String, String> NATIVE_METHOD_DESCRIPTOR_MAP = new ConcurrentHashMap<String, String>() {
        {
            put("getClazz", "()Ljava/lang/Class;");
            put("hashCode", "()I");
            put("clone", "()Ljava/lang/Object;");
        }
    };

    public void getClazz(StackFrame frame) {
        try {
            MyObject thisObject = frame.getLocalVariableTable().getThis();
            MyObject reflectClass = thisObject.getClazz().getReflectClass();
            frame.getOperandStack().pushRef(reflectClass);
        } catch (Exception e) {
            MyLog.error("Failed To Get Reflect Class");
            e.printStackTrace();
        }
    }

    public void hashCode(StackFrame frame) {
        try {
            MyObject thisObject = frame.getLocalVariableTable().getThis();
            frame.getOperandStack().pushInteger(thisObject.hashCode());
        } catch (Exception e) {
            MyLog.error("Failed To Get HashCode");
            e.printStackTrace();
        }
    }

    public void clone(StackFrame frame) {
        try {
            MyObject thisObject = frame.getLocalVariableTable().getThis();
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
