package jvm.runtimeDataArea.shared.heap.info;

import cmd.Parser;
import jvm.runtimeDataArea.threadDependent.VariableSlot;

/**
 * @author 22454
 */
public class MyArray {
    private VariableSlot[] array;

    public MyArray(int arrayLength) {
        if (arrayLength > 0) {
            array = new VariableSlot[arrayLength];
            for (int i = 0; i < array.length; i++) {
                array[i] = VariableSlot.createDefaultSlot();
            }
        }
    }

    public void setInteger(int index, int value) {
        this.array[index] = VariableSlot.createVariableSlot(value);
    }

    public int getInteger(int index) {
        return (Integer) this.array[index].getValue();
    }

    public void setFloat(int index, float value) {
        this.array[index] = VariableSlot.createVariableSlot(value);
    }

    public float getFloat(int index) {
        return (Float) this.array[index].getValue();
    }

    public void setLong(int index, long value) {
        this.array[index] = VariableSlot.createVariableSlot(value);
    }

    public long getLong(int index) {
        return (Long) this.array[index].getValue();
    }

    public void setDouble(int index, double value) {
        this.array[index] = VariableSlot.createVariableSlot(value);
    }

    public double getDouble(int index) {
        return (Double) this.array[index].getValue();
    }

    public void setObjectRef(int index, MyObject objectRef) {
        this.array[index] = VariableSlot.createVariableSlot(objectRef);
    }

    public MyObject getObjectRef(int index) {
        return (MyObject) this.array[index].getValue();
    }

}
