package jvm.runtimeDataArea.shared.heap.info.ref;

import jvm.classLoadSystem.analyzer.constant.constantInfo.constantInfoImpl.ConstantInfoInterfaceMethodRef;
import jvm.runtimeDataArea.shared.heap.RuntimeConstantPool;
import jvm.runtimeDataArea.shared.heap.info.MyClass;
import jvm.runtimeDataArea.shared.heap.info.MyMethod;
import log.MyLog;

import java.util.Map;

/**
 * @author 22454
 */
public class MyInterfaceMethodRef {
    private RuntimeConstantPool runtimeConstantPool;
    private String className;
    private MyClass clazz;
    private String methodName;
    private String description;
    private MyMethod method;

    public static MyInterfaceMethodRef createInterfaceMethodRef(RuntimeConstantPool runtimeConstantPool, ConstantInfoInterfaceMethodRef constantInfoInterfaceMethodRef) {
        try {
            MyInterfaceMethodRef ref = new MyInterfaceMethodRef();
            ref.setRuntimeConstantPool(runtimeConstantPool);
            ref.setClassName(constantInfoInterfaceMethodRef.getClassName());
//            ref.setClazz(runtimeConstantPool.getClazz().getClassLoader().loadClass(ref.getClassName()));
            Map<String, String> nameAndType = constantInfoInterfaceMethodRef.getNameAndType();
            ref.setMethodName(nameAndType.get("name"));
            ref.setDescription(nameAndType.get("_type"));
            return ref;
        } catch (Exception e) {
            e.printStackTrace();
            MyLog.error("Failed To Create Interface Method Ref");
            return null;
        }
    }

    public MyMethod resolveInterfaceMethod() {
        if (this.method == null) {
            this.resolveInterfaceMethodRef();
        }
        return this.method;
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

    private void resolveInterfaceMethodRef() {
        MyClass d = this.runtimeConstantPool.getClazz();
        MyClass c = this.resolvedClass();
        if (!c.isInterface()) {
            throw new IncompatibleClassChangeError();
        }
        MyMethod method = lookupInterfaceMethod(c, this.methodName, this.description);
        if (null == method) {
            throw new NoSuchMethodError();
        }
        if (!method.isAccessibleFor(d)) {
            throw new IllegalAccessError();
        }
        this.method = method;
    }

    private MyMethod lookupInterfaceMethod(MyClass interfaceClazz, String methodName, String description) {
        for (MyMethod method : interfaceClazz.getMethods()) {
            if (method.getName().equals(methodName) && method.getDescriptor().equals(description)) {
                return method;
            }
        }
        MyClass[] interfaces = interfaceClazz.getInterfaces();
        for (MyClass anInterface : interfaces) {
            for (MyMethod method : anInterface.getMethods()) {
                if (method.getName().equals(methodName) && method.getDescriptor().equals(description)) {
                    return method;
                }
            }
        }
        return null;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MyMethod getMethod() {
        return method;
    }

    public void setMethod(MyMethod method) {
        this.method = method;
    }
}
