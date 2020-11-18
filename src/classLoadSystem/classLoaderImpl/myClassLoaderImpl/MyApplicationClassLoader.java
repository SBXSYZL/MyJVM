package classLoadSystem.classLoaderImpl.myClassLoaderImpl;


import classLoadSystem.classLoaderImpl.MyClassLoader;
import exception.EmClassLoadErr;
import exception.JvmException;

/**
 * @author 22454
 */
public class MyApplicationClassLoader extends MyClassLoader {
    public MyApplicationClassLoader(MyClassLoader parentLoader) {
        this.parent = parentLoader;
    }

    @Override
    public byte[] findClass(String absClassName) throws Exception {
        byte[] byteCode = this.loadClass(absClassName);
        if (byteCode != null) {
            return byteCode;
        }
        throw new JvmException(EmClassLoadErr.FAIL_TO_LOAD_CLASS, "Class: [ ".concat(absClassName).concat(" ] Load Fail."));
    }
}
