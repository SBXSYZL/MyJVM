package jvm.runtimeDataArea.threadDependent;

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
    public void pushInteger(Integer value) throws Exception {
        stack.addFirst(VariableSlot.createVariableSlot(value));
    }

    /**
     * Integer 出栈
     *
     * @return 值
     */
    public Integer popInteger() {
        return (int) stack.removeFirst().getValue();
    }

    /**
     * Float 入栈
     *
     * @param value 值
     */
    public void pushFloat(Float value) throws Exception {
        stack.addFirst(VariableSlot.createVariableSlot(value));
    }

    /**
     * Float 出栈
     *
     * @return 值
     */
    public Float popFloat() {
        return (Float) stack.removeFirst().getValue();
    }

    /**
     * Long 入栈
     *
     * @param value 值
     */
    public void pushLong(Long value) throws Exception {
//        Map<String, Integer> map = LongUtil.splitLongIntoTwoIntegers(value);
//        int low = map.get(LongUtil.LOW_32_BIT_INTEGER);
//        int high = map.get(LongUtil.HIGH_32_BIT_INTEGER);
//        VariableSlot highSlot = VariableSlot.createVariableSlot(high);
//        VariableSlot lowSlot = VariableSlot.createVariableSlot(low);
        VariableSlot.LongVariableSplitEntity longVariableSplitEntity = new VariableSlot.LongVariableSplitEntity(value);
        stack.addFirst(longVariableSplitEntity.getHighVariableSlot());
        stack.addFirst(longVariableSplitEntity.getLowVariableSlot());
    }

    /**
     * Long 出栈
     *
     * @return 值
     */
    public Long popLong() {
        VariableSlot lowSlot = stack.removeFirst();
        VariableSlot highSlot = stack.removeFirst();
//        int high = (int) highSlot.getValue();
//        int low = (int) lowSlot.getValue();
//        return LongUtil.twoIntegersAreRestoredToLong(high, low);
        VariableSlot.LongVariableSplitEntity longVariableSplitEntity = new VariableSlot.LongVariableSplitEntity(highSlot, lowSlot);
        return longVariableSplitEntity.resumeToLong();
    }

    /**
     * Double 入栈
     *
     * @param value 值
     */
    public void pushDouble(Double value) throws Exception {
        long longBits = Double.doubleToLongBits(value);
        pushLong(longBits);
    }

    /**
     * Double 出栈
     *
     * @return 值
     */
    public Double popDouble() {

        long l = popLong();
        return Double.longBitsToDouble(l);
    }

    /**
     * Object 入栈
     *
     * @param ref 对象引用
     */
    public void pushRef(Object ref) throws Exception {
        stack.addFirst(VariableSlot.createVariableSlot(ref));
    }

    /**
     * Object 出栈
     *
     * @return 对象引用
     */
    public Object popRef() {
        return stack.removeFirst().getValue();
    }

    public void pushVariableSlot(VariableSlot slot) throws Exception {
        stack.addFirst(VariableSlot.createVariableSlot(slot));
    }

    public VariableSlot popVariableSlot() {
        return stack.removeFirst();
    }

    public void clear() {
        stack.clear();
    }

    public Object getRefFromTop(int n) throws Exception {
        if (n >= stack.size()) {
            throw new Exception("Get Ref From Top Fail");
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
    public void pushBoolean(boolean value) throws Exception {
        pushInteger(value ? 1 : 0);
    }

    /**
     * Boolean 出栈
     *
     * @return bool值
     */
    public Boolean popBoolean() {
        return ((Integer) stack.removeFirst().getValue()) == 1;
    }

    public LinkedList<VariableSlot> getStack() {
        return stack;
    }
}
