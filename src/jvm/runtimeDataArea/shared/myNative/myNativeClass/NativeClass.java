package jvm.runtimeDataArea.shared.myNative.myNativeClass;

import java.util.Map;

/**
 * @author 22454
 */
public interface NativeClass {

    String getClassName();

    Map<String, String> getNativeMethodDescriptorMap();
}
