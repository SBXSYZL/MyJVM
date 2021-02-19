package com.myJvm.jvm.runtime.shared.mynative;

import com.myJvm.jvm.runtime.threaddependent.StackFrame;

import java.util.Map;

/**
 * @author 22454
 */
public interface NativeClass {

    String getClassName();

    Map<String, String> getNativeMethodDescriptorMap();

    public void registerNatives(StackFrame frame);
}
