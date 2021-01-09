package jvm.runtimeDataArea.shared.heap.info;

import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.AttributeInfoLineNumberTable;
import jvm.classLoadSystem.analyzer.constant.memberInfo.memberInfoImpl.MethodInfo;
import jvm.runtimeDataArea.shared.heap.info.dependence.ExceptionHandler;
import jvm.runtimeDataArea.shared.heap.info.dependence.ExceptionTable;
import jvm.runtimeDataArea.shared.heap.info.dependence.MethodDescriptor;
import jvm.runtimeDataArea.shared.heap.info.dependence.MethodDescriptorParser;
import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.AttributeInfoCode;
import jvm.runtimeDataArea.common.AccessPermission;
import log.MyLog;

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
    private int argsCount;

    public static MyMethod createMethod(MyClass clazz, MethodInfo methodInfo) {
        try {
            MyMethod method = new MyMethod();
            method.setAccessFlag(methodInfo.getAccessFlag());
            method.setName(methodInfo.getName());
            method.setDescriptor(methodInfo.getDescriptor());
            method.setClazz(clazz);
            AttributeInfoCode codeAttribute = methodInfo.getCodeAttribute();
            if (codeAttribute != null) {
                method.setMaxStack(codeAttribute.getMaxStack());
                method.setMaxLocals(codeAttribute.getMaxLocals());
                method.setCode(codeAttribute.getCode());
                method.setExceptionTable(new ExceptionTable(codeAttribute.getExceptionTable(), clazz.getRuntimeConstantPool()));
                method.setLineNumberTable(codeAttribute.getLineNumberTableAttribute());
            }
            MethodDescriptor methodDescriptor = MethodDescriptorParser.parseMethodDescriptorParser(method.getDescriptor());
            int cnt = 0;
            for (String parameterType : methodDescriptor.getParameterTypes()) {
                cnt++;
                if ("J".equals(parameterType) || "D".equals(parameterType)) {
                    cnt++;
                }
            }
            if (!method.isStatic()) {
                cnt++;
            }
            method.argsCount = cnt;
            if (method.isNative()) {
                method.injectCodeAttribute(methodDescriptor.getReturnType());
            }
            return method;

        } catch (Exception e) {
            MyLog.error("Failed To Create Method");
            e.printStackTrace();
            return null;
        }
    }


    private void injectCodeAttribute(String returnType) {
        this.maxStack = 4;
        this.maxLocals = this.argsCount;
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
        StringBuilder str = new StringBuilder("MyMethod{\n" +
                "accessFlag=" + accessFlag +
                ", name='" + name + '\'' +
                ", descriptor='" + descriptor + '\'' +
                ", maxStack=" + maxStack +
                ", maxLocals=" + maxLocals +
                ", code=[");
        if (code != null) {
            for (int c : code) {
                str.append(Integer.toHexString(c & 0xff)).append(" , ");
            }
        }

        str.append("], exceptionTable=")
                .append(exceptionTable)
                .append(", lineNumberTable=")
                .append(lineNumberTable).append('}');

        return str.toString();
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

    public boolean isNative() {
        return AccessPermission.isNative(accessFlag);
    }

    public boolean isAccessibleFor(MyClass d) {
        if (this.isPublic()) {
            return true;
        }
        MyClass c = this.clazz;
        if (isProtected()) {
            String cPackageName = c.getPackageName();
            String dPackageName = d.getPackageName();
            return d == c || cPackageName.equals(dPackageName) || d.isSubClassOf(c);
        }
        if (isPrivate()) {
            String cPackageName = c.getPackageName();
            String dPackageName = d.getPackageName();
            return cPackageName.equals(dPackageName);
        }
        String cPackageName = c.getPackageName();
        String dPackageName = d.getPackageName();
        return d == c || cPackageName.equals(dPackageName);
    }

    public boolean isPrivate() {
        return AccessPermission.isPrivate(accessFlag);
    }

    public boolean isProtected() {
        return AccessPermission.isProtected(accessFlag);
    }


    public boolean isPublic() {
        return AccessPermission.isPublic(accessFlag);
    }

    public boolean isStatic() {
        return AccessPermission.isStatic(accessFlag);
    }

    public int getArgsCount() {
        return argsCount;
    }

    public boolean isAbstract() {
        return AccessPermission.isAbstract(accessFlag);
    }

    public int findExceptionHandler(MyClass exClazz, int pc) {
        ExceptionHandler handler = this.exceptionTable.findExceptionHandler(exClazz, pc);
        if (handler != null) {
            return handler.getHandlePc();
        }
        return -1;
    }
}
