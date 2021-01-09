package jvm.runtimeDataArea.shared.heap.info.ref;

import jvm.classLoadSystem.analyzer.constant.constantInfo.constantInfoImpl.ConstantInfoFieldRef;
import jvm.runtimeDataArea.shared.heap.RuntimeConstantPool;
import jvm.runtimeDataArea.shared.heap.info.MyClass;
import jvm.runtimeDataArea.shared.heap.info.MyField;

import java.util.Map;

/**
 * @author 22454
 */
public class MyFieldRef {
    private RuntimeConstantPool runtimeConstantPool;
    private String className;
    private MyClass clazz;
    private String fieldName;
    private String description;
    private MyField field;

    public static MyFieldRef createMyFieldRef(RuntimeConstantPool runtimeConstantPool, ConstantInfoFieldRef constantInfoFieldRef) {
        try {
            MyFieldRef fieldRef = new MyFieldRef();
            fieldRef.setRuntimeConstantPool(runtimeConstantPool);
            fieldRef.setClassName(constantInfoFieldRef.getClassName());
//            MyClass d = runtimeConstantPool.getClazz();
//            MyClass c = d.getClassLoader().findClass(fieldRef.getClassName());
//            fieldRef.setClazz(c);
            Map<String, String> nameAndType = constantInfoFieldRef.getNameAndType();
            fieldRef.setFieldName(nameAndType.get("name"));
            fieldRef.setDescription(nameAndType.get("_type"));
            return fieldRef;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed To Create Field Ref");
        }
    }

    public MyField resolveField() {
        if (null == field) {
            resolveFieldRef();
        }
        return field;
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

    private void resolveFieldRef() {
        MyClass d = runtimeConstantPool.getClazz();
        clazz = this.resolvedClass();
        MyField field = lookupField(clazz, this.fieldName, this.description);
        if (null == field) {
            throw new NoSuchFieldError();
        }
        if (!field.isAccessibleTo(d)) {
            throw new IllegalAccessError();
        }
        this.field = field;
    }

    private MyField lookupField(MyClass clazz, String fieldName, String description) {
        for (MyField field : clazz.getFields()) {
            if (field.getName().equals(fieldName) && field.getDescriptor().equals(description)) {
                return field;
            }
        }
        if (null != clazz.getInterfaces()) {
            for (MyClass anInterface : clazz.getInterfaces()) {
                MyField field = lookupField(anInterface, fieldName, description);
                if (null != field) {
                    return field;
                }
            }
        }
        if (null != clazz.getSuperClass()) {
            return lookupField(clazz.getSuperClass(), fieldName, description);
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

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MyField getField() {
        return field;
    }

    public void setField(MyField field) {
        this.field = field;
    }
}
