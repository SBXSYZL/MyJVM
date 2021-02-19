package com.myJvm.jvm.runtime.shared.heap.info;

import com.myJvm.jvm.beancenter.annotations.MyBean;
import com.myJvm.jvm.loadcore.analyzer.ClassFile;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.AttributeInfoSourceFile;
import com.myJvm.jvm.loadcore.loader.MyClassLoader;
import com.myJvm.jvm.runtime.common.AccessPermission;
import com.myJvm.jvm.runtime.shared.heap.RuntimeConstantPool;

import java.util.Arrays;

/**
 * @author 22454
 */
@MyBean
public class MyClass {
    public static final String ANCESTOR_OBJECT_NAME = "java/lang/Object";
    private int accessFlag;
    private String className;
    private String superName;
    private String[] interfaceNames;
    private RuntimeConstantPool runtimeConstantPool;
    private MyField[] fields;
    private MyMethod[] methods;
    private String sourceFile;
    private MyClass superClass;
    private MyClass[] interfaces;
    private int instanceCount;
    private int staticSlotCount;
    private MyArray staticVariables;
    private boolean initStarted;
    private MyObject reflectClass;
    private MyClassLoader classLoader;


    public void setAccessFlag(int accessFlag) {
        this.accessFlag = accessFlag;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setSuperName(String superName) {
        this.superName = superName;
    }

    public void setInterfaceNames(String[] interfaceNames) {
        this.interfaceNames = interfaceNames;
    }

    public void setRuntimeConstantPool(RuntimeConstantPool runtimeConstantPool) {
        this.runtimeConstantPool = runtimeConstantPool;
    }

    public void setFields(MyField[] fields) {
        this.fields = fields;
    }

    public void setMethods(MyMethod[] methods) {
        this.methods = methods;
    }

    public void setSourceFile(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    public void setSuperClass(MyClass superClass) {
        this.superClass = superClass;
    }

    public void setInterfaces(MyClass[] interfaces) {
        this.interfaces = interfaces;
    }

    public void setInstanceCount(int instanceCount) {
        this.instanceCount = instanceCount;
    }

    public void setStaticSlotCount(int staticSlotCount) {
        this.staticSlotCount = staticSlotCount;
    }


    public void setInitStarted(boolean initStarted) {
        this.initStarted = initStarted;
    }

    public void setReflectClass(MyObject reflectClass) {
        this.reflectClass = reflectClass;
    }

    public void setClassLoader(MyClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    private String getSourceFile(ClassFile classFile) throws Exception {
        AttributeInfoSourceFile attributeInfoSourceFile = classFile.getSourceFile();
        return attributeInfoSourceFile.getSourceFileName();
    }

    public MyClassLoader getClassLoader() {
        return classLoader;
    }

    public RuntimeConstantPool getRuntimeConstantPool() {
        return runtimeConstantPool;
    }

    public int getAccessFlag() {
        return accessFlag;
    }

    public String getClassName() {
        return className;
    }

    public String getSimpleClassName() {
        return getClassName().substring(getClassName().lastIndexOf("/") + 1);
    }

    public String getSuperName() {
        return superName;
    }

    public String[] getInterfaceNames() {
        return interfaceNames;
    }

    public MyField[] getFields() {
        return fields;
    }

    public MyMethod[] getMethods() {
        return methods;
    }

    public String getSourceFile() {
        return sourceFile;
    }

    public MyClass getSuperClass() {
        return superClass;
    }

    public MyClass[] getInterfaces() {
        return interfaces;
    }

    public int getInstanceCount() {
        return instanceCount;
    }

    public int getStaticSlotCount() {
        return staticSlotCount;
    }

    public MyArray getStaticVariables() {
        return staticVariables;
    }

    public void setStaticVariables(MyArray staticVariables) {
        this.staticVariables = staticVariables;
    }

    public boolean isInitStarted() {
        return initStarted;
    }

    public MyObject getReflectClass() {
        return reflectClass;
    }

    @Override
    public String toString() {
        return "MyClass{\n" +
                "\taccessFlag=" + accessFlag + '\n' +
                "\tclassName='" + className + '\'' + '\n' +
                "\tsuperName='" + superName + '\'' + '\n' +
                "\tinterfaceNames=" + Arrays.toString(interfaceNames) + '\n' +
                "\truntimeConstantPool=" + runtimeConstantPool + '\n' +
                "\tfields=" + Arrays.toString(fields) + '\n' +
                "\tmethods=" + Arrays.toString(methods) + '\n' +
                "\tsourceFile='" + sourceFile + '\'' + '\n' +
                "\tsuperClass=" + superClass + '\n' +
                "\tinterfaces=" + Arrays.toString(interfaces) + '\n' +
                "\tinstanceCount=" + instanceCount + '\n' +
                "\tstaticSlotCount=" + staticSlotCount + '\n' +
                "\tstaticVariables=" + staticVariables + '\n' +
                "\tinitStarted=" + initStarted + '\n' +
                "\tjClass=" + reflectClass + '\n' +
                "\tclassLoader=" + classLoader + '\n' +
                '}' + '\n';
    }

    public MyObject toObject() {
        return new MyObject(this);
    }

    public MyField getField(String fieldName, String descriptor, boolean isStatic) {
        for (MyClass c = this; c != null; c = c.superClass) {
            for (MyField field : c.getFields()) {
                if (field.getName().equals(fieldName) &&
                        field.getDescriptor().equals(descriptor) &&
                        AccessPermission.isStatic(field.getAccessFlag()) == isStatic) {
                    return field;
                }
            }
        }
        return null;
    }

    public boolean isArray() {
        return this.className.getBytes()[0] == '[';
    }

    public MyObject createArray(int length) {
        if (!isArray()) {
            throw new RuntimeException("Not Array Class : " + className);
        }
        switch (className) {
            case "[Z":
            case "[B":
                return new MyObject(this, new byte[length]);
            case "[C":
                return new MyObject(this, new char[length]);
            case "[S":
                return new MyObject(this, new short[length]);
            case "[I":
                return new MyObject(this, new int[length]);
            case "[J":
                return new MyObject(this, new long[length]);
            case "[F":
                return new MyObject(this, new float[length]);
            case "[D":
                return new MyObject(this, new double[length]);
            default:
                return new MyObject(this, new MyObject[length]);
        }
    }

    public String getJavaName() {
        return this.getClassName().substring(0, 1) + this.getClassName().substring(1).replace("/", ".");
    }

    public boolean isInterface() {
        return AccessPermission.isInterface(accessFlag);
    }

    public boolean isPrimitiveClass() {
        return MyClassLoader.PRIMITIVE_TYPE.get(this.getClassName()) != null;
    }

    public boolean isImplements(MyClass clazz) {
        for (MyClass c = this; c != null; c = c.superClass) {
            for (MyClass cls : c.interfaces) {
                if (cls == clazz || cls.isSubInterfaceOf(clazz)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isSubInterfaceOf(MyClass clazz) {
        for (MyClass superInterface : this.interfaces) {
            if (superInterface == clazz || superInterface.isSubInterfaceOf(clazz)) {
                return true;
            }
        }
        return false;
    }

    public MyClass componentClass() throws Exception {
        String componentClassName = MyClassLoader.getComponentClassName(this.getClassName());
        return this.getClassLoader().findClass(componentClassName);
    }

    public String getPackageName() {
        int index = this.className.lastIndexOf("/");
        if (index >= 0) {
            return this.className.substring(0, index);
        }
        return "";
    }

    public MyMethod getClassInitMethod() {
        return this.getStaticMethod("<clinit>", "()V", true);
    }

    public MyMethod getMainMethod() {
        return this.getStaticMethod("main", "([Ljava/lang/String;)V", true);
    }

    public MyMethod getInstanceMethod(String methodName, String description) {
        return this.getStaticMethod(methodName, description, false);
    }

    private MyMethod getStaticMethod(String methodName, String descriptor, boolean isStatic) {
        for (MyClass c = this; c != null; c = c.getSuperClass()) {
            if (null == c.getMethods()) {
                continue;
            }
            for (MyMethod method : c.getMethods()) {
                if (method.isStatic() == isStatic && method.getName().equals(methodName) && method.getDescriptor().equals(descriptor)) {
                    return method;
                }
            }
        }
        throw new RuntimeException("Method Not Found：" + methodName + "  " + descriptor);
    }

    public boolean isSuper() {
        return AccessPermission.isSuper(accessFlag);
    }

    public boolean isSubClassOf(MyClass other) {
        for (MyClass c = this.superClass; c != null; c = c.superClass) {
            if (c == other) {
                return true;
            }
        }
        return false;
    }

    public boolean isAbstract() {
        return AccessPermission.isAbstract(accessFlag);
    }

    public MyObject newObject() {
        return new MyObject(this);
    }

    public MyClass getArrayClass() throws Exception {
        String arrayClassName = MyClassLoader.getArrayClassName(className);
        return classLoader.loadClass(arrayClassName);
    }

    public boolean isAssignableFrom(MyClass clazz) {
        if (this == clazz) {
            return true;
        }
        if (!clazz.isInterface()) {
            return this.isSubClassOf(clazz);
        } else {
            return this.isImplements(clazz);
        }
    }
}
