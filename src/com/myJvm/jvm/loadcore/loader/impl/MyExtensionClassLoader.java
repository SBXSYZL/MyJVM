package com.myJvm.jvm.loadcore.loader.impl;

import com.myJvm.jvm.beancenter.annotations.MyAutoWired;
import com.myJvm.jvm.beancenter.annotations.MyBean;
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
        myClass = this.loadClass(absClassName);
        if (myClass != null) {
            if (myClass.getReflectClass() == null) {
                MyClass basicClazz = loadClass("java/lang/Class");
                myClass.setReflectClass(basicClazz.toObject());
                myClass.getReflectClass().setExtra(myClass);
            }
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
