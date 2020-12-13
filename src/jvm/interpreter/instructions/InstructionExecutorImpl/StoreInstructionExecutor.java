package jvm.interpreter.instructions.InstructionExecutorImpl;

import com.sun.org.apache.bcel.internal.classfile.Code;
import javafx.scene.control.CheckBox;
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
public class StoreInstructionExecutor implements InstructionExecutor {
    public boolean isStoreInstruction(int operatorCode) {
        return operatorCode >= InstructionEnum.I_STORE && operatorCode <= InstructionEnum.S_A_STORE;
    }

    @Override
    public void execute(int operatorCode, StackFrame frame, CodeReader reader) {
        switch (operatorCode) {
            case InstructionEnum.I_STORE:
                iStore(frame, reader);
                break;
            case InstructionEnum.L_STORE:
                lStore(frame, reader);
                break;
            case InstructionEnum.F_STORE:
                fStore(frame, reader);
                break;
            case InstructionEnum.D_STORE:
                dStore(frame, reader);
                break;
            case InstructionEnum.A_STORE:
                aStore(frame, reader);
                break;
            case InstructionEnum.I_STORE_0:
                iStore0(frame);
                break;
            case InstructionEnum.I_STORE_1:
                iStore1(frame);
                break;
            case InstructionEnum.I_STORE_2:
                iStore2(frame);
                break;
            case InstructionEnum.I_STORE_3:
                iStore3(frame);
                break;

            case InstructionEnum.L_STORE_0:
                lStore0(frame);
                break;
            case InstructionEnum.L_STORE_1:
                lStore1(frame);
                break;
            case InstructionEnum.L_STORE_2:
                lStore2(frame);
                break;
            case InstructionEnum.L_STORE_3:
                lStore3(frame);
                break;
            case InstructionEnum.F_STORE_0:
                fStore0(frame);
                break;
            case InstructionEnum.F_STORE_1:
                fStore1(frame);
                break;
            case InstructionEnum.F_STORE_2:
                fStore2(frame);
                break;
            case InstructionEnum.F_STORE_3:
                fStore3(frame);
                break;
            case InstructionEnum.D_STORE_0:
                dStore0(frame);
                break;
            case InstructionEnum.D_STORE_1:
                dStore1(frame);
                break;
            case InstructionEnum.D_STORE_2:
                dStore2(frame);
                break;
            case InstructionEnum.D_STORE_3:
                dStore3(frame);
                break;
            case InstructionEnum.A_STORE_0:
                aStore0(frame);
                break;
            case InstructionEnum.A_STORE_1:
                aStore1(frame);
                break;
            case InstructionEnum.A_STORE_2:
                aStore2(frame);
                break;
            case InstructionEnum.A_STORE_3:
                aStore3(frame);
                break;
            case InstructionEnum.I_A_STORE:
                iaStore(frame);
                break;
            case InstructionEnum.L_A_STORE:
                laStore(frame);
                break;
            case InstructionEnum.F_A_STORE:
                faStore(frame);
                break;
            case InstructionEnum.D_A_STORE:
                daStore(frame);
                break;
            case InstructionEnum.A_A_STORE:
                aaStore(frame);
                break;
            case InstructionEnum.B_A_STORE:
                baStore(frame);
                break;
            case InstructionEnum.C_A_STORE:
                caStore(frame);
                break;
            case InstructionEnum.S_A_STORE:
                saStore(frame);
                break;
            default:
                throw new RuntimeException("Operator Code: " + operatorCode + " Never Found Instruction");
        }
    }

    /**
     * 将栈顶 integer 类型数据存入局部变量表的指定位置
     *
     * @param frame  当前操作栈栈帧
     * @param reader 方法 Code 属性读取器
     */
    private void iStore(StackFrame frame, CodeReader reader) {
        byte index = reader.readByte();
        Integer integer = frame.getOperandStack().popInteger();
        frame.getLocalVariableTable().putInteger(index, integer);
    }

