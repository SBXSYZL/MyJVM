package jvm.interpreter.instructions.InstructionExecutorImpl;

import jvm.interpreter.CodeReader;
import jvm.interpreter.instructions.InstructionEnum;
import jvm.interpreter.instructions.InstructionExecutor;
import jvm.runtimeDataArea.threadDependent.OperandStack;
import jvm.runtimeDataArea.threadDependent.StackFrame;

/**
 * @author 22454
 */
public class ConversionInstructionExecutor implements InstructionExecutor {
    public boolean isConversionInstruction(int operatorCode) {
        return operatorCode >= InstructionEnum.I_2_L && operatorCode <= InstructionEnum.I_2_S;
    }

    @Override
    public void execute(int operatorCode, StackFrame frame, CodeReader reader) {
        switch (operatorCode) {
            case InstructionEnum.I_2_L:
                i2l(frame);
                break;
            case InstructionEnum.I_2_F:
                i2f(frame);
                break;
            case InstructionEnum.I_2_D:
                i2d(frame);
                break;
            case InstructionEnum.L_2_I:
                l2i(frame);
                break;
            case InstructionEnum.L_2_F:
                l2f(frame);
                break;
            case InstructionEnum.L_2_D:
                l2d(frame);
                break;
            case InstructionEnum.F_2_I:
                f2i(frame);
                break;
            case InstructionEnum.F_2_L:
                f2l(frame);
                break;
            case InstructionEnum.F_2_D:
                f2d(frame);
                break;
            case InstructionEnum.D_2_I:
                d2i(frame);
                break;
            case InstructionEnum.D_2_L:
                d2l(frame);
                break;
            case InstructionEnum.D_2_F:
                d2f(frame);
                break;
            case InstructionEnum.I_2_B:
                i2b(frame);
                break;
            case InstructionEnum.I_2_C:
                i2c(frame);
                break;
            case InstructionEnum.I_2_S:
                i2s(frame);
                break;
            default:
                throw new RuntimeException("Operator Code: " + operatorCode + " Never Found Instruction");
        }
    }

    private void i2l(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        long integer = operandStack.popInteger();
        operandStack.pushLong(integer);
    }

    private void i2f(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        float integer = operandStack.popInteger();
        operandStack.pushFloat(integer);
    }

    private void i2d(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        double integer = operandStack.popInteger();
        operandStack.pushDouble(integer);
    }

    private void l2i(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        long value = operandStack.popLong();
        int result = (int) value;
        operandStack.pushInteger(result);
    }

    private void l2f(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        long value = operandStack.popLong();
        float result = (float) value;
        operandStack.pushFloat(result);
    }

    private void l2d(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        long value = operandStack.popLong();
        double result = (double) value;
        operandStack.pushDouble(result);
    }

    private void f2i(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        float value = operandStack.popFloat();
        int result = (int) value;
        operandStack.pushInteger(result);
    }

    private void f2l(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        float value = operandStack.popFloat();
        long result = (long) value;
        operandStack.pushLong(result);
    }

    private void f2d(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        float value = operandStack.popFloat();
        operandStack.pushDouble((double) value);
    }

    private void d2i(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        double value = operandStack.popDouble();
        int result = (int) value;
        operandStack.pushInteger(result);
    }

    private void d2l(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        double value = operandStack.popDouble();
        long result = (long) value;
        operandStack.pushLong(result);
    }

    private void d2f(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        double value = operandStack.popDouble();
        float result = (float) value;
        operandStack.pushFloat(result);
    }

    private void i2b(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int value = operandStack.popInteger();
        int result = (byte) value;
        operandStack.pushInteger(result);
    }

    private void i2c(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int value = operandStack.popInteger();
        int result = (char) value;
        operandStack.pushInteger(result);
    }

    private void i2s(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int value = operandStack.popInteger();
        int result = (short) value;
        operandStack.pushInteger(result);
    }
}
