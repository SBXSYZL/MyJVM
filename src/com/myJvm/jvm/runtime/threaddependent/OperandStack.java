package com.myJvm.jvm.runtime.threaddependent;

import com.myJvm.log.MyLog;

import java.util.LinkedList;

/**
 * 操作数栈
 *
 * @author 22454
 */
public class OperandStack {
    private LinkedList<VariableSlot> stack;
    private int maxStack;

    public OperandStack(int maxStack) {
        this.maxStack = maxStack;
        stack = new LinkedList<>();
    }

    /**
     * Integer 入栈
     *
     * @param value 值
     */
    public void pushInteger(Integer value) {
        try {
            stack.addFirst(VariableSlot.createVariableSlot(value));
        } catch (Exception e) {
            MyLog.error("Failed To Push Integer To Operand Stack");
            e.printStackTrace();
        }

    }

    /**
     * Integer 出栈
     *
     * @return 值
     */
    public Integer popInteger() {
        if (stack.size() > 0) {
            return (int) stack.removeFirst().getValue();
        }
        return null;
    }

    /**
     * Float 入栈
     *
     * @param value 值
     */
    public void pushFloat(Float value) {
        try {
            stack.addFirst(VariableSlot.createVariableSlot(value));
        } catch (Exception e) {
            MyLog.error("Failed To Push Float To Operand Stack");
            e.printStackTrace();
        }

    }

    /**
     * Float 出栈
     *
     * @return 值
     */
    public Float popFloat() {
        if (stack.size() > 0) {
            return (Float) stack.removeFirst().getValue();
        }
        return null;
    }

    /**
     * Long 入栈
     *
     * @param value 值
     */
    public void pushLong(Long value) {
        try {
            VariableSlot.LongVariableSplitEntity longVariableSplitEntity = new VariableSlot.LongVariableSplitEntity(value);
            stack.addFirst(longVariableSplitEntity.getHighVariableSlot());
            stack.addFirst(longVariableSplitEntity.getLowVariableSlot());
        } catch (Exception e) {
            MyLog.error("Failed To Push Long To Operand Stack");
            e.printStackTrace();
        }

    }

    /**
     * Long 出栈
     *
     * @return 值
     */
    public Long popLong() {
        if (stack.size() > 1) {
            VariableSlot lowSlot = stack.removeFirst();
            VariableSlot highSlot = stack.removeFirst();
            VariableSlot.LongVariableSplitEntity longVariableSplitEntity = new VariableSlot.LongVariableSplitEntity(highSlot, lowSlot);
            return longVariableSplitEntity.resumeToLong();
        }
        return null;

    }

    /**
     * Double 入栈
     *
     * @param value 值
     */
    public void pushDouble(Double value) {
        long longBits = Double.doubleToLongBits(value);
        pushLong(longBits);
    }

    /**
     * Double 出栈
     *
     * @return 值
     */
    public Double popDouble() {

        Long l = popLong();
        if (l != null) {
            return Double.longBitsToDouble(l);
        }
        return null;
    }

    /**
     * Object 入栈
     *
     * @param ref 对象引用
     */
    public void pushRef(Object ref) {
        try {
            stack.addFirst(VariableSlot.createVariableSlot(ref));
        } catch (Exception e) {
            MyLog.error("Failed To Push Ref To Operand Stack");
            e.printStackTrace();
        }

    }

    /**
     * Object 出栈
     *
     * @return 对象引用
     */
    public Object popRef() {
        if (stack.size() > 0) {
            return stack.removeFirst().getValue();
        }
        return null;

    }

    public void pushVariableSlot(VariableSlot slot) {
        try {
            stack.addFirst(VariableSlot.createVariableSlot(slot));
        } catch (Exception e) {
            MyLog.error("Failed To Push VariableSlot To Operand Stack");
            e.printStackTrace();
        }

    }

    public VariableSlot popVariableSlot() {
        if (stack.size() > 0) {
            return stack.removeFirst();
        }
        return null;
    }

    public void clear() {
        stack.clear();
    }

    public Object getRefFromTop(int n) {
        if (n >= stack.size()) {
            return null;
        }
        for (int i = 0; i < stack.size(); i++) {
            if (i == n) {
                return stack.get(i).getValue();
            }
        }
        return null;
    }

    /**
     * Boolean 入栈
     *
     * @param value bool值
     */
    public void pushBoolean(boolean value) {
        try {
            pushInteger(value ? 1 : 0);
        } catch (Exception e) {
            MyLog.error("Failed To Push Boolean To Operand Stack");
            e.printStackTrace();
        }

    }

    /**
     * Boolean 出栈
     *
     * @return bool值
     */
    public Boolean popBoolean() {
        return ((Integer) stack.removeFirst().getValue()) == 1;
    }


    public void push(char type,Object data){
        switch (type) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
                this.pushInteger((Integer) data);
                break;
            case 'F':
                this.pushFloat((Float) data);
                break;
            case 'J':
                this.pushLong((Long) data);
                break;
            case 'D':
                this.pushDouble((Double) data);
                break;
            case 'L':
            case '[':
                this.pushRef(data);
                break;
            default:
                break;
        }
    }

    public LinkedList<VariableSlot> getStack() {
        return stack;
    }

    @Override
    public String toString() {
        return "OperandStack{" +
                "stack=" + stack +
                ", maxStack=" + maxStack +
                '}';
    }
}
