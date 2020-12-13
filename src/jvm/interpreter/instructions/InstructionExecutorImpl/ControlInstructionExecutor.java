package jvm.interpreter.instructions.InstructionExecutorImpl;

import jvm.interpreter.instructions.InstructionEnum;

/**
 * @author 22454
 */
public class ControlInstructionExecutor {
    public static boolean isControlInstruction(int operatorCode) {
        return operatorCode >= InstructionEnum.GOTO && operatorCode <= InstructionEnum.RETURN;
    }
}
