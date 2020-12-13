package jvm.interpreter.instructions.InstructionExecutorImpl;

import jvm.interpreter.CodeReader;
import jvm.interpreter.instructions.InstructionEnum;
import jvm.interpreter.instructions.InstructionExecutor;
import jvm.runtimeDataArea.shared.heap.info.MyObject;
import jvm.runtimeDataArea.threadDependent.OperandStack;
import jvm.runtimeDataArea.threadDependent.StackFrame;
import utils.CheckUtil;

/**
 * @author 22454
 */
public class LoadInstructionExecutor implements InstructionExecutor {
    public boolean isLoadInstruction(int operatorCode) {
        return operatorCode >= InstructionEnum.I_LOAD && operatorCode <= InstructionEnum.S_A_LOAD;
    }

    @Override
    public void execute(int operatorCode, StackFrame frame, CodeReader reader) {
        switch (operatorCode) {
            case InstructionEnum.I_LOAD:
                iLoad(frame, reader);
                break;
            case InstructionEnum.L_LOAD:
                lLoad(frame, reader);
                break;
            case InstructionEnum.F_LOAD:
                fLoad(frame, reader);
                break;
            case InstructionEnum.D_LOAD:
                dLoad(frame, reader);
                break;
            case InstructionEnum.A_LOAD:
                aLoad(frame, reader);
                break;
            case InstructionEnum.I_LOAD_0:
                iLoad0(frame);
                break;
            case InstructionEnum.I_LOAD_1:
                iLoad1(frame);
                break;
            case InstructionEnum.I_LOAD_2:
                iLoad2(frame);
                break;
            case InstructionEnum.I_LOAD_3:
                iLoad3(frame);
                break;
            case InstructionEnum.L_LOAD_0:
                lLoad0(frame);
                break;
            case InstructionEnum.L_LOAD_1:
                lLoad1(frame);
                break;
            case InstructionEnum.L_LOAD_2:
                lLoad2(frame);
                break;
            case InstructionEnum.L_LOAD_3:
                lLoad3(frame);
                break;
            case InstructionEnum.F_LOAD_0:
                fLoad0(frame);
                break;
            case InstructionEnum.F_LOAD_1:
                fLoad1(frame);
                break;
            case InstructionEnum.F_LOAD_2:
                fLoad2(frame);
                break;
            case InstructionEnum.F_LOAD_3:
                fLoad3(frame);
                break;
            case InstructionEnum.D_LOAD_0:
                dLoad0(frame);
                break;
            case InstructionEnum.D_LOAD_1:
                dLoad1(frame);
                break;
            case InstructionEnum.D_LOAD_2:
                dLoad2(frame);
                break;
            case InstructionEnum.D_LOAD_3:
                dLoad3(frame);
                break;
            case InstructionEnum.A_LOAD_0:
                aLoad0(frame);
                break;
            case InstructionEnum.A_LOAD_1:
                aLoad1(frame);
                break;
            case InstructionEnum.A_LOAD_2:
                aLoad2(frame);
                break;
            case InstructionEnum.A_LOAD_3:
                aLoad3(frame);
                break;
            case InstructionEnum.I_A_LOAD:
                iaLoad(frame);
                break;
            case InstructionEnum.L_A_LOAD:
                laLoad(frame);
                break;
            case InstructionEnum.F_A_LOAD:
                faLoad(frame);
                break;
            case InstructionEnum.D_A_LOAD:
                daLoad(frame);
                break;
            case InstructionEnum.A_A_LOAD:
                aaLoad(frame);
                break;
            case InstructionEnum.B_A_LOAD:
                baLoad(frame);
                break;
            case InstructionEnum.C_A_LOAD:
                caLoad(frame);
                break;
            case InstructionEnum.S_A_LOAD:
                saLoad(frame);
                break;
            default:
                throw new RuntimeException("Operator Code: " + operatorCode + " Never Found Instruction");
        }
    }

    /**
     * 将指定位置的 int 类型从局部变量表推送至栈顶
     *
     * @param frame  当前操作栈栈帧
     * @param reader 方法 Code 属性读取器
     */
    private void iLoad(StackFrame frame, CodeReader reader) {
        int localVariableIndex = reader.readByte();
        Integer integer = frame.getLocalVariableTable().getInteger(localVariableIndex);
        frame.getOperandStack().pushInteger(integer);
    }

    /**
     * 将指定位置的 long 类型从局部变量表推送至栈顶
     *
     * @param frame  当前操作栈栈帧
     * @param reader 方法 Code 属性读取器
     */
    private void lLoad(StackFrame frame, CodeReader reader) {
        int localVariableIndex = reader.readByte();
        Long longValue = frame.getLocalVariableTable().getLong(localVariableIndex);
        frame.getOperandStack().pushLong(longValue);
    }

