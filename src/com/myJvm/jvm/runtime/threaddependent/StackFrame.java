package com.myJvm.jvm.runtime.threaddependent;

import com.myJvm.jvm.runtime.MyThread;
import com.myJvm.jvm.runtime.shared.heap.info.MyMethod;
import com.myJvm.log.MyLog;

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
    /**
     * 当前线程
     */
    private MyThread thread;
    /**
     * 当前方法
     */
    private MyMethod method;
    /**
     * 下一个PC
     */
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

    public void revertNextPc() {
        this.nextPc = this.thread.getPc();
    }

    @Override
    public String toString() {
        return "\nStackFrame{" +
                "localVariableTable=" + localVariableTable +
                ", operandStack=" + operandStack +
                ", thread=" + thread +
                ", method=" + method +
                ", nextPc=" + nextPc +
                '}';
    }
}
