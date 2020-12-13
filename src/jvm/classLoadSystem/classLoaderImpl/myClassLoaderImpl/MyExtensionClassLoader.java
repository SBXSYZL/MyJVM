package jvm.classLoadSystem.classLoaderImpl.myClassLoaderImpl;

import jvm.BeanCenter.MyAutoWired;
import jvm.BeanCenter.MyBean;
import jvm.classLoadSystem.classLoaderImpl.MyClassLoader;
import jvm.runtimeDataArea.shared.heap.info.MyClass;

/**
 * @author 22454
 */
@MyBean
public class MyExtensionClassLoader implements MyClassLoader {
    @MyAutoWired
    private MyBootstrapClassLoader parent;


    @Override
    public MyClass findClass(String absClassName) throws Exception {
        absClassName = MyClassLoader.checkClassName(absClassName);
        MyClass myClass = CACHE.get(absClassName);
        if (myClass != null) {
            return myClass;
        }
        return this.loadClass(absClassName);
    }

    @Override
    public MyClass loadClass(String absClassName) throws Exception {
        if (parent != null) {
            return parent.findClass(absClassName);
        }
        return null;
    }

    @Override
    public MyClass loadArrayClass(String absClassName) {
        return null;
    }

    @Override
    public MyClass loadNonArrayClass(String absClassName) {
        return null;
    }

    @Override
    public void verifyByteCode(String[] byteCode) {

    }

    @Override
    public MyClassLoader getParent() {
        return parent;
    }


}
