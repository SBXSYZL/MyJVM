package com.myJvm.jvm.runtime.threaddependent.impl;

import com.myJvm.jvm.runtime.threaddependent.VariableSlot;

/**
 * 数值型变量槽
 *
 * @author 22454
 */
public class VariableSlotNumber implements VariableSlot {
    private Number value;

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) throws Exception {
        if (value instanceof Number) {
            this.value = (Number) value;
        } else {
            throw new Exception("Set Number Variable Slot Value Fail");
        }
    }
}