    /**
     * 将栈顶 long 类型数据存入局部变量表的指定位置
     *
     * @param frame  当前操作栈栈帧
     * @param reader 方法 Code 属性读取器
     */
    private void lStore(StackFrame frame, CodeReader reader) {
        byte index = reader.readByte();
        Long longVal = frame.getOperandStack().popLong();
        frame.getLocalVariableTable().putLong(index, longVal);
    }

    /**
     * 将栈顶 float 类型数据存入局部变量表的指定位置
     *
     * @param frame  当前操作栈栈帧
     * @param reader 方法 Code 属性读取器
     */
    private void fStore(StackFrame frame, CodeReader reader) {
        byte index = reader.readByte();
        Float floatVal = frame.getOperandStack().popFloat();
        frame.getLocalVariableTable().putFloat(index, floatVal);
    }

    /**
     * 将栈顶 double 类型数据存入局部变量表的指定位置
     *
     * @param frame  当前操作栈栈帧
     * @param reader 方法 Code 属性读取器
     */
    private void dStore(StackFrame frame, CodeReader reader) {
        byte index = reader.readByte();
        Double doubleVal = frame.getOperandStack().popDouble();
        frame.getLocalVariableTable().putDouble(index, doubleVal);
    }

    /**
     * 将栈顶 MyObject 类型数据存入局部变量表的指定位置
     *
     * @param frame  当前操作栈栈帧
     * @param reader 方法 Code 属性读取器
     */
    private void aStore(StackFrame frame, CodeReader reader) {
        byte index = reader.readByte();
        MyObject myObject = frame.getOperandStack().popRef();
        frame.getLocalVariableTable().putRef(index, myObject);
    }

    /**
     * 将栈顶 integer 类型数据存入局部变量表的第 1 个位置
     *
     * @param frame 当前操作栈栈帧
     */
    private void iStore0(StackFrame frame) {
        Integer integer = frame.getOperandStack().popInteger();
        frame.getLocalVariableTable().putInteger(0, integer);
    }

    /**
     * 将栈顶 integer 类型数据存入局部变量表的第 2 个位置
     *
     * @param frame 当前操作栈栈帧
     */
    private void iStore1(StackFrame frame) {
        Integer integer = frame.getOperandStack().popInteger();
        frame.getLocalVariableTable().putInteger(1, integer);
    }

    /**
     * 将栈顶 integer 类型数据存入局部变量表的第 3 个位置
     *
     * @param frame 当前操作栈栈帧
     */
    private void iStore2(StackFrame frame) {
        Integer integer = frame.getOperandStack().popInteger();
        frame.getLocalVariableTable().putInteger(2, integer);
    }

    /**
     * 将栈顶 integer 类型数据存入局部变量表的第 4 个位置
     *
     * @param frame 当前操作栈栈帧
     */
    private void iStore3(StackFrame frame) {
        Integer integer = frame.getOperandStack().popInteger();
        frame.getLocalVariableTable().putInteger(3, integer);
    }

    /**
     * 将栈顶 long 类型数据存入局部变量表的第 1 个位置
     *
     * @param frame 当前操作栈栈帧
     */
    private void lStore0(StackFrame frame) {
        Long longVal = frame.getOperandStack().popLong();
        frame.getLocalVariableTable().putLong(0, longVal);
    }

    /**
     * 将栈顶 long 类型数据存入局部变量表的第 2 个位置
     *
     * @param frame 当前操作栈栈帧
     */
    private void lStore1(StackFrame frame) {
        Long longVal = frame.getOperandStack().popLong();
        frame.getLocalVariableTable().putLong(1, longVal);
    }

    /**
     * 将栈顶 long 类型数据存入局部变量表的第 3 个位置
     *
     * @param frame 当前操作栈栈帧
     */
    private void lStore2(StackFrame frame) {
        Long longVal = frame.getOperandStack().popLong();
        frame.getLocalVariableTable().putLong(2, longVal);
    }

    /**
     * 将栈顶 long 类型数据存入局部变量表的第 4 个位置
     *
     * @param frame 当前操作栈栈帧
     */
    private void lStore3(StackFrame frame) {
        Long longVal = frame.getOperandStack().popLong();
        frame.getLocalVariableTable().putLong(3, longVal);
    }

