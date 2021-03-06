package com.myJvm.jvm.runtime.shared.heap.builder;

import com.myJvm.jvm.beancenter.annotations.MyAutoWired;
import com.myJvm.jvm.beancenter.annotations.MyBean;
import com.myJvm.jvm.loadcore.analyzer.ClassFile;
import com.myJvm.jvm.loadcore.analyzer.constant.memberInfo.impl.FieldInfo;
import com.myJvm.jvm.loadcore.analyzer.constant.memberInfo.impl.MethodInfo;
import com.myJvm.jvm.loadcore.loader.MyClassLoader;
import com.myJvm.jvm.runtime.common.AccessPermission;
import com.myJvm.jvm.runtime.common.FieldDescriptorEnum;
import com.myJvm.jvm.runtime.shared.heap.RuntimeConstantPool;
import com.myJvm.jvm.runtime.shared.heap.info.*;
import com.myJvm.jvm.runtime.shared.heap.info.dependence.StringCache;
import com.myJvm.log.MyLog;

import java.util.LinkedList;

/**
 * @author 22454
 */
@MyBean
public class MyClassBuilder {
    @MyAutoWired
    private MyMethodBuilder methodBuilder;


    public MyClass build(ClassFile classFile) {
        MyClass clazz;
        try {
            clazz = new MyClass();
            clazz.setClassLoader(classFile.getClassLoader());
            clazz.setAccessFlag(classFile.getAccessFlag());
            clazz.setClassName(classFile.getThisClassName());
            clazz.setSuperName(classFile.getSuperClassName());
            clazz.setInterfaceNames(classFile.getInterfaceNames());
            RuntimeConstantPool runtimeConstantPool = new RuntimeConstantPool(clazz, classFile.getConstantPool());
            clazz.setRuntimeConstantPool(runtimeConstantPool);
            clazz.setFields(getFields(clazz, classFile));
            clazz.setMethods(getMethods(clazz, classFile));
            clazz.setSourceFile(classFile.getSourceFile().getSourceFileName());
            if (!MyClass.ANCESTOR_OBJECT_NAME.equals(clazz.getClassName())) {
                clazz.setSuperClass(clazz.getClassLoader().loadClass(classFile.getSuperClassName()));
            }
            clazz.setInterfaces(getInterfaces(clazz));

            LinkedList<MyField> staticFields = countTheNumberOfNormalFieldAndStaticField(clazz);
            initializeTheStaticField(clazz, runtimeConstantPool, staticFields);
            return clazz;
        } catch (Exception e) {
            e.printStackTrace();
            MyLog.error("Failed To Build MyClass.");
        }
        return null;
    }

    public MyClass buildArray(int accessFlag, String className, MyClassLoader classLoader, boolean initStarted, MyClass superClass, MyClass[] interfaces) {
        MyClass clazz = new MyClass();
        clazz.setAccessFlag(accessFlag);
        clazz.setClassName(className);
        clazz.setClassLoader(classLoader);
        clazz.setInitStarted(initStarted);
        clazz.setSuperClass(superClass);
        clazz.setInterfaces(interfaces);
        return clazz;
    }

    private MyField[] getFields(MyClass clazz, ClassFile classFile) throws Exception {
        int fieldsCount = classFile.getFieldsCount();
        MyField[] fields = new MyField[fieldsCount];
        FieldInfo[] classFileFields = classFile.getFields();
        for (int i = 0; i < fieldsCount; i++) {
            fields[i] = MyField.createMyField(clazz, classFileFields[i]);
        }
        return fields;
    }

    private MyMethod[] getMethods(MyClass clazz, ClassFile classFile) {
        MethodInfo[] methods = classFile.getMethods();
        MyMethod[] myMethods = new MyMethod[methods.length];
        for (int i = 0; i < methods.length; i++) {
            myMethods[i] = methodBuilder.build(clazz, methods[i]);
        }
        return myMethods;
    }

