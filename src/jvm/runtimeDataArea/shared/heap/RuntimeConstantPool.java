package jvm.runtimeDataArea.shared.heap;

import jvm.classLoadSystem.analyzer.constant.ConstantPool;
import jvm.classLoadSystem.analyzer.constant.constantInfo.ConstantInfo;
import jvm.classLoadSystem.analyzer.constant.constantInfo.constantInfoImpl.*;
import jvm.runtimeDataArea.shared.heap.info.MyClass;

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