    /**
     * 将栈顶 float 类型数据存入局部变量表的第 1 个位置
     *
     * @param frame 当前操作栈栈帧
     */
    private void fStore0(StackFrame frame) {
        Float floatVal = frame.getOperandStack().popFloat();
        frame.getLocalVariableTable().putFloat(0, floatVal);
    }

    /**
     * 将栈顶 float 类型数据存入局部变量表的第 2 个位置
     *
     * @param frame 当前操作栈栈帧
     */
    private void fStore1(StackFrame frame) {
        Float floatVal = frame.getOperandStack().popFloat();
        frame.getLocalVariableTable().putFloat(1, floatVal);
    }

    /**
     * 将栈顶 float 类型数据存入局部变量表的第 3 个位置
     *
     * @param frame 当前操作栈栈帧
     */
    private void fStore2(StackFrame frame) {
        Float floatVal = frame.getOperandStack().popFloat();
        frame.getLocalVariableTable().putFloat(2, floatVal);
    }

    /**
     * 将栈顶 float 类型数据存入局部变量表的第 4 个位置
     *
     * @param frame 当前操作栈栈帧
     */
    private void fStore3(StackFrame frame) {
        Float floatVal = frame.getOperandStack().popFloat();
        frame.getLocalVariableTable().putFloat(3, floatVal);
    }

    /**
     * 将栈顶 double 类型数据存入局部变量表的第 1 个位置
     *
     * @param frame 当前操作栈栈帧
     */
    private void dStore0(StackFrame frame) {
        Double doubleVal = frame.getOperandStack().popDouble();
        frame.getLocalVariableTable().putDouble(0, doubleVal);
    }

    /**
     * 将栈顶 double 类型数据存入局部变量表的第 2 个位置
     *
     * @param frame 当前操作栈栈帧
     */
    private void dStore1(StackFrame frame) {
        Double doubleVal = frame.getOperandStack().popDouble();
        frame.getLocalVariableTable().putDouble(1, doubleVal);
    }

    /**
     * 将栈顶 double 类型数据存入局部变量表的第 3 个位置
     *
     * @param frame 当前操作栈栈帧
     */
    private void dStore2(StackFrame frame) {
        Double doubleVal = frame.getOperandStack().popDouble();
        frame.getLocalVariableTable().putDouble(2, doubleVal);
    }

    /**
     * 将栈顶 double 类型数据存入局部变量表的第 4 个位置
     *
     * @param frame 当前操作栈栈帧
     */
    private void dStore3(StackFrame frame) {
        Double doubleVal = frame.getOperandStack().popDouble();
        frame.getLocalVariableTable().putDouble(3, doubleVal);
    }

    /**
     * 将栈顶 MyObject 类型数据存入局部变量表的第 1 个位置
     *
     * @param frame 当前操作栈栈帧
     */
    private void aStore0(StackFrame frame) {
        MyObject myObject = frame.getOperandStack().popRef();
        frame.getLocalVariableTable().putRef(0, myObject);
    }

    /**
     * 将栈顶 MyObject 类型数据存入局部变量表的第 2 个位置
     *
     * @param frame 当前操作栈栈帧
     */
    private void aStore1(StackFrame frame) {
        MyObject myObject = frame.getOperandStack().popRef();
        frame.getLocalVariableTable().putRef(1, myObject);
    }

    /**
     * 将栈顶 MyObject 类型数据存入局部变量表的第 3 个位置
     *
     * @param frame 当前操作栈栈帧
     */
    private void aStore2(StackFrame frame) {
        MyObject myObject = frame.getOperandStack().popRef();
        frame.getLocalVariableTable().putRef(2, myObject);
    }

    /**
     * 将栈顶 MyObject 类型数据存入局部变量表的第 4 个位置
     *
     * @param frame 当前操作栈栈帧
     */
    private void aStore3(StackFrame frame) {
        MyObject myObject = frame.getOperandStack().popRef();
        frame.getLocalVariableTable().putRef(3, myObject);
    }

