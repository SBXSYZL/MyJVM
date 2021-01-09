package jvm.runtimeDataArea.shared.heap.builder;

import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.AttributeInfoCode;
import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.ExceptionInfo;
import jvm.classLoadSystem.analyzer.constant.memberInfo.memberInfoImpl.MethodInfo;
import jvm.runtimeDataArea.common.FieldDescriptorEnum;
import jvm.runtimeDataArea.shared.heap.RuntimeConstantPool;
import jvm.runtimeDataArea.shared.heap.info.MyClass;
import jvm.runtimeDataArea.shared.heap.info.MyMethod;
import jvm.runtimeDataArea.shared.heap.info.dependence.ExceptionTable;
import jvm.runtimeDataArea.shared.heap.info.dependence.MethodDescriptor;
import jvm.runtimeDataArea.shared.heap.info.dependence.MethodDescriptorParser;
import log.MyLog;

import java.util.List;

/**
 * @author 22454
 */
public class MyMethodBuilder {
    private MyClass clazz;


    public MyMethod build(MyClass clazz, MethodInfo methodInfo) {
        try {
//            MyMethod method = null;
//            if (clazz != null) {
//                this.clazz = clazz;
//            } else {
//                throw new Exception("MyClass is Null");
//            }
//            method = new MyMethod();
//            method.setAccessFlag(methodInfo.getAccessFlag());
//            method.setName(methodInfo.getName());
//            method.setDescriptor(methodInfo.getDescriptor());
//            method.setClazz(this.clazz);
//            setMethodCodeAttribute(method, methodInfo.getCodeAttribute());
//
//            return method;
            return MyMethod.createMethod(clazz, methodInfo);
        } catch (Exception e) {
            e.printStackTrace();
            MyLog.error("Failed To Build Method");
        }
        return null;
    }

    private void setMethodCodeAttribute(MyMethod method, AttributeInfoCode codeAttribute) {
        if (codeAttribute != null) {
            method.setMaxStack(codeAttribute.getMaxStack());
            method.setMaxLocals(codeAttribute.getMaxLocals());
            method.setCode(codeAttribute.getCode());
            ExceptionInfo[] exceptionTable = codeAttribute.getExceptionTable();
            RuntimeConstantPool runtimeConstantPool = this.clazz.getRuntimeConstantPool();
            method.setExceptionTable(new ExceptionTable(exceptionTable, runtimeConstantPool));
            method.setLineNumberTable(codeAttribute.getLineNumberTableAttribute());
        }
    }

}
