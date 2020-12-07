package jvm.runtimeDataArea.shared.myNative.myNativeClass;

import jvm.BeanCenter.NativeObject;
import jvm.runtimeDataArea.MyThread;
import jvm.runtimeDataArea.shared.heap.info.MyClass;
import jvm.runtimeDataArea.shared.heap.info.MyMethod;
import jvm.runtimeDataArea.shared.heap.info.MyObject;
import jvm.runtimeDataArea.threadDependent.StackFrame;
import log.MyLog;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 22454
 */
@NativeObject
public class ThrowableNative implements NativeClass {
    private static final String CLASS_NAME = "java/lang/Throwable";
    private StackTraceElement stackTraceElement;
    private static final ConcurrentHashMap<String, String> NATIVE_METHOD_DESCRIPTOR_MAP = new ConcurrentHashMap<String, String>() {
        {
            put("fillInStackTrace", "(I)Ljava/lang/Throwable;");
        }
    };

    public String getString() {
        return String.format("%s.%s(%s:%d)",
                this.stackTraceElement.getClassName(),
                this.stackTraceElement.getMethodName(),
                this.stackTraceElement.getFileName(),
                this.stackTraceElement.getLineNumber());
    }

    public void fillInStackTrace(StackFrame frame) {
        try {
            MyObject thisObject = frame.getLocalVariableTable().getThis();
            frame.getOperandStack().pushRef(thisObject);
            ThrowableNative[] stackTraceElements = createStackTraceElements(thisObject, frame.getThread());
            thisObject.setExtra(stackTraceElements);

        } catch (Exception e) {
            MyLog.error("Failed To Throw FillInStackTrace");
            e.printStackTrace();
        }

    }

    private ThrowableNative[] createStackTraceElements(MyObject tObj, MyThread thread) {
        int skip = distanceToObject(tObj.getClazz()) + 2;
        StackFrame[] frames = thread.getStackFrames();
        ThrowableNative[] stes = new ThrowableNative[frames.length - skip];
        int index = 0;
        for (int i = skip; i < frames.length; i++) {
            stes[index] = createStackTraceElement(frames[i]);
        }
        return stes;
    }

    private ThrowableNative createStackTraceElement(StackFrame frame) {
        MyMethod method = frame.getMethod();
        MyClass clazz = method.getClazz();

        StackTraceElement stackTraceElement = new StackTraceElement();
        stackTraceElement.setFileName(clazz.getSourceFile());
        stackTraceElement.setClassName(clazz.getClassName());
        stackTraceElement.setMethodName(method.getName());
        stackTraceElement.setLineNumber(method.getLineNumber(frame.getNextPc() - 1));

        ThrowableNative throwableNative = new ThrowableNative();
        throwableNative.setStackTraceElement(stackTraceElement);
        return throwableNative;

    }

    private int distanceToObject(MyClass clazz) {
        int distance = 0;
        for (MyClass c = clazz.getSuperClass(); c != null; c = c.getSuperClass()) {
            distance++;
        }
        return distance;
    }

    public void setStackTraceElement(StackTraceElement stackTraceElement) {
        this.stackTraceElement = stackTraceElement;
    }

    private static class StackTraceElement {
        private String fileName;
        private String className;
        private String methodName;
        private int lineNumber;

        public String getFileName() {
            return fileName;
        }

        public String getClassName() {
            return className;
        }

        public String getMethodName() {
            return methodName;
        }

        public int getLineNumber() {
            return lineNumber;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }

        public void setLineNumber(int lineNumber) {
            this.lineNumber = lineNumber;
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
