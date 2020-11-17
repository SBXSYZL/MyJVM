package runtimeDataArea.threadDependent;

import com.sun.org.apache.xpath.internal.operations.Bool;
import utils.LongUtil;

import java.util.LinkedList;
import java.util.Map;

/**
 * @author 22454
 */
public class OperandStack {
    private LinkedList<VariableSlot> stack;
    private int maxStack;

    public OperandStack(int maxStack) {
        this.maxStack = maxStack;
        stack = new LinkedList<>();
    }

    public void pushInteger(Integer value) throws Exception {
        stack.addFirst(VariableSlot.createVariableSlot(value));
    }

    public Integer popInteger() {
        return (int) stack.removeFirst().getValue();
    }

    public void pushFloat(Float value) throws Exception {
        stack.addFirst(VariableSlot.createVariableSlot(value));
    }

    public Float popFloat() {
        return (Float) stack.removeFirst().getValue();
    }

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

    public Long popLong() {
        VariableSlot lowSlot = stack.removeFirst();
        VariableSlot highSlot = stack.removeFirst();
//        int high = (int) highSlot.getValue();
//        int low = (int) lowSlot.getValue();
//        return LongUtil.twoIntegersAreRestoredToLong(high, low);
        VariableSlot.LongVariableSplitEntity longVariableSplitEntity = new VariableSlot.LongVariableSplitEntity(highSlot, lowSlot);
        return longVariableSplitEntity.resumeToLong();
    }

    public void pushDouble(Double value) throws Exception {
        long longBits = Double.doubleToLongBits(value);
        System.out.println("long bits: " + longBits);
        pushLong(longBits);
    }

    public Double popDouble() {

        long l = popLong();
        System.out.println("resume long bits: " + l);
        return Double.longBitsToDouble(l);
    }

    public void pushRef(Object ref) throws Exception {
        stack.addFirst(VariableSlot.createVariableSlot(ref));
    }

    public Object popRef() {
        return stack.removeFirst().getValue();
    }

    public void pushVariableSlot(VariableSlot slot) throws Exception {
        stack.addFirst(VariableSlot.createVariableSlot(slot));
    }

    public VariableSlot popVariableSlot() {
        return (VariableSlot) stack.removeFirst().getValue();
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

    public void pushBoolean(boolean value) throws Exception {
        pushInteger(value ? 1 : 0);
    }

    public Boolean popBoolean() {
        return ((Integer) stack.removeFirst().getValue()) == 1;
    }

    public LinkedList<VariableSlot> getStack() {
        return stack;
    }
}
