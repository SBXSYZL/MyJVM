package com.myJvm.jvm.interpreter.instructions;

import com.myJvm.jvm.interpreter.CodeReader;
import com.myJvm.jvm.runtime.threaddependent.StackFrame;

/**
 * @author 22454
 */
public interface InstructionExecutor {
    /**
     * 执行指令
     *
     * @param operatorCode 操作码
     * @param frame        当前栈栈帧
     * @param reader       方法 Code 属性读取器
     */
    void execute(int operatorCode, StackFrame frame, CodeReader reader);


}
