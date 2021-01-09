package jvm.runtimeDataArea.shared.heap;

import jvm.classLoadSystem.analyzer.constant.ConstantPool;
import jvm.classLoadSystem.analyzer.constant.constantInfo.ConstantInfo;
import jvm.classLoadSystem.analyzer.constant.constantInfo.constantInfoImpl.*;
import jvm.runtimeDataArea.shared.heap.info.MyClass;
import jvm.runtimeDataArea.shared.heap.info.MyField;
import jvm.runtimeDataArea.shared.heap.info.ref.MyClassRef;
import jvm.runtimeDataArea.shared.heap.info.ref.MyFieldRef;
import jvm.runtimeDataArea.shared.heap.info.ref.MyInterfaceMethodRef;
import jvm.runtimeDataArea.shared.heap.info.ref.MyMethodRef;

/**
 * @author 22454
 */
public class RuntimeConstantPool {
    private MyClass clazz;
    private Object[] constantPool;

    public RuntimeConstantPool(MyClass clazz, ConstantPool constantPool) throws Exception {
        this.clazz = clazz;
        int constantPoolCount = constantPool.getConstantPoolCount();
        this.constantPool = new Object[constantPoolCount];
        ConstantInfo[] allConstantInfo = constantPool.getAllConstantInfo();
        for (int i = 1; i < constantPoolCount; i++) {
            ConstantInfo constantInfo = allConstantInfo[i];
            switch (constantInfo.getTag()) {
                case ConstantInfo.INTEGER_TAG:
                    this.constantPool[i] = ((ConstantInfoInteger) constantInfo).getInteger();
                    break;
                case ConstantInfo.FLOAT_TAG:
                    this.constantPool[i] = ((ConstantInfoFloat) constantInfo).getFloat();
                    break;
                case ConstantInfo.LONG_TAG:
                    this.constantPool[i] = ((ConstantInfoLong) constantInfo).getLong();
                    i++;
                    break;
                case ConstantInfo.DOUBLE_TAG:
                    this.constantPool[i] = ((ConstantInfoDouble) constantInfo).getDouble();
                    i++;
                    break;
                case ConstantInfo.STRING_TAG:
                    this.constantPool[i] = ((ConstantInfoString) constantInfo).getString();
                    break;
                case ConstantInfo.CLASS_TAG:
                    ConstantInfoClass classInfo = (ConstantInfoClass) constantInfo;
                    this.constantPool[i] = MyClassRef.createClassRef(this, classInfo);
                    break;
                case ConstantInfo.FIELD_REF_TAG:
                    this.constantPool[i] = MyFieldRef.createMyFieldRef(this, (ConstantInfoFieldRef) constantInfo);
                    break;
                case ConstantInfo.INTERFACE_METHOD_REF_TAG:
                    this.constantPool[i] = MyInterfaceMethodRef.createInterfaceMethodRef(this, (ConstantInfoInterfaceMethodRef) constantInfo);
                    break;
                case ConstantInfo.METHOD_REF_TAG:
                    this.constantPool[i] = MyMethodRef.createMyMethodRef(this, (ConstantInfoMethodRef) constantInfo);
                    break;
                default:
                    break;
            }
        }
    }

    public MyClass getClazz() {
        return clazz;
    }

    public Object getConstants(int index) {
        return constantPool[index];
    }

    public Object[] getConstantPool() {
        return constantPool;
    }
}
