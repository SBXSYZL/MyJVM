package jvm.classLoadSystem.classLoaderImpl.myClassLoaderImpl;


import jvm.BeanCenter.MyAutoWired;
import jvm.BeanCenter.MyBean;
import jvm.classLoadSystem.analyzer.ClassFile;
import jvm.classLoadSystem.analyzer.ClassFileReader;
import jvm.classLoadSystem.classLoaderImpl.MyClassLoader;
import exception.EmClassLoadErr;
import exception.JvmException;
import jvm.runtimeDataArea.common.AccessPermission;
import jvm.runtimeDataArea.shared.heap.builder.MyClassBuilder;
import jvm.runtimeDataArea.shared.heap.info.MyClass;
import log.MyLog;

import java.util.Objects;

/**
 * @author 22454
 */
@MyBean
public class MyApplicationClassLoader implements MyClassLoader {
    @MyAutoWired
    private MyExtensionClassLoader parent;
    @MyAutoWired
    private MyClassBuilder classBuilder;


    @Override
    public MyClass findClass(String absClassName) throws Exception {
        absClassName = MyClassLoader.checkClassName(absClassName);
        MyClass myClass;
        myClass = CACHE.get(absClassName);
        if (myClass != null) {
            return myClass;
        }
        myClass = this.loadClass(absClassName);
        if (myClass != null) {
            return myClass;
        }
        MyLog.error("Class: [ ".concat(absClassName).concat(" ] Load Fail."));
        throw new JvmException(EmClassLoadErr.FAIL_TO_LOAD_CLASS, "Class: [ ".concat(absClassName).concat(" ] Load Fail."));
    }

    @Override
    public MyClass loadClass(String absClassName) throws Exception {
        MyClass myClass = null;
        myClass = CACHE.get(absClassName);
        if (myClass != null) {
            return myClass;
        }
        if (parent != null) {
            myClass = parent.findClass(absClassName);
        }
        if (myClass == null) {
            if (absClassName.getBytes()[0] == '[') {
                myClass = loadArrayClass(absClassName);
            } else {
                System.out.println(CACHE);
                myClass = loadNonArrayClass(absClassName);
            }
        }

        return myClass;
    }

    @Override
    public MyClass loadArrayClass(String absClassName) {
        try {
            MyClass clazz = classBuilder.buildArray(
                    AccessPermission.ACC_PUBLIC,
                    absClassName,
                    this,
                    true,
                    this.loadClass(MyClass.ANCESTOR_OBJECT_NAME),
                    new MyClass[]{
                            findClass("java/lang/Cloneable"),
                            findClass("java/io/Serializable")
                    }
            );
            CACHE.put(absClassName, clazz);
            return clazz;
        } catch (Exception e) {
            e.printStackTrace();
            MyLog.error("Failed To Create Array.");
        }
        return null;
    }

    @Override
    public MyClass loadNonArrayClass(String absClassName) throws Exception {
        String path = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
        byte[] byteCode = ClassFileReader.readClass(absClassName, path);
        if (byteCode == null) {
            MyLog.error("Failed To Load Class: [ " + absClassName + " ].");
            throw new JvmException(EmClassLoadErr.FAILED_TO_LOAD_CLASS, "Failed To Load Class: [ " + absClassName + " ].");
        } else {
            MyClass myClass = classBuilder.build(new ClassFile(new ClassEntry(this, byteCode)));
            CACHE.put(absClassName, myClass);
            return myClass;
        }
    }

    @Override
    public void verifyByteCode(String[] byteCode) {

    }

    @Override
    public MyClassLoader getParent() {
        return null;
    }
}
