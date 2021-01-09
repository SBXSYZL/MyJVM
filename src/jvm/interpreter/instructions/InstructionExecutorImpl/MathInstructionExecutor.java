package jvm.interpreter.instructions.InstructionExecutorImpl;

import com.sun.org.apache.bcel.internal.generic.IXOR;
import jvm.interpreter.CodeReader;
import jvm.interpreter.instructions.InstructionEnum;
import jvm.interpreter.instructions.InstructionExecutor;
import jvm.runtimeDataArea.threadDependent.LocalVariableTable;
import jvm.runtimeDataArea.threadDependent.OperandStack;
import jvm.runtimeDataArea.threadDependent.StackFrame;
import log.MyLog;

/**
 * 运算指令
 *
 * @author 22454
 */
public class MathInstructionExecutor implements InstructionExecutor {

    public boolean isMathInstruction(int operatorCode) {
        return operatorCode >= InstructionEnum.I_ADD && operatorCode <= InstructionEnum.I_INC;
    }


    @Override
    public void execute(int operatorCode, StackFrame frame, CodeReader reader) {
        switch (operatorCode) {
            case InstructionEnum.I_ADD:
                iAdd(frame);
                break;
            case InstructionEnum.L_ADD:
                lAdd(frame);
                break;
            case InstructionEnum.F_ADD:
                fAdd(frame);
                break;
            case InstructionEnum.D_ADD:
                dAdd(frame);
                break;
            case InstructionEnum.I_SUB:
                iSub(frame);
                break;
            case InstructionEnum.L_SUB:
                lSub(frame);
                break;
            case InstructionEnum.F_SUB:
                fSub(frame);
                break;
            case InstructionEnum.D_SUB:
                dSub(frame);
                break;
            case InstructionEnum.I_MUL:
                iMul(frame);
                break;
            case InstructionEnum.L_MUL:
                lMul(frame);
                break;
            case InstructionEnum.F_MUL:
                fMul(frame);
                break;
            case InstructionEnum.D_MUL:
                dMul(frame);
                break;
            case InstructionEnum.I_DIV:
                iDiv(frame);
                break;
            case InstructionEnum.L_DIV:
                lDiv(frame);
                break;
            case InstructionEnum.F_DIV:
                fDiv(frame);
                break;
            case InstructionEnum.D_DIV:
                dDiv(frame);
                break;
            case InstructionEnum.I_REM:
                iRem(frame);
                break;
            case InstructionEnum.L_REM:
                lRem(frame);
                break;
            case InstructionEnum.F_REM:
                fRem(frame);
                break;
            case InstructionEnum.D_REM:
                dRem(frame);
                break;
            case InstructionEnum.I_NEG:
                iNeg(frame);
                break;
            case InstructionEnum.L_NEG:
                lNeg(frame);
                break;
            case InstructionEnum.F_NEG:
                fNeg(frame);
                break;
            case InstructionEnum.D_NEG:
                dNeg(frame);
                break;
            case InstructionEnum.I_SH_L:
                iShL(frame);
                break;
            case InstructionEnum.L_SH_L:
                lShL(frame);
                break;
            case InstructionEnum.I_SH_R:
                iShR(frame);
                break;
            case InstructionEnum.L_SH_R:
                lShR(frame);
                break;
            case InstructionEnum.I_U_SH_R:
                iuShR(frame);
                break;
            case InstructionEnum.L_U_SH_R:
                luShR(frame);
                break;
            case InstructionEnum.I_AND:
                iAnd(frame);
                break;
            case InstructionEnum.L_AND:
                lAnd(frame);
                break;
            case InstructionEnum.I_OR:
                iOr(frame);
                break;
            case InstructionEnum.L_OR:
                lOr(frame);
                break;
            case InstructionEnum.I_XOR:
                iXOr(frame);
                break;
            case InstructionEnum.L_XOR:
                lXOr(frame);
                break;
            case InstructionEnum.I_INC:
                iInc(frame, reader);
                break;
            default:
                throw new RuntimeException("Operator Code: " + operatorCode + " Never Found Instruction");
        }
    }

