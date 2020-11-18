package classLoadSystem.classLoaderImpl;

import classLoadSystem.analyzer.ClassFileReader;
import com.sun.istack.internal.NotNull;
import exception.EmClassLoadErr;
import exception.EmCommandErr;
import exception.JvmException;

import java.util.Objects;

/**
 * @author 22454
 */
public abstract class MyClassLoader {
    protected MyClassLoader parent = null;

    /**
     * 查找类字节码
     *
     * @param absClassName 完整类名
     * @return 字节码字符串数组
     * @throws Exception 查找失败
     */
    public abstract byte[] findClass(@NotNull String absClassName) throws Exception;

    protected byte[] loadClass(@NotNull String absClassName) throws Exception {
        byte[] byteCode = null;
        if (parent != null) {
            byteCode = parent.findClass(absClassName);
        }
        if (byteCode == null) {
            try {
                String path = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
                byteCode = ClassFileReader.findClass(absClassName, path);
            } catch (Exception e) {
                e.printStackTrace();
                throw new JvmException(EmClassLoadErr.CLASS_NOT_FOUND, "Class: [ " + absClassName + " ] Not Found.");
            }
        }
        return byteCode;
    }

    /**
     * 检验字节码格式
     *
     * @param byteCode 字节码字符串数组
     */
    void verifyByteCode(String[] byteCode) {

    }

    /**
     * 获取父加载器
     */
    MyClassLoader getParent() {
        return parent;
    }
}
