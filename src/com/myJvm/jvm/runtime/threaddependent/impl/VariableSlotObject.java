package com.myJvm.jvm.runtime.threaddependent.impl;

import com.myJvm.jvm.runtime.threaddependent.VariableSlot;

/**
 * 对象型变量槽
 *
 * @author 22454
 */
public class VariableSlotObject implements VariableSlot {
    private Object value;

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) throws Exception {
        this.value = value;
    }
}
