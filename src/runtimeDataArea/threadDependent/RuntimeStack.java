package runtimeDataArea.threadDependent;

import java.util.LinkedList;

/**
 * 虚拟机栈
 *
 * @author 22454
 */
public class RuntimeStack {
    private LinkedList<StackFrame> stack;
    /**
     * 虚拟机栈最大深度
     */

    private static int maxSize = 2048;


    public RuntimeStack() {
        stack = new LinkedList<>();
    }

    public void push(StackFrame frame) throws Exception {
        if (maxSize == stack.size()) {
            throw new Exception("Stack Overflow Error");
        }
        stack.addFirst(frame);
    }

    public StackFrame pop() throws Exception {
        if (stack.size() == 0) {
            throw new Exception("Stack Depth is 0");
        }
        return stack.removeFirst();
    }

    public StackFrame top() throws Exception {
        if (stack.size() == 0) {
            throw new Exception("Stack Depth is 0");
        }
        return stack.getFirst();
    }

    public StackFrame[] getStackFrames() {
        StackFrame[] stackFrames = new StackFrame[stack.size()];
        int i = 0;
        for (StackFrame stackFrame : stack) {
            stackFrames[i++] = stackFrame;
        }
        return stackFrames;
    }

    public static void setMaxSize(int maxSize) {
        RuntimeStack.maxSize = maxSize;
    }
}
