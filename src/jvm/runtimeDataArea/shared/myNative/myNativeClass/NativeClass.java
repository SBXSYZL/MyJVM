package jvm.runtimeDataArea.shared.myNative.myNativeClass;

import jvm.runtimeDataArea.threadDependent.StackFrame;

import java.util.Map;

/**
 * @author 22454
 */
public interface NativeClass {

    String getClassName();

    Map<String, String> getNativeMethodDescriptorMap();

    public void registerNatives(StackFrame frame);
}
