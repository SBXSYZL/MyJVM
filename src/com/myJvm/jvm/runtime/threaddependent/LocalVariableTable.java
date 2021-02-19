package com.myJvm.jvm.runtime.threaddependent;

import java.util.Arrays;

/**
 * @author 22454
 */
public class LocalVariableTable {
    private VariableSlot[] table;

    public LocalVariableTable(int maxLocals) {
        table = new VariableSlot[maxLocals];
    }

    /**
     * 放置一个 integer
     *
     * @param index 索引
     * @param value 值
     */
    public void putInteger(int index, Integer value) {
        table[index] = VariableSlot.createVariableSlot(value);
    }

    /**
     * 放置一个 float
     *
     * @param index 索引
     * @param value 值
     */
    public void putFloat(int index, Float value) {
        table[index] = VariableSlot.createVariableSlot(value.intValue());
    }

    /**
     * 放置一个 long
     *
     * @param index 索引
     * @param value 值
     */
    public void putLong(int index, Long value) {
        VariableSlot.LongVariableSplitEntity longVariableSplitEntity = new VariableSlot.LongVariableSplitEntity(value);
        VariableSlot highSlot = longVariableSplitEntity.getHighVariableSlot();
        VariableSlot lowSlot = longVariableSplitEntity.getLowVariableSlot();
        table[index] = lowSlot;
        table[index + 1] = highSlot;
    }

    /**
     * 放置一个 double
     *
     * @param index 索引
     * @param value 值
     */
    public void putDouble(int index, Double value) {
        long longBits = Double.doubleToLongBits(value);
        putLong(index, longBits);
    }

    /**
     * 放置一个 索引
     *
     * @param index 索引
     * @param ref   对象索引
     */
    public void putRef(int index, Object ref) {
        table[index] = VariableSlot.createVariableSlot(ref);
    }

    /**
     * 放置一个 槽
     *
     * @param index 索引
     * @param slot  值
     */
    public void putSlot(int index, VariableSlot slot) {
        table[index] = VariableSlot.createVariableSlot(slot);
    }

    /**
     * 获得一个 integer
     *
     * @param index 索引
     */
    public Integer getInteger(int index) {
        return (Integer) table[index].getValue();
    }

    /**
     * 获得一个 float
     *
     * @param index 索引
     */
    public Float getFloat(int index) {
        return (Float) table[index].getValue();
    }

    /**
     * 获得一个 long
     *
     * @param index 索引
     */
    public Long getLong(int index) {
        VariableSlot lowSlot = table[index];
        VariableSlot highSlot = table[index + 1];
        VariableSlot.LongVariableSplitEntity longVariableSplitEntity = new VariableSlot.LongVariableSplitEntity(highSlot, lowSlot);
        return longVariableSplitEntity.resumeToLong();
    }

    /**
     * 获得一个 double
     *
     * @param index 索引
     */
    public Double getDouble(int index) {
        Long aLong = getLong(index);
        return Double.longBitsToDouble(aLong);
    }

    /**
     * 获得一个 对象索引
     *
     * @param index 索引
     */
    public Object getRef(int index) {
        return table[index].getValue();
    }

    /**
     * 获得一个 变量槽
     *
     * @param index 索引
     */
    public VariableSlot getVariableSlot(int index) {
        return table[index];
    }

    /**
     * 获得 方法所属对象 this 关键字
     */
    public Object getThis() {
        return this.getRef(0);
    }

    /**
     * 获得 局部变量表
     */
    public VariableSlot[] getTable() {
        return table;
    }

    @Override
    public String toString() {
        return "LocalVariableTable{" +
                "table=" + Arrays.toString(table) +
                '}';
    }
}
