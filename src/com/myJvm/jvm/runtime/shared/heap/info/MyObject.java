package com.myJvm.jvm.runtime.shared.heap.info;

/**
 * @author 22454
 */
public class MyObject {
    private MyClass clazz;
    private Object data;
    private Object extra;

    public MyObject(MyClass clazz) {
        this.clazz = clazz;
        this.data = new MyArray(clazz.getInstanceCount());
    }

    public MyObject(MyClass clazz, Object data) {
        this.clazz = clazz;
        this.data = data;
    }


    public MyObject[] getRefs() {
        return (MyObject[]) this.data;
    }


    public MyObject getRefVariable(String fieldName, String descriptor) {
        MyField field = clazz.getField(fieldName, descriptor, false);
        MyArray array = (MyArray) this.data;
        return array.getObjectRef(field.getSlotIndex());
    }

    public void setRefVariable(String fieldName, String descriptor, MyObject objRef) throws Exception {
        MyField field = clazz.getField(fieldName, descriptor, false);
        MyArray array = (MyArray) this.data;
        array.setObjectRef(field.getSlotIndex(), objRef);
    }

    public void setClazz(MyClass clazz) {
        this.clazz = clazz;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }

    public char[] getCharArray() {
        return (char[]) this.data;
    }

    public MyClass getClazz() {
        return clazz;
    }

    public Object getData() {
        return data;
    }

    public Object getExtra() {
        return extra;
    }

    public int getArrayLength() {
        if (this.data instanceof byte[]) {
            return ((byte[]) this.data).length;
        }

        if (this.data instanceof short[]) {
            return ((short[]) this.data).length;
        }

        if (this.data instanceof int[]) {
            return ((int[]) this.data).length;
        }

        if (this.data instanceof long[]) {
            return ((long[]) this.data).length;
        }

        if (this.data instanceof char[]) {
            return ((char[]) this.data).length;
        }

        if (this.data instanceof float[]) {
            return ((float[]) this.data).length;
        }

        if (this.data instanceof double[]) {
            return ((double[]) this.data).length;
        }

        if (this.data instanceof Object[]) {
            return ((Object[]) this.data).length;
        }

        throw new RuntimeException("Not array");
    }

    public int[] getIntegerArray() {
        return (int[]) this.data;
    }

    public float[] getFloatArray() {
        return (float[]) this.data;
    }

    public double[] getDoubleArray() {
        return (double[]) this.data;
    }

    public MyObject[] getMyObjectArray() {
        return (MyObject[]) this.data;
    }

    public long[] getLongArray() {
        return (long[]) this.data;
    }

    public byte[] getByteArray() {
        return (byte[]) this.data;
    }

    public short[] getShortArray() {
        return (short[]) this.data;
    }

    public MyArray getFields() {
        return (MyArray) this.data;
    }

    public boolean isInstanceOf(MyClass myClass) {
        return myClass.isAssignableFrom(clazz);
    }
}
