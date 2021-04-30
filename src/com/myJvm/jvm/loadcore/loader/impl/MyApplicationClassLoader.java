package com.myJvm.jvm.loadcore.loader.impl;


import com.myJvm.exception.EmClassLoadErr;
import com.myJvm.exception.JvmException;
import com.myJvm.jvm.beancenter.annotations.MyAutoWired;
import com.myJvm.jvm.beancenter.annotations.MyBean;
import com.myJvm.jvm.loadcore.analyzer.ClassFile;
import com.myJvm.jvm.loadcore.analyzer.ClassFileReader;
import com.myJvm.jvm.loadcore.loader.MyClassLoader;
import com.myJvm.jvm.runtime.common.AccessPermission;
import com.myJvm.jvm.runtime.shared.heap.builder.MyClassBuilder;
import com.myJvm.jvm.runtime.shared.heap.info.MyClass;
import com.myJvm.log.MyLog;

import java.util.Objects;

/**
 * @author 22454
 */
@MyBean
public class MyApplicationClassLoader implements MyClassLoader {
    private String classPath = null;


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
            CACHE.put(absClassName, myClass);
            if (myClass.getReflectClass() == null) {
                MyClass basicClazz = loadClass("java/lang/Class");
                myClass.setReflectClass(basicClazz.toObject());
                myClass.getReflectClass().setExtra(myClass);
            }
            return myClass;
        }
        MyLog.error("Class: [ ".concat(absClassName).concat(" ] Load Fail."));
        throw new JvmException(EmClassLoadErr.FAIL_TO_LOAD_CLASS, "Class: [ ".concat(absClassName).concat(" ] Load Fail."));
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
        if (classPath == null) {
            classPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
        }
        byte[] byteCode = ClassFileReader.readClass(absClassName, classPath);

        if (byteCode == null) {
            byteCode = ClassFileReader.readClass(absClassName);
            if (byteCode == null) {
                MyLog.error("Failed To Load Class: [ " + absClassName + " ].");
                throw new JvmException(EmClassLoadErr.FAILED_TO_LOAD_CLASS, "Failed To Load Class: [ " + absClassName + " ].");
            }

        }
        ClassFile classFile = new ClassFile(new ClassEntry(this, byteCode));
        return classBuilder.build(classFile);

    }

    @Override
    public void verifyByteCode(String[] byteCode) {

    }

    @Override
    public MyClassLoader getParent() {
        return parent;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }
}
