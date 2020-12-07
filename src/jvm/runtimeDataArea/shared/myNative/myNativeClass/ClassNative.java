package jvm.runtimeDataArea.shared.myNative.myNativeClass;

import jvm.BeanCenter.NativeObject;
import jvm.classLoadSystem.classLoaderImpl.MyClassLoader;
import jvm.runtimeDataArea.shared.heap.info.MyClass;
import jvm.runtimeDataArea.shared.heap.info.MyObject;
import jvm.runtimeDataArea.shared.heap.info.dependence.StringCache;
import jvm.runtimeDataArea.threadDependent.LocalVariableTable;
import jvm.runtimeDataArea.threadDependent.OperandStack;
import jvm.runtimeDataArea.threadDependent.StackFrame;
import log.MyLog;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 22454
 */
@NativeObject
public class ClassNative implements NativeClass {
    private static final String CLASS_NAME = "java/lang/Class";
    private static final ConcurrentHashMap<String, String> NATIVE_METHOD_DESCRIPTOR_MAP = new ConcurrentHashMap<String, String>() {
        {
            put("getPrimitiveClass", "(Ljava/lang/String;)Ljava/lang/Class;");
            put("getName", "()Ljava/lang/String;");
            put("desiredAssertionStatus", "(Ljava/lang/Class;)Z");
        }
    };

    public void getPrimitiveClass(StackFrame frame) {
        try {
            MyObject ref = frame.getLocalVariableTable().getRef(0);
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
        try {
            MyObject thisObject = (MyObject) frame.getLocalVariableTable().getThis();
            MyClass clazz = (MyClass) thisObject.getExtra();

            String javaName = clazz.getJavaName();
            MyObject string = StringCache.string(clazz.getClassLoader(), javaName);
            frame.getOperandStack().pushRef(string);
        } catch (Exception e) {
            MyLog.error("Failed To Get Name");
            e.printStackTrace();
        }

    }

    public void desiredAssertionStatus(StackFrame frame) {
        try {
            frame.getOperandStack().pushBoolean(false);
        } catch (Exception e) {
            MyLog.error("Failed To Desired Assertion Status");
            e.printStackTrace();
        }

    }

    public void isInterface(StackFrame frame) {
        try {
            LocalVariableTable localVariableTable = frame.getLocalVariableTable();
            MyObject thisObject = localVariableTable.getThis();
            MyClass clazz = (MyClass) thisObject.getExtra();
            OperandStack operandStack = frame.getOperandStack();
            operandStack.pushBoolean(clazz.isInterface());
        } catch (Exception e) {
            MyLog.error("Determine Whether The Class Interface Fails");
            e.printStackTrace();
        }

    }

    public void isPrimitive(StackFrame frame) {
        try {
            LocalVariableTable localVariableTable = frame.getLocalVariableTable();
            MyObject thisObject = localVariableTable.getThis();
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
