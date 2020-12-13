package jvm.runtimeDataArea.shared.heap.info;

import jvm.BeanCenter.MyBean;
import jvm.classLoadSystem.analyzer.ClassFile;
import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.AttributeInfoSourceFile;
import jvm.classLoadSystem.classLoaderImpl.MyClassLoader;
import jvm.runtimeDataArea.common.AccessPermission;
import jvm.runtimeDataArea.shared.heap.RuntimeConstantPool;

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

    private boolean isSubInterfaceOf(MyClass clazz) {
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
}
