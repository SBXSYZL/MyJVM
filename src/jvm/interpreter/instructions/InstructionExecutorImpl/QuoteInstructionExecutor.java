package jvm.interpreter.instructions.InstructionExecutorImpl;

import jvm.interpreter.instructions.InstructionEnum;

/**
 * @author 22454
 */
public class QuoteInstructionExecutor {
    public static boolean isQuoteInstruction(int operatorCode) {
        return operatorCode >= InstructionEnum.GET_STATIC && operatorCode <= InstructionEnum.MONITOR_EXIT;
    }
}
