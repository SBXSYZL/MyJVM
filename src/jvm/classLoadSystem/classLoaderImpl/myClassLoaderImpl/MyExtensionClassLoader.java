package jvm.classLoadSystem.classLoaderImpl.myClassLoaderImpl;

import jvm.BeanCenter.MyAutoWired;
import jvm.BeanCenter.MyBean;
import jvm.classLoadSystem.analyzer.ClassFile;
import jvm.classLoadSystem.analyzer.ClassFileReader;
import jvm.classLoadSystem.classLoaderImpl.MyClassLoader;
import jvm.runtimeDataArea.common.AccessPermission;
import jvm.runtimeDataArea.shared.heap.builder.MyClassBuilder;
import jvm.runtimeDataArea.shared.heap.info.MyClass;
import log.MyLog;

/**
 * @author 22454
 */
@MyBean
public class MyExtensionClassLoader implements MyClassLoader {
    @MyAutoWired
    private MyBootstrapClassLoader parent;
    @MyAutoWired
    private MyClassBuilder classBuilder;

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
        MyClass myClass;
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
                myClass = loadNonArrayClass(absClassName);
            }
        }
        if (myClass != null) {
            CACHE.put(absClassName, myClass);
        }

        return myClass;
    }

    @Override
    public MyClass loadArrayClass(String absClassName) {
        try {
            return classBuilder.buildArray(
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
        } catch (Exception e) {
            e.printStackTrace();
            MyLog.error("Failed To Create Array In Application Class Loader.");
        }
        return null;
    }

    @Override
    public MyClass loadNonArrayClass(String absClassName) throws Exception {
        String extensionClassPath = System.getProperty("java.home");
        extensionClassPath = extensionClassPath.concat("\\lib\\ext");
        byte[] byteCode = ClassFileReader.readClass(absClassName, extensionClassPath);
        if (byteCode == null) {
            return null;
        }
        ClassFile classFile = new ClassFile(
                new ClassEntry(this, byteCode)
        );
        return classBuilder.build(classFile);
    }

    @Override
    public void verifyByteCode(String[] byteCode) {

    }

    @Override
    public MyClassLoader getParent() {
        return parent;
    }


}
