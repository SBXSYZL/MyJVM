package classLoadSystem.classLoaderImpl.myClassLoaderImpl;

import classLoadSystem.analyzer.ClassFileReader;
import classLoadSystem.classLoaderImpl.MyClassLoader;
import exception.EmClassLoadErr;
import exception.JvmException;

/**
 * @author 22454
 */
public class MyBootstrapClassLoader extends MyClassLoader {

    @Override
    public byte[] findClass(String absClassName) throws Exception {
        byte[] byteCode;
        try {
            String property = System.getProperty("java.home");
            byteCode = ClassFileReader.findClass(absClassName, property);
        } catch (Exception e) {
            e.printStackTrace();
            throw new JvmException(EmClassLoadErr.FAIL_TO_LOAD_CLASS, "Class: [ " + absClassName + " ] Load Fail.");
        }
        return byteCode;
    }
}
