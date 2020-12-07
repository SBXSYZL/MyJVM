package jvm.classLoadSystem.classLoaderImpl.myClassLoaderImpl;

import jvm.BeanCenter.AutoWired;
import jvm.BeanCenter.Bean;
import jvm.BeanCenter.Task;
import jvm.classLoadSystem.analyzer.ClassFile;
import jvm.classLoadSystem.analyzer.ClassFileReader;
import jvm.classLoadSystem.classLoaderImpl.MyClassLoader;
import exception.EmClassLoadErr;
import exception.JvmException;
import jvm.runtimeDataArea.common.AccessPermission;
import jvm.runtimeDataArea.shared.heap.builder.MyClassBuilder;
import jvm.runtimeDataArea.shared.heap.info.MyClass;
import log.MyLog;

/**
 * @author 22454
 */
@Bean
public class MyBootstrapClassLoader implements MyClassLoader {
    @AutoWired
    private MyClassBuilder classBuilder;

    @Task
    public void loadPrimitiveType() {
        try {
            MyClass basicClazz = loadClass("java/lang/Class");
            PRIMITIVE_TYPE.forEach((className, codeName) -> {
                MyClass clazz = classBuilder.build(AccessPermission.ACC_PUBLIC,
                        className,
                        this,
                        true);
                CACHE.put(className, clazz);
            });
            CACHE.forEach((className, clazz) -> {
                if (clazz.getReflectClass() == null) {
                    clazz.setReflectClass(basicClazz.toObject());
                    clazz.getReflectClass().setExtra(clazz);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            MyLog.error("Failed To Init Bootstrap Class Loader.");
        }
    }

    @Override
    public MyClass findClass(String absClassName) throws Exception {
        absClassName = MyClassLoader.checkClassName(absClassName);
        MyClass myClass = null;
        try {
            myClass = CACHE.get(absClassName);
            if (myClass != null) {
                return myClass;
            }
            myClass = loadClass(absClassName);
            if (myClass != null) {
                MyClassLoader.CACHE.put(absClassName, myClass);
                return myClass;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new JvmException(EmClassLoadErr.FAIL_TO_LOAD_CLASS, "Failed To Load Class: [ " + absClassName + " ].");
        }
        return null;
    }

    @Override
    public MyClass loadClass(String absClassName) throws Exception {
        MyClass myClass = CACHE.get(absClassName);
        if (myClass != null) {
            return myClass;
        }
        if (absClassName.getBytes()[0] == '[') {
            return loadArrayClass(absClassName);
        } else {
            return loadNonArrayClass(absClassName);
        }

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
        String property = System.getProperty("java.home");
        ClassFile classFile = new ClassFile(
                new ClassEntry(this, ClassFileReader.readClass(absClassName, property))
        );
        return classBuilder.build(classFile);

    }

    @Override
    public void verifyByteCode(String[] byteCode) {

    }

    @Override
    public MyClassLoader getParent() {
        return null;
    }
}
