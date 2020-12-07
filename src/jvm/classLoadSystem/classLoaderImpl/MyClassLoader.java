package jvm.classLoadSystem.classLoaderImpl;

import jvm.classLoadSystem.analyzer.ClassFile;
import jvm.classLoadSystem.analyzer.ClassFileReader;
import com.sun.istack.internal.NotNull;
import exception.EmClassLoadErr;
import exception.JvmException;
import jvm.runtimeDataArea.shared.heap.info.MyClass;

import java.util.Map;
import java.util.Objects;
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
    MyClass loadClass(@NotNull String absClassName) throws Exception;


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
}