package com.myJvm.jvm.loadcore.loader;

import com.myJvm.jvm.runtime.shared.heap.info.MyClass;
import com.sun.istack.internal.NotNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 22454
 */
public interface MyClassLoader {
    ConcurrentHashMap<String, MyClass> CACHE = new ConcurrentHashMap<>();

    ConcurrentHashMap<String, String> PRIMITIVE_TYPE = new ConcurrentHashMap<String, String>() {
        {
            put("void", "V");
            put("boolean", "Z");
            put("byte", "B");
            put("short", "S");
            put("int", "I");
            put("long", "J");
            put("char", "C");
            put("float", "F");
            put("double", "D");
        }
    };

    /**
     * 查找类字节码
     *
     * @param absClassName 完整类名
     * @return 字节码字符串数组
     * @throws Exception 查找失败
     */
    MyClass findClass(@NotNull String absClassName) throws Exception;

    /**
     * 加载字节码
     *
     * @param absClassName 全限定类名
     * @return 字节码
     * @throws Exception ex
     */
    default MyClass loadClass(@NotNull String absClassName) throws Exception{
        MyClass myClass;
        myClass = CACHE.get(absClassName);
        if (myClass != null) {
            return myClass;
        }
        if (getParent() != null) {
            myClass = getParent().findClass(absClassName);
        }
        if (myClass == null) {
            if (absClassName.getBytes()[0] == '[') {
                myClass = loadArrayClass(absClassName);
            } else {
                myClass = loadNonArrayClass(absClassName);
            }
        }
        if (myClass != null) {
            CACHE.put(absClassName, myClass);
        }
        return myClass;
    };


    MyClass loadArrayClass(@NotNull String absClassName);

    MyClass loadNonArrayClass(@NotNull String absClassName) throws Exception;

    /**
     * 检验字节码格式
     *
     * @param byteCode 字节码字符串数组
     */
    void verifyByteCode(String[] byteCode);

    /**
     * 获取父加载器
     *
     * @return 获取父加载器
     */
    MyClassLoader getParent();

    static String checkClassName(String absClassName) {
        return absClassName.replaceAll("/", ".");
    }

    static String getComponentClassName(String className) {
        if (className.getBytes()[0] == '[') {
            String componentTypeDescriptor = className.substring(1);
            return toClassName(componentTypeDescriptor);
        }
        throw new RuntimeException("Not Array: " + className);
    }

    static String toClassName(String componentTypeDescriptor) {
        byte descByte = componentTypeDescriptor.getBytes()[0];
        if (descByte == '[') {
            return componentTypeDescriptor;
        }
        if (descByte == 'L') {
            return componentTypeDescriptor.substring(1, componentTypeDescriptor.length() - 1);
        }
        for (Map.Entry<String, String> entry : PRIMITIVE_TYPE.entrySet()) {
            if (entry.getValue().equals(componentTypeDescriptor)) {
                return entry.getKey();
            }
        }
        throw new RuntimeException("Invalid Descriptor: " + componentTypeDescriptor);
    }

    static String getArrayClassName(String className) {
        String result = "[";
        if (className.getBytes()[0] == '[') {
            result += className;
        }
        String d = PRIMITIVE_TYPE.get(className);
        if (null != d) {
            result += d;
        }
        return result += ("L" + className + ";");
    }

    class ClassEntry {
        private MyClassLoader loader;
        private byte[] byteCode;

        public ClassEntry(MyClassLoader loader, byte[] byteCode) {
            this.loader = loader;
            this.byteCode = byteCode;
        }

        public MyClassLoader getLoader() {
            return loader;
        }

        public byte[] getByteCode() {
            return byteCode;
        }
    }

    static void showCache() {
        CACHE.forEach((k, v) -> {
            System.out.println(k + "\t\t\t" + v.getClassName());
        });
    }
}
