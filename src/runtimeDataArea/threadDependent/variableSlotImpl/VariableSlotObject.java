package runtimeDataArea.threadDependent.variableSlotImpl;

import runtimeDataArea.threadDependent.VariableSlot;

/**
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