    /**
     * 将栈顶 integer 类型数据存入指定数组的指定位置
     *
     * @param frame 当前操作栈栈帧
     */
    private void iaStore(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Integer value = operandStack.popInteger();
        Integer index = operandStack.popInteger();
        MyObject ref = operandStack.popRef();
        if (ref == null) {
            throw new RuntimeException("Failed To Exec Instruction: iaStore");
        }
        int[] integerArray = ref.getIntegerArray();
        CheckUtil.checkIndex(integerArray.length, index);
        integerArray[index] = value;

    }

    /**
     * 将栈顶 long 类型数据存入指定数组的指定位置
     *
     * @param frame 当前操作栈栈帧
     */
    private void laStore(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Long value = operandStack.popLong();
        Integer index = operandStack.popInteger();
        MyObject ref = operandStack.popRef();
        if (ref == null) {
            throw new RuntimeException("Failed To Exec Instruction: laStore");
        }
        long[] longArray = ref.getLongArray();
        CheckUtil.checkIndex(longArray.length, index);
        longArray[index] = value;

    }

    /**
     * 将栈顶 float 类型数据存入指定数组的指定位置
     *
     * @param frame 当前操作栈栈帧
     */
    private void faStore(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Float value = operandStack.popFloat();
        Integer index = operandStack.popInteger();
        MyObject ref = operandStack.popRef();
        if (ref == null) {
            throw new RuntimeException("Failed To Exec Instruction: faStore");
        }
        float[] floatArray = ref.getFloatArray();
        CheckUtil.checkIndex(floatArray.length, index);
        floatArray[index] = value;
    }

    /**
     * 将栈顶 double 类型数据存入指定数组的指定位置
     *
     * @param frame 当前操作栈栈帧
     */
    private void daStore(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Double value = operandStack.popDouble();
        Integer index = operandStack.popInteger();
        MyObject ref = operandStack.popRef();
        if (ref == null) {
            throw new RuntimeException("Failed To Exec Instruction: daStore");
        }
        double[] doubleArray = ref.getDoubleArray();
        CheckUtil.checkIndex(doubleArray.length, index);
        doubleArray[index] = value;
    }

    /**
     * 将栈顶 MyObject 类型数据存入指定数组的指定位置
     *
     * @param frame 当前操作栈栈帧
     */
    private void aaStore(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        MyObject value = operandStack.popRef();
        Integer index = operandStack.popInteger();
        MyObject ref = operandStack.popRef();
        if (ref == null) {
            throw new RuntimeException("Failed To Exec Instruction: aaStore");
        }
        MyObject[] myObjectArray = ref.getMyObjectArray();
        CheckUtil.checkIndex(myObjectArray.length, index);
        myObjectArray[index] = value;
    }

    /**
     * 将栈顶 boolean / byte 类型数据存入指定数组的指定位置
     */
    private void baStore(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int value = operandStack.popInteger();
        Integer index = operandStack.popInteger();
        MyObject ref = operandStack.popRef();
        if (ref == null) {
            throw new RuntimeException("Failed To Exec Instruction: baStore");
        }
        byte[] byteArray = ref.getByteArray();
        CheckUtil.checkIndex(byteArray.length, index);
        byteArray[index] = (byte) value;
    }

    /**
     * 将栈顶 char 类型数据存入指定数组的指定位置
     */
    private void caStore(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int value = operandStack.popInteger();
        Integer index = operandStack.popInteger();
        MyObject ref = operandStack.popRef();
        if (ref == null) {
            throw new RuntimeException("Failed To Exec Instruction: caStore");
        }
        char[] charArray = ref.getCharArray();
        CheckUtil.checkIndex(charArray.length, index);
        charArray[index] = (char) value;
    }

    /**
     * 将栈顶 short 类型数据存入指定数组的指定位置
     */
    private void saStore(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int value = operandStack.popInteger();
        Integer index = operandStack.popInteger();
        MyObject ref = operandStack.popRef();
        if (ref == null) {
            throw new RuntimeException("Failed To Exec Instruction: saStore");
        }
        short[] shortArray = ref.getShortArray();
        CheckUtil.checkIndex(shortArray.length, index);
        shortArray[index] = (short) value;
    }


}
