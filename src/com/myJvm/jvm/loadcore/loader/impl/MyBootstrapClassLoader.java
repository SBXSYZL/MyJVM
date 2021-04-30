package com.myJvm.jvm.loadcore.loader.impl;

import com.myJvm.exception.EmClassLoadErr;
import com.myJvm.exception.JvmException;
import com.myJvm.jvm.beancenter.annotations.MyAutoWired;
import com.myJvm.jvm.beancenter.annotations.MyBean;
import com.myJvm.jvm.beancenter.annotations.MyTask;
import com.myJvm.jvm.loadcore.analyzer.ClassFile;
import com.myJvm.jvm.loadcore.analyzer.ClassFileReader;
import com.myJvm.jvm.loadcore.loader.MyClassLoader;
import com.myJvm.jvm.runtime.common.AccessPermission;
import com.myJvm.jvm.runtime.shared.heap.builder.MyClassBuilder;
import com.myJvm.jvm.runtime.shared.heap.info.MyClass;
import com.myJvm.log.MyLog;

/**
 * @author 22454
 */
@MyBean
public class MyBootstrapClassLoader implements MyClassLoader {
    @MyAutoWired
    private MyClassBuilder classBuilder;

    @MyTask
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
        MyClass myClass;
        try {
            myClass = CACHE.get(absClassName);
            if (myClass != null) {
                return myClass;
            }
            myClass = loadClass(absClassName);
            if (myClass != null) {
                CACHE.put(absClassName, myClass);
                if (myClass.getReflectClass() == null) {
                    MyClass basicClazz = loadClass("java/lang/Class");
                    myClass.setReflectClass(basicClazz.toObject());
                    myClass.getReflectClass().setExtra(myClass);
                }
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
            myClass = loadArrayClass(absClassName);
        } else {
            myClass = loadNonArrayClass(absClassName);
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
            MyLog.error("Failed To Create Array In Bootstrap Class Loader.");
        }
        return null;
    }

    @Override
    public MyClass loadNonArrayClass(String absClassName) throws Exception {

        String bootstrapClassPath = System.getProperty("java.home");
        bootstrapClassPath = bootstrapClassPath.concat("\\lib");
        byte[] byteCode = ClassFileReader.readClass(absClassName, bootstrapClassPath);
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
        return null;
    }
}
