package jvm.runtimeDataArea.shared.myNative;

import jdk.nashorn.internal.objects.NativeObject;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 22454
 */
public class NativeMethodTable {
    private static final ConcurrentHashMap<String, MyNativeMethod> NATIVE_METHOD_TABLE = new ConcurrentHashMap<>();

    public static void register(String className, String methodName, String methodDescriptor, MyNativeMethod method) {
        String key = className + "~" + methodName + "~" + methodDescriptor;
        NATIVE_METHOD_TABLE.put(key, method);
    }

    public static MyNativeMethod findNativeMethod(String className, String methodName, String methodDescriptor) {
        String key = className + "~" + methodName + "~" + methodDescriptor;
        return NATIVE_METHOD_TABLE.get(key);
    }
}
