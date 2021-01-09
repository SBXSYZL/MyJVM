package jvm.runtimeDataArea.threadDependent;

import jvm.runtimeDataArea.shared.heap.info.MyObject;
import jvm.runtimeDataArea.threadDependent.variableSlotImpl.VariableSlotNumber;
import jvm.runtimeDataArea.threadDependent.variableSlotImpl.VariableSlotObject;
import utils.LongUtil;

import java.util.Map;

/**
 * @author 22454
 */
public interface VariableSlot {
    /**
     * 获取值
     *
     * @return 值
     */
    Object getValue();

    /**
     * 设置值
     *
     * @param value 值
     * @throws Exception ex
     */
    void setValue(Object value) throws Exception;

    static VariableSlot createDefaultSlot() {
        return new VariableSlotObject();
    }

    /**
     * 创建一个变量槽
     *
     * @param value 值
     * @return 变量槽
     */
    static VariableSlot createVariableSlot(Object value) {
        try {
            VariableSlot variableSlot = null;
            //如果是数值型
            if (value instanceof Number) {
                variableSlot = new VariableSlotNumber();
            }
            //如果是对象型
            else {
                variableSlot = new VariableSlotObject();
            }
            variableSlot.setValue(value);
            return variableSlot;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed To Create Variable Slot");
        }

    }

    class LongVariableSplitEntity {
        private VariableSlot highVariableSlot;
        private VariableSlot lowVariableSlot;

        public LongVariableSplitEntity(long value) {
            Map<String, Integer> map = LongUtil.splitLongIntoTwoIntegers(value);
            int low = map.get(LongUtil.LOW_32_BIT_INTEGER);
            int high = map.get(LongUtil.HIGH_32_BIT_INTEGER);
            this.highVariableSlot = VariableSlot.createVariableSlot(high);
            this.lowVariableSlot = VariableSlot.createVariableSlot(low);
        }

        public LongVariableSplitEntity(VariableSlot highVariableSlot, VariableSlot lowVariableSlot) {
            this.highVariableSlot = highVariableSlot;
            this.lowVariableSlot = lowVariableSlot;
        }

        public long resumeToLong() {
            int high = (int) highVariableSlot.getValue();
            int low = (int) lowVariableSlot.getValue();
            return LongUtil.twoIntegersAreRestoredToLong(high, low);
        }

        public VariableSlot getHighVariableSlot() {
            return highVariableSlot;
        }

        public VariableSlot getLowVariableSlot() {
            return lowVariableSlot;
        }
    }
}
