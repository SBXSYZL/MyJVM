package com.myJvm.jvm.runtime.shared.heap.builder;

import com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.AttributeInfoCode;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.ExceptionInfo;
import com.myJvm.jvm.loadcore.analyzer.constant.memberInfo.impl.MethodInfo;
import com.myJvm.jvm.runtime.shared.heap.RuntimeConstantPool;
import com.myJvm.jvm.runtime.shared.heap.info.MyClass;
import com.myJvm.jvm.runtime.shared.heap.info.MyMethod;
import com.myJvm.jvm.runtime.shared.heap.info.dependence.ExceptionTable;
import com.myJvm.log.MyLog;

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