    /**
     * 将指定位置的 float 类型从局部变量表推送至栈顶
     *
     * @param frame  当前操作栈栈帧
     * @param reader 方法 Code 属性读取器
     */
    private void fLoad(StackFrame frame, CodeReader reader) {
        int localVariableIndex = reader.readByte();
        Float aFloat = frame.getLocalVariableTable().getFloat(localVariableIndex);
        frame.getOperandStack().pushFloat(aFloat);
    }

    /**
     * 将指定位置的 double 类型从局部变量表推送至栈顶
     *
     * @param frame  当前操作栈栈帧
     * @param reader 方法 Code 属性读取器
     */
    private void dLoad(StackFrame frame, CodeReader reader) {
        byte localVariableIndex = reader.readByte();
        Double val = frame.getLocalVariableTable().getDouble(localVariableIndex);
        frame.getOperandStack().pushDouble(val);
    }

    /**
     * 将指定位置的 MyObject 类型从局部变量表推送至栈顶
     *
     * @param frame  当前操作栈栈帧
     * @param reader 方法 Code 属性读取器
     */
    private void aLoad(StackFrame frame, CodeReader reader) {
        byte localVariableIndex = reader.readByte();
        MyObject ref = frame.getLocalVariableTable().getRef(localVariableIndex);
        frame.getOperandStack().pushRef(ref);
    }

