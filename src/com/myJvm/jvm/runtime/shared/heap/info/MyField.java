package com.myJvm.jvm.runtime.shared.heap.info;

import com.myJvm.jvm.loadcore.analyzer.constant.memberInfo.impl.FieldInfo;
import com.myJvm.jvm.runtime.common.AccessPermission;
import com.myJvm.jvm.runtime.common.FieldDescriptorEnum;

/**
 * @author 22454
 */
public class MyField {
    private int accessFlag;
    private String name;
    private String descriptor;
    private MyClass myClass;
    private int constValueIndex;
    private int slotIndex;

    public static MyField createMyField(MyClass myClass, FieldInfo classFileField) throws Exception {
        MyField field = new MyField();
        field.setAccessFlag(classFileField.getAccessFlag());
        field.setName(classFileField.getName());
        field.setDescriptor(classFileField.getDescriptor());
        field.setMyClass(myClass);
        field.setConstValueIndex(classFileField.getConstantValueAttribute() == null ?
                -1 : classFileField.getConstantValueAttribute().getConstantValueIndex());
        return field;
    }

    public int getAccessFlag() {
        return accessFlag;
    }

    public void setAccessFlag(int accessFlag) {
        this.accessFlag = accessFlag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public MyClass getMyClass() {
        return myClass;
    }

    public void setMyClass(MyClass myClass) {
        this.myClass = myClass;
    }

    public int getConstValueIndex() {
        return constValueIndex;
    }

    public void setConstValueIndex(int constValueIndex) {
        this.constValueIndex = constValueIndex;
    }

    public int getSlotIndex() {
        return slotIndex;
    }

    public void setSlotIndex(int slotIndex) {
        this.slotIndex = slotIndex;
    }

    public boolean isLongOrDouble() {
        return FieldDescriptorEnum.LONG_DESCRIPTOR.equals(this.descriptor) ||
                FieldDescriptorEnum.DOUBLE_DESCRIPTOR.equals(this.descriptor);
    }


    @Override
    public String toString() {
        return "MyField{" +
                "accessFlag=" + accessFlag +
                ", name='" + name + '\'' +
                ", descriptor='" + descriptor + '\'' +
                ", constValueIndex=" + constValueIndex +
                ", slotIndex=" + slotIndex +
                '}';
    }

    public boolean isAccessibleTo(MyClass d) {
        if (this.isPublic()) {
            return true;
        }
        MyClass c = this.myClass;
        if (c.getClassName().equals(d.getClassName())){
            return true;
        }
        if (this.isProtected()) {
            return c.getPackageName().equals(d.getPackageName());
        }
        if (!this.isPrivate()) {
            return c.getPackageName().equals(d.getPackageName());
        }
        return false;
    }

    private boolean isPrivate() {
        return AccessPermission.isPrivate(accessFlag);
    }

    private boolean isProtected() {
        return AccessPermission.isProtected(accessFlag);
    }

    private boolean isPublic() {
        return AccessPermission.isPublic(accessFlag);
    }

    public boolean isStatic() {
        return AccessPermission.isStatic(accessFlag);
    }

    public boolean isFinal() {
        return AccessPermission.isFinal(accessFlag);
    }
}
