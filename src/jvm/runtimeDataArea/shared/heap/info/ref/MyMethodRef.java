package jvm.runtimeDataArea.shared.heap.info.ref;

import jvm.classLoadSystem.analyzer.constant.constantInfo.constantInfoImpl.ConstantInfoMethodRef;
import jvm.runtimeDataArea.shared.heap.RuntimeConstantPool;
import jvm.runtimeDataArea.shared.heap.info.MyClass;
import jvm.runtimeDataArea.shared.heap.info.MyMethod;

import java.util.Map;

/**
 * @author 22454
 */
public class MyMethodRef {
    private RuntimeConstantPool runtimeConstantPool;
    private String className;
    private MyClass clazz;
    private String methodName;
    private String descriptor;
    private MyMethod method;

    public static MyMethodRef createMyMethodRef(RuntimeConstantPool runtimeConstantPool, ConstantInfoMethodRef constantInfoMethodRef) {
        try {
            MyMethodRef myMethodRef = new MyMethodRef();
            myMethodRef.setRuntimeConstantPool(runtimeConstantPool);
            myMethodRef.setClassName(constantInfoMethodRef.getClassName());
//            MyClass d = runtimeConstantPool.getClazz();
//            MyClass c = d.getClassLoader().loadClass(myMethodRef.getClassName());
//            myMethodRef.setClazz(c);
            Map<String, String> nameAndDescriptor = constantInfoMethodRef.getNameAndDescriptor();
            myMethodRef.setMethodName(nameAndDescriptor.get("name"));
            myMethodRef.setDescriptor(nameAndDescriptor.get("_type"));
            return myMethodRef;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed To Create Method Ref");
        }
    }

    public MyMethod resolveMethod() {
        if (null == method) {
            this.resolveMethodRef();
        }
        return method;
    }

    public MyClass resolvedClass() {
        if (null != this.clazz) {
            return this.clazz;
        }
        MyClass d = this.runtimeConstantPool.getClazz();
        try {
            this.clazz = d.getClassLoader().findClass(this.className);
            return this.clazz;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Can Not Resolved Class: [ " + this.className + " ]");
        }
    }

    private void resolveMethodRef() {
        MyClass d = runtimeConstantPool.getClazz();
        clazz = this.resolvedClass();
        if (this.clazz.isInterface()) {
            throw new IncompatibleClassChangeError();
        }
        MyMethod method = lookupMethod(this.clazz, this.methodName, this.descriptor);
        if (method == null) {
            throw new NoSuchMethodError();
        }
        if (!method.isAccessibleFor(d)) {
            throw new IllegalAccessError();
        }
        this.method = method;
    }

    public static MyMethod lookupMethod(MyClass clazz, String name, String descriptor) {
        MyMethod method = null;
        for (MyClass c = clazz; c != null; c = c.getSuperClass()) {
            for (MyMethod method1 : c.getMethods()) {
                if (method1.getName().equals(name) && method1.getDescriptor().equals(descriptor)) {
                    method = method1;
                    break;
                }
            }
        }
        if (null == method) {
            for (MyClass interfase : clazz.getInterfaces()) {
                for (MyMethod method1 : interfase.getMethods()) {
                    if (method1.getName().equals(name) && method1.getDescriptor().equals(descriptor)) {
                        method = method1;
                        break;
                    }
                }
            }
        }
        return method;

    }

    public RuntimeConstantPool getRuntimeConstantPool() {
        return runtimeConstantPool;
    }

    public void setRuntimeConstantPool(RuntimeConstantPool runtimeConstantPool) {
        this.runtimeConstantPool = runtimeConstantPool;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public MyClass getClazz() {
        return clazz;
    }

    public void setClazz(MyClass clazz) {
        this.clazz = clazz;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public MyMethod getMethod() {
        return method;
    }

    public void setMethod(MyMethod method) {
        this.method = method;
    }
}
