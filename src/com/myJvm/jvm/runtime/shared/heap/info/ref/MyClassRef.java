package com.myJvm.jvm.runtime.shared.heap.info.ref;

import com.myJvm.jvm.loadcore.analyzer.constant.constantInfo.impl.ConstantInfoClass;
import com.myJvm.jvm.runtime.shared.heap.RuntimeConstantPool;
import com.myJvm.jvm.runtime.shared.heap.info.MyClass;

/**
 * @author 22454
 */
public class MyClassRef {
    private RuntimeConstantPool runtimeConstantPool;
    private String className;
    private MyClass clazz;


    public static MyClassRef createClassRef(RuntimeConstantPool runtimeConstantPool, ConstantInfoClass constantInfoClass) throws Exception {
        String className = constantInfoClass.getClassName();
        MyClassRef classRef = new MyClassRef();
        classRef.setRuntimeConstantPool(runtimeConstantPool);
        classRef.setClassName(className);
        return classRef;
    }

    public MyClass resolvedClass() throws Exception {
        if (clazz == null) {
            MyClass myClass = runtimeConstantPool.getClazz();
            this.clazz = myClass.getClassLoader().findClass(this.className);
            return this.clazz;
        }
        return this.clazz;
    }

    public void setRuntimeConstantPool(RuntimeConstantPool runtimeConstantPool) {
        this.runtimeConstantPool = runtimeConstantPool;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setClazz(MyClass clazz) {
        this.clazz = clazz;
    }
}