    private MyClass[] getInterfaces(MyClass clazz) throws Exception {
        int interfaceCount = clazz.getInterfaceNames().length;
        if (interfaceCount > 0) {
            String[] interfaceNames = clazz.getInterfaceNames();
            MyClass[] interfaces = new MyClass[interfaceCount];
            for (int i = 0; i < interfaceCount; i++) {
                interfaces[i] = clazz.getClassLoader().findClass(interfaceNames[i]);
            }
            return interfaces;
        }
        return null;
    }

    private LinkedList<MyField> countTheNumberOfNormalFieldAndStaticField(MyClass clazz) {
        LinkedList<MyField> staticField = new LinkedList<>();
        int instanceSlotIndex = 0;
        int staticFieldSlotIndex = 0;
        if (clazz.getSuperClass() != null) {
            instanceSlotIndex = clazz.getSuperClass().getInstanceCount();
        }
        for (MyField field : clazz.getFields()) {
            if (!AccessPermission.isStatic(field.getAccessFlag())) {
                field.setSlotIndex(instanceSlotIndex++);
            } else {
                staticFieldSlotIndex++;
                if (AccessPermission.isFinal(field.getAccessFlag())) {
                    staticField.add(field);
                }
            }
            if (field.isLongOrDouble()) {
                ++instanceSlotIndex;
            }
        }
        clazz.setInstanceCount(instanceSlotIndex);
        clazz.setStaticSlotCount(staticFieldSlotIndex);
        return staticField;
    }


    private void initializeTheStaticField(MyClass clazz, RuntimeConstantPool runtimeConstantPool, LinkedList<MyField> staticFields) throws Exception {
        MyArray staticVariables = new MyArray(clazz.getStaticSlotCount());
        for (MyField field : staticFields) {
            if (AccessPermission.isFinal(field.getAccessFlag())) {
                int index = field.getConstValueIndex();
                int slotId = field.getSlotIndex();
                if (index > 0) {
                    String descriptor = field.getDescriptor();
                    switch (descriptor) {
                        case FieldDescriptorEnum.BOOLEAN_DESCRIPTOR:
                        case FieldDescriptorEnum.BYTE_DESCRIPTOR:
                        case FieldDescriptorEnum.CHAR_DESCRIPTOR:
                        case FieldDescriptorEnum.SHORT_DESCRIPTOR:
                        case FieldDescriptorEnum.INTEGER_DESCRIPTOR:
                            staticVariables.setInteger(slotId, (Integer) runtimeConstantPool.getConstants(index));
                            break;
                        case FieldDescriptorEnum.LONG_DESCRIPTOR:
                            Object constants = runtimeConstantPool.getConstants(index);
                            staticVariables.setLong(slotId, (Long) constants);
                            break;
                        case FieldDescriptorEnum.FLOAT_DESCRIPTOR:
                            staticVariables.setFloat(slotId, (Float) runtimeConstantPool.getConstants(index));
                        case FieldDescriptorEnum.DOUBLE_DESCRIPTOR:
                            staticVariables.setDouble(slotId, (Double) runtimeConstantPool.getConstants(index));
                            break;
                        case FieldDescriptorEnum.STRING_DESCRIPTOR:
                            String str = (String) runtimeConstantPool.getConstants(index);
                            MyObject string = StringCache.putString(clazz.getClassLoader(), str);
                            staticVariables.setObjectRef(slotId, string);
                            break;
                        default:
                            MyLog.error("Failed To Create Static Field.");
                            break;
                    }
                }
            }
        }

        clazz.setStaticVariables(staticVariables);
    }

    public MyClass build(int accessFlag, String className, MyClassLoader classLoader, boolean initStarted) {
        MyClass clazz = new MyClass();
        clazz.setAccessFlag(accessFlag);
        clazz.setClassName(className);
        clazz.setClassLoader(classLoader);
        clazz.setInitStarted(initStarted);
        return clazz;
    }
}