    /**
     * 将栈顶两个 integer 类型数据相加，然后将结果压回栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void iAdd(StackFrame frame) {
        MyLog.command("iadd");
        OperandStack operandStack = frame.getOperandStack();
        Integer integer1 = operandStack.popInteger();
        Integer integer2 = operandStack.popInteger();
        int addResult = integer1 + integer2;
        operandStack.pushInteger(addResult);
    }

    /**
     * 将栈顶两个 long 类型数据相加，然后将结果压回栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void lAdd(StackFrame frame) {
        MyLog.command("ladd");
        OperandStack operandStack = frame.getOperandStack();
        Long longVal1 = operandStack.popLong();
        Long longVal2 = operandStack.popLong();
        long addResult = longVal1 + longVal2;
        operandStack.pushLong(addResult);
    }

    /**
     * 将栈顶两个 float 类型数据相加，然后将结果压回栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void fAdd(StackFrame frame) {
        MyLog.command("fadd");
        OperandStack operandStack = frame.getOperandStack();
        Float floatVal1 = operandStack.popFloat();
        Float floatVal2 = operandStack.popFloat();
        float addResult = floatVal1 + floatVal2;
        operandStack.pushFloat(addResult);
    }

    /**
     * 将栈顶两个 double 类型数据相加，然后将结果压回栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void dAdd(StackFrame frame) {
        MyLog.command("dadd");
        OperandStack operandStack = frame.getOperandStack();
        Double doubleVal1 = operandStack.popDouble();
        Double doubleVal2 = operandStack.popDouble();
        double addResult = doubleVal1 + doubleVal2;
        operandStack.pushDouble(addResult);
    }

    /**
     * 将栈顶两个 integer 类型数据相减，然后将结果压回栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void iSub(StackFrame frame) {
        MyLog.command("isub");
        OperandStack operandStack = frame.getOperandStack();
        Integer integer1 = operandStack.popInteger();
        Integer integer2 = operandStack.popInteger();
        int subResult = integer1 - integer2;
        operandStack.pushInteger(subResult);
    }

    /**
     * 将栈顶两个 long 类型数据相减，然后将结果压回栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void lSub(StackFrame frame) {
        MyLog.command("lsub");
        OperandStack operandStack = frame.getOperandStack();
        Long longVal1 = operandStack.popLong();
        Long longVal2 = operandStack.popLong();
        long subResult = longVal1 - longVal2;
        operandStack.pushLong(subResult);
    }

    /**
     * 将栈顶两个 float 类型数据相减，然后将结果压回栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void fSub(StackFrame frame) {
        MyLog.command("fsub");
        OperandStack operandStack = frame.getOperandStack();
        float floatVal1 = operandStack.popFloat();
        float floatVal2 = operandStack.popFloat();
        float subResult = floatVal1 - floatVal2;
        operandStack.pushFloat(subResult);
    }

    /**
     * 将栈顶两个 double 类型数据相减，然后将结果压回栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void dSub(StackFrame frame) {
        MyLog.command("dsub");
        OperandStack operandStack = frame.getOperandStack();
        double doubleVal1 = operandStack.popDouble();
        double doubleVal2 = operandStack.popLong();
        double subResult = doubleVal1 - doubleVal2;
        operandStack.pushDouble(subResult);
    }

    /**
     * 将栈顶两个 integer 类型数据相乘，然后将结果压回栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void iMul(StackFrame frame) {
        MyLog.command("imul");
        OperandStack operandStack = frame.getOperandStack();
        Integer integerVal1 = operandStack.popInteger();
        Integer integerVal2 = operandStack.popInteger();
        int mulResult = integerVal1 * integerVal2;
        operandStack.pushInteger(mulResult);
    }

    /**
     * 将栈顶两个 long 类型数据相乘，然后将结果压回栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void lMul(StackFrame frame) {
        MyLog.command("lmul");
        OperandStack operandStack = frame.getOperandStack();
        long val1 = operandStack.popLong();
        long val2 = operandStack.popLong();
        long mulResult = val1 * val2;
        operandStack.pushLong(mulResult);
    }

    /**
     * 将栈顶两个 float 类型数据相乘，然后将结果压回栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void fMul(StackFrame frame) {
        MyLog.command("fmul");
        OperandStack operandStack = frame.getOperandStack();
        float val1 = operandStack.popFloat();
        float val2 = operandStack.popFloat();
        float mulResult = val1 * val2;
        operandStack.pushFloat(mulResult);
    }

    /**
     * 将栈顶两个 double 类型数据相乘，然后将结果压回栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void dMul(StackFrame frame) {
        MyLog.command("dmul");
        OperandStack operandStack = frame.getOperandStack();
        double val1 = operandStack.popDouble();
        double val2 = operandStack.popDouble();
        double mulResult = val1 * val2;
        operandStack.pushDouble(mulResult);
    }

    /**
     * 将栈顶两个 integer 类型数据相除，然后将结果压回栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void iDiv(StackFrame frame) {
        MyLog.command("idiv");
        OperandStack operandStack = frame.getOperandStack();
        int val1 = operandStack.popInteger();
        int val2 = operandStack.popInteger();
        int mulResult = val1 / val2;
        operandStack.pushInteger(mulResult);
    }

    /**
     * 将栈顶两个 long 类型数据相除，然后将结果压回栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void lDiv(StackFrame frame) {
        MyLog.command("ldiv");
        OperandStack operandStack = frame.getOperandStack();
        long val1 = operandStack.popLong();
        long val2 = operandStack.popLong();
        long mulResult = val1 / val2;
        operandStack.pushLong(mulResult);
    }

    /**
     * 将栈顶两个 float 类型数据相除，然后将结果压回栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void fDiv(StackFrame frame) {
        MyLog.command("fdiv");
        OperandStack operandStack = frame.getOperandStack();
        float val1 = operandStack.popFloat();
        float val2 = operandStack.popFloat();
        float mulResult = val1 / val2;
        operandStack.pushFloat(mulResult);
    }

    /**
     * 将栈顶两个 double 类型数据相除，然后将结果压回栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void dDiv(StackFrame frame) {
        MyLog.command("ddiv");
        OperandStack operandStack = frame.getOperandStack();
        double val1 = operandStack.popDouble();
        double val2 = operandStack.popDouble();
        double mulResult = val1 / val2;
        operandStack.pushDouble(mulResult);
    }

    /**
     * 将栈顶两个 integer 类型数据做取模运算，然后将结果压回栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void iRem(StackFrame frame) {
        MyLog.command("irem");
        OperandStack operandStack = frame.getOperandStack();
        int val1 = operandStack.popInteger();
        int val2 = operandStack.popInteger();
        int remResult = val1 % val2;
        operandStack.pushInteger(remResult);
    }

    /**
     * 将栈顶两个 long 类型数据做取模运算，然后将结果压回栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void lRem(StackFrame frame) {
        MyLog.command("lrem");
        OperandStack operandStack = frame.getOperandStack();
        long val1 = operandStack.popLong();
        long val2 = operandStack.popLong();
        long remResult = val1 % val2;
        operandStack.pushLong(remResult);
    }

    /**
     * 将栈顶两个 float 类型数据做取模运算，然后将结果压回栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void fRem(StackFrame frame) {
        MyLog.command("frem");
        OperandStack operandStack = frame.getOperandStack();
        float val1 = operandStack.popFloat();
        float val2 = operandStack.popFloat();
        float remResult = val1 % val2;
        operandStack.pushFloat(remResult);
    }

    /**
     * 将栈顶两个 double 类型数据做取模运算，然后将结果压回栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void dRem(StackFrame frame) {
        MyLog.command("drem");
        OperandStack operandStack = frame.getOperandStack();
        double val1 = operandStack.popDouble();
        double val2 = operandStack.popDouble();
        double remResult = val1 % val2;
        operandStack.pushDouble(remResult);
    }

    /**
     * 将栈顶的 integer 类型数据做取负，然后将结果压回栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void iNeg(StackFrame frame) {
        MyLog.command("ineg");
        OperandStack operandStack = frame.getOperandStack();
        Integer val = operandStack.popInteger();
        val = -val;
        operandStack.pushInteger(val);
    }

    /**
     * 将栈顶的 long 类型数据做取负，然后将结果压回栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void lNeg(StackFrame frame) {
        MyLog.command("lneg");
        OperandStack operandStack = frame.getOperandStack();
        long val = operandStack.popLong();
        val = -val;
        operandStack.pushLong(val);
    }

    /**
     * 将栈顶的 float 类型数据做取负，然后将结果压回栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void fNeg(StackFrame frame) {
        MyLog.command("fneg");
        OperandStack operandStack = frame.getOperandStack();
        float val = operandStack.popFloat();
        val = -val;
        operandStack.pushFloat(val);
    }

    /**
     * 将栈顶的 double 类型数据做取负，然后将结果压回栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void dNeg(StackFrame frame) {
        MyLog.command("dneg");
        OperandStack operandStack = frame.getOperandStack();
        double val = operandStack.popDouble();
        val = -val;
        operandStack.pushDouble(val);
    }


    /**
     * 将栈顶的 integer 类型数据左移指定位数，然后将结果压回栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void iShL(StackFrame frame) {
        MyLog.command("ishl");
        OperandStack operandStack = frame.getOperandStack();
        int cnt = operandStack.popInteger();
        cnt &= 0x1f;
        int value = operandStack.popInteger();
        int result = value << cnt;
        operandStack.pushInteger(result);
    }

    /**
     * 将栈顶的 long 类型数据左移指定位数，然后将结果压回栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void lShL(StackFrame frame) {
        MyLog.command("lshl");
        OperandStack operandStack = frame.getOperandStack();
        int cnt = operandStack.popInteger();
        cnt &= 0x3f;
        long value = operandStack.popLong();
        long result = value << cnt;
        operandStack.pushLong(result);
    }

    /**
     * 将栈顶的 integer 类型数据右移指定位数，然后将结果压回栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void iShR(StackFrame frame) {
        MyLog.command("ishr");
        OperandStack operandStack = frame.getOperandStack();
        int cnt = operandStack.popInteger();
        cnt &= 0x1f;
        int value = operandStack.popInteger();
        int result = value >> cnt;
        operandStack.pushInteger(result);
    }

    /**
     * 将栈顶的 long 类型数据右移指定位数，然后将结果压回栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void lShR(StackFrame frame) {
        MyLog.command("lshr");
        OperandStack operandStack = frame.getOperandStack();
        int cnt = operandStack.popInteger();
        cnt &= 0x3f;
        long value = operandStack.popLong();
        long result = value >> cnt;
        operandStack.pushLong(result);
    }

    /**
     * 将栈顶的 unsigned integer 类型数据右移指定位数，然后将结果压回栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void iuShR(StackFrame frame) {
        //TODO 可能有BUG
        MyLog.command("iushr");
        OperandStack operandStack = frame.getOperandStack();
        int cnt = operandStack.popInteger();
        cnt &= 0x1f;
        int value = operandStack.popInteger();
        int result = value >> cnt;
        operandStack.pushInteger(result);
    }

    /**
     * 将栈顶的 unsigned long 类型数据右移指定位数，然后将结果压回栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void luShR(StackFrame frame) {
        //TODO 可能有BUG
        MyLog.command("lushr");
        OperandStack operandStack = frame.getOperandStack();
        int cnt = operandStack.popInteger();
        cnt &= 0x3f;
        long value = operandStack.popLong();
        long result = value >> cnt;
        operandStack.pushLong(result);
    }

    /**
     * 将栈顶的两个 integer 类型数据做与操作，然后将结果压回栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void iAnd(StackFrame frame) {
        MyLog.command("iand");
        OperandStack operandStack = frame.getOperandStack();
        int value1 = operandStack.popInteger();
        int value2 = operandStack.popInteger();
        int result = value1 & value2;
        operandStack.pushInteger(result);
    }

    /**
     * 将栈顶的两个 long 类型数据做与操作，然后将结果压回栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void lAnd(StackFrame frame) {
        MyLog.command("land");
        OperandStack operandStack = frame.getOperandStack();
        long value1 = operandStack.popLong();
        long value2 = operandStack.popLong();
        long result = value1 & value2;
        operandStack.pushLong(result);
    }

    /**
     * 将栈顶的两个 integer 类型数据做或操作，然后将结果压回栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void iOr(StackFrame frame) {
        MyLog.command("ior");
        OperandStack operandStack = frame.getOperandStack();
        int value1 = operandStack.popInteger();
        int value2 = operandStack.popInteger();
        int result = value1 | value2;
        operandStack.pushInteger(result);
    }

    /**
     * 将栈顶的两个 long 类型数据做或操作，然后将结果压回栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void lOr(StackFrame frame) {
        MyLog.command("lor");
        OperandStack operandStack = frame.getOperandStack();
        long value1 = operandStack.popLong();
        long value2 = operandStack.popLong();
        long result = value1 | value2;
        operandStack.pushLong(result);
    }

    /**
     * 将栈顶的两个 integer 类型数据做异或操作，然后将结果压回栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void iXOr(StackFrame frame) {
        MyLog.command("ixor");
        OperandStack operandStack = frame.getOperandStack();
        int value1 = operandStack.popInteger();
        int value2 = operandStack.popInteger();
        int result = value1 ^ value2;
        operandStack.pushInteger(result);
    }

    /**
     * 将栈顶的两个 long 类型数据做异或操作，然后将结果压回栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void lXOr(StackFrame frame) {
        MyLog.command("lxor");
        OperandStack operandStack = frame.getOperandStack();
        long value1 = operandStack.popLong();
        long value2 = operandStack.popLong();
        long result = value1 ^ value2;
        operandStack.pushLong(result);
    }

    /**
     * 将指定 integer 类型变量增加指定值（ i++，i--，i+=2 ）
     *
     * @param frame  当前操作栈栈帧
     * @param reader 方法 Code 属性读取器
     */
    private void iInc(StackFrame frame, CodeReader reader) {
        MyLog.command("iinc");
        int index = reader.readByte();
        int constantValue = reader.readByte();
        LocalVariableTable localVariableTable = frame.getLocalVariableTable();
        int value = localVariableTable.getInteger(index);
        value += constantValue;
        localVariableTable.putInteger(index, value);
    }
}
