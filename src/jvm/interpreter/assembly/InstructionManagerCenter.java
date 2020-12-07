package jvm.interpreter.assembly;

import jvm.interpreter.CodeReader;
import jvm.runtimeDataArea.threadDependent.StackFrame;

/**
 * @author 22454
 */
public class InstructionManagerCenter {
    /**
     * 运行指令
     */
    public static void invokeInstruction(byte operatorCode, CodeReader codeReader, StackFrame stackTopFrame) {
        //找到对应指令，如果必要，获取操作数

    }

}
