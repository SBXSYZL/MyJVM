package com.myJvm.jvm.runtime;

import com.myJvm.jvm.runtime.shared.heap.info.MyMethod;
import com.myJvm.jvm.runtime.threaddependent.RuntimeStack;
import com.myJvm.jvm.runtime.threaddependent.StackFrame;
import com.myJvm.log.MyLog;

/**
 * @author 22454
 */
public class MyThread {
    private int pc;
    private RuntimeStack stack;

    public MyThread() {
        stack = new RuntimeStack();
    }

    public void pushStackFrame(StackFrame frame) {
        try {
            this.stack.push(frame);
        } catch (Exception e) {
            MyLog.error("Failed To Push Frame To Runtime Stack.");
            e.printStackTrace();
        }
    }

    public StackFrame popStackFrame() {
        try {
            return this.stack.pop();
        } catch (Exception e) {
            MyLog.error("Failed To Pop Frame From Runtime Stack.");
            e.printStackTrace();
        }
        return null;
    }

    public StackFrame getStackTopFrame() {
        try {
            return stack.top();
        } catch (Exception e) {
            MyLog.error("Failed To Get Top Frame From Runtime Stack.");
            e.printStackTrace();
        }
        return null;
    }

    public StackFrame[] getStackFrames() {
        return stack.getStackFrames();
    }

    public boolean stackEmpty() {
        return stack.empty();
    }

    public void clearStack() {
        stack.clear();
    }

    public void setNextPc(int nextPc) {
        this.pc = nextPc;
    }

    public int getPc() {
        return pc;
    }

    public StackFrame createNewStackFrame(MyMethod method) {
        return new StackFrame(this, method);
    }

    @Override
    public String toString() {
        return "MyThread{" +
                "pc=" + pc +
                ", stack=" + stack +
                '}';
    }
}
