package jvm.interpreter.instructions.InstructionExecutorImpl;

import jvm.interpreter.CodeReader;
import jvm.interpreter.instructions.InstructionEnum;
import jvm.interpreter.instructions.InstructionExecutor;
import jvm.runtimeDataArea.threadDependent.OperandStack;
import jvm.runtimeDataArea.threadDependent.StackFrame;
import jvm.runtimeDataArea.threadDependent.VariableSlot;
import log.MyLog;

/**
 * 栈指令
 *
 * @author 22454
 */
public class StackInstructionExecutor implements InstructionExecutor {

    public boolean isStackInstruction(int operatorCode) {
        return operatorCode >= InstructionEnum.POP && operatorCode <= InstructionEnum.SWAP;
    }

    @Override
    public void execute(int operatorCode, StackFrame frame, CodeReader reader) {
        switch (operatorCode) {
            case InstructionEnum.POP:
                pop(frame);
                break;
            case InstructionEnum.POP_2:
                pop2(frame);
                break;
            case InstructionEnum.DUP:
                dup(frame);
                break;
            case InstructionEnum.DUP_X_1:
                dupX1(frame);
                break;
            case InstructionEnum.DUP_X_2:
                dupX2(frame);
                break;
            case InstructionEnum.DUP_2:
                dup2(frame);
                break;
            case InstructionEnum.DUP_2_X_1:
                dup2X1(frame);
                break;
            case InstructionEnum.DUP_2_X_2:
                dup2X2(frame);
                break;
            case InstructionEnum.SWAP:
                swap(frame);
                break;
            default:
                throw new RuntimeException("Operator Code: " + operatorCode + " Never Found Instruction");
        }
    }

    /**
     * 将栈顶数值弹出（不能是 long 或者 double ，因为他们需要两个槽，应使用 pop2）
     *
     * @param frame 当前操作栈栈帧
     */
    private void pop(StackFrame frame) {
        MyLog.command("pop");
        frame.getOperandStack().popVariableSlot();
    }

    /**
     * 将栈顶的 long / double 类型数值弹出
     *
     * @param frame 当前操作栈栈帧
     */
    private void pop2(StackFrame frame) {
        MyLog.command("pop2");
        OperandStack operandStack = frame.getOperandStack();
        operandStack.popVariableSlot();
        operandStack.popVariableSlot();
    }

    /**
     * 复制栈顶元素，并压回栈
     *
     * @param frame 当前操作栈栈帧
     */
    private void dup(StackFrame frame) {
        MyLog.command("dup");
        OperandStack operandStack = frame.getOperandStack();
        VariableSlot variableSlot = operandStack.popVariableSlot();
        operandStack.pushVariableSlot(variableSlot);
        operandStack.pushVariableSlot(variableSlot);
    }

    /**
     * 复制栈顶元素，并将其插入到栈顶的两个值（不是槽）下面
     *
     * @param frame 当前操作栈栈帧
     */
    private void dupX1(StackFrame frame) {
        MyLog.command("dup_x1");
        OperandStack operandStack = frame.getOperandStack();
        VariableSlot variableSlot1 = operandStack.popVariableSlot();
        VariableSlot variableSlot2 = operandStack.popVariableSlot();
        operandStack.pushVariableSlot(variableSlot1);
        operandStack.pushVariableSlot(variableSlot2);
        operandStack.pushVariableSlot(variableSlot1);
    }

    /**
     * 复制栈顶元素，并将其插入到栈顶的两个值或三个值（不是槽）下面
     *
     * @param frame 当前操作栈栈帧
     */
    private void dupX2(StackFrame frame) {
        MyLog.command("dup_x2");
        OperandStack operandStack = frame.getOperandStack();
        VariableSlot variableSlot1 = operandStack.popVariableSlot();
        VariableSlot variableSlot2 = operandStack.popVariableSlot();
        VariableSlot variableSlot3 = operandStack.popVariableSlot();
        operandStack.pushVariableSlot(variableSlot1);
        operandStack.pushVariableSlot(variableSlot3);
        operandStack.pushVariableSlot(variableSlot2);
        operandStack.pushVariableSlot(variableSlot1);
    }

    /**
     * 复制栈顶一个 double /long 类型的值，或者两个其他类型的值，并将其压入栈顶
     *
     * @param frame 当前操作栈栈帧
     */
    private void dup2(StackFrame frame) {
        MyLog.command("dup2");
        OperandStack operandStack = frame.getOperandStack();
        VariableSlot variableSlot1 = operandStack.popVariableSlot();
        VariableSlot variableSlot2 = operandStack.popVariableSlot();
        operandStack.pushVariableSlot(variableSlot2);
        operandStack.pushVariableSlot(variableSlot1);
        operandStack.pushVariableSlot(variableSlot2);
        operandStack.pushVariableSlot(variableSlot1);
    }

    /**
     * 复制栈顶的一个或者两个值，并将其插入栈顶那两个或者三个值的下面
     *
     * @param frame 当前操作栈栈帧
     */
    private void dup2X1(StackFrame frame) {
        MyLog.command("dup2_x1");
        OperandStack operandStack = frame.getOperandStack();
        VariableSlot variableSlot1 = operandStack.popVariableSlot();
        VariableSlot variableSlot2 = operandStack.popVariableSlot();
        VariableSlot variableSlot3 = operandStack.popVariableSlot();
        operandStack.pushVariableSlot(variableSlot2);
        operandStack.pushVariableSlot(variableSlot1);
        operandStack.pushVariableSlot(variableSlot3);
        operandStack.pushVariableSlot(variableSlot2);
        operandStack.pushVariableSlot(variableSlot1);
    }

    /**
     * 复制栈顶的一个或者两个值，并将其插入栈顶那两个或者三个或者四个值的下面
     *
     * @param frame 当前操作栈栈帧
     */
    private void dup2X2(StackFrame frame) {
        MyLog.command("dup2_x2");
        OperandStack operandStack = frame.getOperandStack();
        VariableSlot variableSlot1 = operandStack.popVariableSlot();
        VariableSlot variableSlot2 = operandStack.popVariableSlot();
        VariableSlot variableSlot3 = operandStack.popVariableSlot();
        VariableSlot variableSlot4 = operandStack.popVariableSlot();
        operandStack.pushVariableSlot(variableSlot2);
        operandStack.pushVariableSlot(variableSlot1);
        operandStack.pushVariableSlot(variableSlot4);
        operandStack.pushVariableSlot(variableSlot3);
        operandStack.pushVariableSlot(variableSlot2);
        operandStack.pushVariableSlot(variableSlot1);
    }

    /**
     * 将栈顶的两个数值交换（不能是 long / double）
     *
     * @param frame 当前操作栈栈帧
     */
    private void swap(StackFrame frame) {
        MyLog.command("swap");
        OperandStack operandStack = frame.getOperandStack();
        VariableSlot variableSlot1 = operandStack.popVariableSlot();
        VariableSlot variableSlot2 = operandStack.popVariableSlot();
        operandStack.pushVariableSlot(variableSlot1);
        operandStack.pushVariableSlot(variableSlot2);
    }


}
