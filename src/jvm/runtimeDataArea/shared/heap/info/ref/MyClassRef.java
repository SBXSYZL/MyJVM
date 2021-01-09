package jvm.runtimeDataArea.shared.heap.info.ref;

import jvm.classLoadSystem.analyzer.constant.constantInfo.constantInfoImpl.ConstantInfoClass;
import jvm.runtimeDataArea.shared.heap.RuntimeConstantPool;
import jvm.runtimeDataArea.shared.heap.info.MyClass;

/**
 * @author 22454
 */
public class MyClassRef {
    private RuntimeConstantPool runtimeConstantPool;
    private String className;
    private MyClass clazz;

    public static MyClassRef createClassRef(RuntimeConstantPool runtimeConstantPool, ConstantInfoClass constantInfoClass) throws Exception {
        MyClassRef classRef = new MyClassRef();
        classRef.setRuntimeConstantPool(runtimeConstantPool);
        classRef.setClassName(constantInfoClass.getClassName());
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
