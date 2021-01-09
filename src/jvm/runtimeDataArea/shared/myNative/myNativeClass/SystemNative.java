package jvm.runtimeDataArea.shared.myNative.myNativeClass;

import jvm.BeanCenter.MyNativeObject;
import jvm.runtimeDataArea.shared.heap.info.MyClass;
import jvm.runtimeDataArea.shared.heap.info.MyObject;
import jvm.runtimeDataArea.threadDependent.LocalVariableTable;
import jvm.runtimeDataArea.threadDependent.StackFrame;
import log.MyLog;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 22454
 */
@MyNativeObject
public class SystemNative implements NativeClass {
    private static final String CLASS_NAME = "java/lang/System";
    private static final ConcurrentHashMap<String, String> NATIVE_METHOD_DESCRIPTOR_MAP = new ConcurrentHashMap<String, String>() {
        {
            put("arrayCopy", "(Ljava/lang/Object;ILjava/lang/Object;II)V");
            put("registerNatives", "()V");
        }
    };

    @Override
    public void registerNatives(StackFrame frame) {
        MyLog.nativeLog("registerNatives");
        //do nothing
    }

    public void arrayCopy(StackFrame frame) {
        LocalVariableTable localVariableTable = frame.getLocalVariableTable();
        MyObject src = localVariableTable.getRef(0);
        int srcPos = localVariableTable.getInteger(1);
        MyObject dest = localVariableTable.getRef(2);
        int destPos = localVariableTable.getInteger(3);
        int length = localVariableTable.getInteger(4);
        if (null == src || dest == null) {
            throw new NullPointerException();
        }
        if (!checkArrayCopy(src, dest)) {
            throw new ArrayStoreException();
        }
        if (srcPos < 0 || destPos < 0 || length < 0 ||
                srcPos + length > src.getArrayLength() ||
                destPos + length > dest.getArrayLength()) {
            throw new IndexOutOfBoundsException();
        }
        Object data = src.getData();
        if (data instanceof byte[]) {
            dest.setData(Arrays.copyOfRange(((byte[]) src.getData()), srcPos, srcPos + length));
            return;
        }
        if (data instanceof char[]) {
            dest.setData(Arrays.copyOfRange(((char[]) src.getData()), srcPos, srcPos + length));
            return;
        }
        if (data instanceof short[]) {
            dest.setData(Arrays.copyOfRange(((short[]) src.getData()), srcPos, srcPos + length));
            return;
        }
        if (data instanceof int[]) {
            dest.setData(Arrays.copyOfRange(((int[]) src.getData()), srcPos, srcPos + length));
            return;
        }
        if (data instanceof double[]) {
            dest.setData(Arrays.copyOfRange(((double[]) src.getData()), srcPos, srcPos + length));
            return;
        }
        if (data instanceof float[]) {
            dest.setData(Arrays.copyOfRange(((float[]) src.getData()), srcPos, srcPos + length));
            return;
        }
        if (data instanceof MyObject[]) {
            dest.setData(Arrays.copyOfRange(((MyObject[]) src.getData()), srcPos, srcPos + length));
            return;
        }
        throw new RuntimeException("Not array!");

    }

    private boolean checkArrayCopy(MyObject src, MyObject dest) {
        try {
            MyClass srcClazz = src.getClazz();
            MyClass destClazz = dest.getClazz();
            if (!srcClazz.isArray() || !destClazz.isArray()) {
                return false;
            }
            if (srcClazz.componentClass().isPrimitiveClass() || destClazz.componentClass().isPrimitiveClass()) {
                return srcClazz == destClazz;
            }
            return true;
        } catch (Exception e) {
            MyLog.error("Src Object Or Dest Object Not Array");
            e.printStackTrace();
        }
        return false;

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
