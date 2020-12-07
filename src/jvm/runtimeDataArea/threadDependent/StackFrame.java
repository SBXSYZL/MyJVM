package jvm.runtimeDataArea.threadDependent;

import jvm.runtimeDataArea.MyThread;
import jvm.runtimeDataArea.shared.heap.info.MyMethod;
import log.MyLog;

/**
 * @author 22454
 */
public class StackFrame {
    /**
     * 局部变量表
     */
    private LocalVariableTable localVariableTable;
    /**
     * 操作数栈
     */
    private OperandStack operandStack;
    private MyThread thread;
    private MyMethod method;
    private int nextPc;

    public StackFrame(MyThread thread, MyMethod method) {
        this.thread = thread;
        this.method = method;
        try {
            this.localVariableTable = new LocalVariableTable(method.getMaxLocals());
            this.operandStack = new OperandStack(method.getMaxStack());
        } catch (Exception e) {
            MyLog.error("Failed To Create Stack Frame.");
            e.printStackTrace();

        }
    }
    /**
     * 动态链接
     * */
    //TODO
    /**
     * 方法返回地址
     * */
    //TODO

    /**
     * 附加信息，i don not known
     */
    //TODO
    public LocalVariableTable getLocalVariableTable() {
        return localVariableTable;
    }

    public OperandStack getOperandStack() {
        return operandStack;
    }

    public MyThread getThread() {
        return thread;
    }

    public MyMethod getMethod() {
        return method;
    }

    public int getNextPc() {
        return nextPc;
    }

    public void setLocalVariableTable(LocalVariableTable localVariableTable) {
        this.localVariableTable = localVariableTable;
    }

    public void setOperandStack(OperandStack operandStack) {
        this.operandStack = operandStack;
    }

    public void setThread(MyThread thread) {
        this.thread = thread;
    }

    public void setMethod(MyMethod method) {
        this.method = method;
    }

    public void setNextPc(int nextPc) {
        this.nextPc = nextPc;
    }
}
