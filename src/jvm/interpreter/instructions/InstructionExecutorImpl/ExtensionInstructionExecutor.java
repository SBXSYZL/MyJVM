package jvm.interpreter.instructions.InstructionExecutorImpl;

import jvm.interpreter.instructions.InstructionEnum;

/**
 * @author 22454
 */
public class ExtensionInstructionExecutor {
    public static boolean isExtensionInstruction(int operatorCode) {
        return operatorCode >= InstructionEnum.WIDE && operatorCode <= InstructionEnum.JSR_W;
    }
}
