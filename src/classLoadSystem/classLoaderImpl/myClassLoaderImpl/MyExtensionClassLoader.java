package classLoadSystem.classLoaderImpl.myClassLoaderImpl;

import classLoadSystem.classLoaderImpl.MyClassLoader;

/**
 * @author 22454
 */
public class MyExtensionClassLoader extends MyClassLoader {
    public MyExtensionClassLoader(MyClassLoader parentLoader) {
        this.parent = parentLoader;
    }

    @Override
    public byte[] findClass(String absClassName) throws Exception {
        return this.loadClass(absClassName);
    }


}
