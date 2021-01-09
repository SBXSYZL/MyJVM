package jvm.runtimeDataArea.shared.heap.info.dependence;

import jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.ExceptionInfo;
import jvm.runtimeDataArea.shared.heap.RuntimeConstantPool;
import jvm.runtimeDataArea.shared.heap.info.MyClass;
import jvm.runtimeDataArea.shared.heap.info.ref.MyClassRef;

/**
 * @author 22454
 */
public class ExceptionTable {
    private ExceptionHandler[] exceptionTable;

    public ExceptionTable(ExceptionInfo[] exceptionInfos, RuntimeConstantPool runtimeConstantPool) {
        this.exceptionTable = new ExceptionHandler[exceptionInfos.length];
        for (int i = 0; i < exceptionInfos.length; i++) {
            Object constants = null;
            if (exceptionInfos[i].getCatchType() != 0) {
                constants = runtimeConstantPool.getConstants(exceptionInfos[i].getCatchType());
            }
            ExceptionHandler exceptionHandler = new ExceptionHandler(
                    exceptionInfos[i].getStartPc(),
                    exceptionInfos[i].getEndPc(),
                    exceptionInfos[i].getHandlerPc(),
                    (MyClassRef) constants
            );
            exceptionTable[i] = exceptionHandler;
        }
    }

    public ExceptionHandler findExceptionHandler(MyClass exClazz, int pc) {
        for (ExceptionHandler handler : exceptionTable) {
            if (pc >= handler.getStartPc() && pc < handler.getEndPc()) {
                if (null == handler.getCatchType()) {
                    return handler;
                }
                try {
                    MyClass catchType = handler.getCatchType().resolvedClass();
                    if (catchType == exClazz || catchType.isSubClassOf(exClazz)) {
                        return handler;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
