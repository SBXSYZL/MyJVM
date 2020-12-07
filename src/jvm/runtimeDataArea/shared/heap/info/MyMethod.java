package jvm.runtimeDataArea.shared.heap.info;

import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.AttributeInfoCode;
import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.AttributeInfoLineNumberTable;
import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.ExceptionInfo;
import jvm.classLoadSystem.analyzer.constant.memberInfo.memberInfoImpl.MethodInfo;
import jvm.runtimeDataArea.common.AccessPermission;
import jvm.runtimeDataArea.common.FieldDescriptorEnum;
import jvm.runtimeDataArea.shared.heap.RuntimeConstantPool;
import jvm.runtimeDataArea.shared.heap.info.dependence.ExceptionTable;
import jvm.runtimeDataArea.shared.heap.info.dependence.MethodDescriptor;
import jvm.runtimeDataArea.shared.heap.info.dependence.MethodDescriptorParser;

import java.util.Arrays;
import java.util.List;

/**
 * @author 22454
 */
public class MyMethod {
    private int accessFlag;
    private String name;
    private String descriptor;
    private MyClass clazz;
    private int maxStack;
    private int maxLocals;
    private byte[] code;
    private ExceptionTable exceptionTable;
    private AttributeInfoLineNumberTable lineNumberTable;


    private void injectCodeAttribute(String returnType) {
        switch (returnType.getBytes()[0]) {
            case 'V':
                this.code = new byte[]{(byte) 0xfe, (byte) 0xb1};
                break;
            case 'L':
            case '[':
                this.code = new byte[]{(byte) 0xfe, (byte) 0xb0};
                break;
            case 'D':
                this.code = new byte[]{(byte) 0xfe, (byte) 0xaf};
                break;
            case 'F':
                this.code = new byte[]{(byte) 0xfe, (byte) 0xae};
                break;
            case 'J':
                this.code = new byte[]{(byte) 0xfe, (byte) 0xad};
                break;
            default:
                this.code = new byte[]{(byte) 0xfe, (byte) 0xac};
                break;
        }
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

    public MyClass getClazz() {
        return clazz;
    }

    public void setClazz(MyClass clazz) {
        this.clazz = clazz;
    }

    public int getMaxStack() {
        return maxStack;
    }

    public void setMaxStack(int maxStack) {
        this.maxStack = maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public void setMaxLocals(int maxLocals) {
        this.maxLocals = maxLocals;
    }

    public byte[] getCode() {
        return code;
    }

    public void setCode(byte[] code) {
        this.code = code;
    }

    public ExceptionTable getExceptionTable() {
        return exceptionTable;
    }

    public void setExceptionTable(ExceptionTable exceptionTable) {
        this.exceptionTable = exceptionTable;
    }

    public AttributeInfoLineNumberTable getLineNumberTable() {
        return lineNumberTable;
    }

    public void setLineNumberTable(AttributeInfoLineNumberTable lineNumberTable) {
        this.lineNumberTable = lineNumberTable;
    }

    @Override
    public String toString() {
        return "MyMethod{\n" +
                "accessFlag=" + accessFlag +
                ", name='" + name + '\'' +
                ", descriptor='" + descriptor + '\'' +
                ", maxStack=" + maxStack +
                ", maxLocals=" + maxLocals +
                ", code=" + Arrays.toString(code) +
                ", exceptionTable=" + exceptionTable +
                ", lineNumberTable=" + lineNumberTable +
                '}';
    }

    public int getLineNumber(int pc) {
        if (this.isNative()) {
            return -2;
        }
        if (this.lineNumberTable == null) {
            return -1;
        }
        return this.lineNumberTable.getLineNumber(pc);
    }

    private boolean isNative() {
        return AccessPermission.isNative(accessFlag);
    }
}