    /**
     * 将局部变量表第 1 个 integer 数据推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void iLoad0(StackFrame frame) {
        Integer integer = frame.getLocalVariableTable().getInteger(0);
        frame.getOperandStack().pushInteger(integer);
    }

    /**
     * 将局部变量表第 2 个 integer 数据推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void iLoad1(StackFrame frame) {
        Integer integer = frame.getLocalVariableTable().getInteger(1);
        frame.getOperandStack().pushInteger(integer);
    }

    /**
     * 将局部变量表第 3 个 integer 数据推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void iLoad2(StackFrame frame) {
        Integer integer = frame.getLocalVariableTable().getInteger(2);
        frame.getOperandStack().pushInteger(integer);
    }

    /**
     * 将局部变量表第 4 个 integer 数据推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void iLoad3(StackFrame frame) {
        Integer integer = frame.getLocalVariableTable().getInteger(3);
        frame.getOperandStack().pushInteger(integer);
    }

    /**
     * 将局部变量表第 1 个 long 数据推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void lLoad0(StackFrame frame) {
        Long longVal = frame.getLocalVariableTable().getLong(0);
        frame.getOperandStack().pushLong(longVal);
    }

    /**
     * 将局部变量表第 2 个 long 数据推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void lLoad1(StackFrame frame) {
        Long longVal = frame.getLocalVariableTable().getLong(1);
        frame.getOperandStack().pushLong(longVal);
    }

    /**
     * 将局部变量表第 3 个 long 数据推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void lLoad2(StackFrame frame) {
        Long longVal = frame.getLocalVariableTable().getLong(2);
        frame.getOperandStack().pushLong(longVal);
    }

    /**
     * 将局部变量表第 4 个 long 数据推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void lLoad3(StackFrame frame) {
        Long longVal = frame.getLocalVariableTable().getLong(3);
        frame.getOperandStack().pushLong(longVal);
    }

    /**
     * 将局部变量表第 1 个 float 数据推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void fLoad0(StackFrame frame) {
        Float floatVal = frame.getLocalVariableTable().getFloat(0);
        frame.getOperandStack().pushFloat(floatVal);
    }

    /**
     * 将局部变量表第 2 个 float 数据推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void fLoad1(StackFrame frame) {
        Float floatVal = frame.getLocalVariableTable().getFloat(1);
        frame.getOperandStack().pushFloat(floatVal);
    }

    /**
     * 将局部变量表第 3 个 float 数据推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void fLoad2(StackFrame frame) {
        Float floatVal = frame.getLocalVariableTable().getFloat(2);
        frame.getOperandStack().pushFloat(floatVal);
    }

    /**
     * 将局部变量表第 4 个 float 数据推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void fLoad3(StackFrame frame) {
        Float floatVal = frame.getLocalVariableTable().getFloat(3);
        frame.getOperandStack().pushFloat(floatVal);
    }

    /**
     * 将局部变量表第 1 个 double 数据推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void dLoad0(StackFrame frame) {
        Double doubleVal = frame.getLocalVariableTable().getDouble(0);
        frame.getOperandStack().pushDouble(doubleVal);
    }

    /**
     * 将局部变量表第 2 个 double 数据推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void dLoad1(StackFrame frame) {
        Double doubleVal = frame.getLocalVariableTable().getDouble(1);
        frame.getOperandStack().pushDouble(doubleVal);
    }

    /**
     * 将局部变量表第 3 个 double 数据推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void dLoad2(StackFrame frame) {
        Double doubleVal = frame.getLocalVariableTable().getDouble(2);
        frame.getOperandStack().pushDouble(doubleVal);
    }

    /**
     * 将局部变量表第 4 个 double 数据推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void dLoad3(StackFrame frame) {
        Double doubleVal = frame.getLocalVariableTable().getDouble(3);
        frame.getOperandStack().pushDouble(doubleVal);
    }

    /**
     * 将局部变量表第 1 个 MyObject 数据推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void aLoad0(StackFrame frame) {
        MyObject ref = frame.getLocalVariableTable().getRef(0);
        frame.getOperandStack().pushRef(ref);
    }

    /**
     * 将局部变量表第 2 个 MyObject 数据推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void aLoad1(StackFrame frame) {
        MyObject ref = frame.getLocalVariableTable().getRef(1);
        frame.getOperandStack().pushRef(ref);
    }

    /**
     * 将局部变量表第 3 个 MyObject 数据推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void aLoad2(StackFrame frame) {
        MyObject ref = frame.getLocalVariableTable().getRef(2);
        frame.getOperandStack().pushRef(ref);
    }

    /**
     * 将局部变量表第 4 个 MyObject 数据推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void aLoad3(StackFrame frame) {
        MyObject ref = frame.getLocalVariableTable().getRef(3);
        frame.getOperandStack().pushRef(ref);
    }

    /**
     * 将 integer 类型数组的指定位置元素推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void iaLoad(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Integer index = operandStack.popInteger();
        MyObject ref = operandStack.popRef();
        if (ref == null) {
            throw new RuntimeException("Failed To Exec Instruction: iaLoad");
        }
        int[] integerArray = ref.getIntegerArray();
        CheckUtil.checkIndex(integerArray.length, index);
        operandStack.pushInteger(integerArray[index]);
    }

    /**
     * 将 long 类型数组的指定位置元素推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void laLoad(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Integer index = operandStack.popInteger();
        MyObject ref = operandStack.popRef();
        if (ref == null) {
            throw new RuntimeException("Failed To Exec Instruction: iaLoad");
        }
        long[] longArray = ref.getLongArray();
        CheckUtil.checkIndex(longArray.length, index);
        operandStack.pushLong(longArray[index]);
    }

    /**
     * 将 float 类型数组的指定位置元素推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void faLoad(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Integer index = operandStack.popInteger();
        MyObject ref = operandStack.popRef();
        if (ref == null) {
            throw new RuntimeException("Failed To Exec Instruction: iaLoad");
        }
        float[] floatArray = ref.getFloatArray();
        CheckUtil.checkIndex(floatArray.length, index);
        operandStack.pushFloat(floatArray[index]);
    }

    /**
     * 将 double 类型数组的指定位置元素推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void daLoad(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Integer index = operandStack.popInteger();
        MyObject ref = operandStack.popRef();
        if (ref == null) {
            throw new RuntimeException("Failed To Exec Instruction: iaLoad");
        }
        double[] doubleArray = ref.getDoubleArray();
        CheckUtil.checkIndex(doubleArray.length, index);
        operandStack.pushDouble(doubleArray[index]);
    }

    /**
     * 将 MyObject 类型数组的指定位置元素推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void aaLoad(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Integer index = operandStack.popInteger();
        MyObject ref = operandStack.popRef();
        if (ref == null) {
            throw new RuntimeException("Failed To Exec Instruction: iaLoad");
        }
        MyObject[] myObjectArray = ref.getMyObjectArray();
        CheckUtil.checkIndex(myObjectArray.length, index);
        operandStack.pushRef(myObjectArray[index]);
    }

    /**
     * 将 byte/boolean 类型数组的指定位置元素推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void baLoad(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Integer index = operandStack.popInteger();
        MyObject ref = operandStack.popRef();
        if (ref == null) {
            throw new RuntimeException("Failed To Exec Instruction: iaLoad");
        }
        byte[] byteArray = ref.getByteArray();
        CheckUtil.checkIndex(byteArray.length, index);
        operandStack.pushInteger((int) byteArray[index]);
    }

    /**
     * 将 char 类型数组的指定位置元素推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void caLoad(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Integer index = operandStack.popInteger();
        MyObject ref = operandStack.popRef();
        if (ref == null) {
            throw new RuntimeException("Failed To Exec Instruction: iaLoad");
        }
        char[] charArray = ref.getCharArray();
        CheckUtil.checkIndex(charArray.length, index);
        operandStack.pushInteger((int) charArray[index]);
    }

    /**
     * 将 short 类型数组的指定位置元素推送至栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void saLoad(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Integer index = operandStack.popInteger();
        MyObject ref = operandStack.popRef();
        if (ref == null) {
            throw new RuntimeException("Failed To Exec Instruction: iaLoad");
        }
        short[] shortArray = ref.getShortArray();
        operandStack.pushInteger((int) shortArray[index]);
    }
}
