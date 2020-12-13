package jvm.interpreter.instructions.InstructionExecutorImpl;

import jvm.interpreter.instructions.InstructionEnum;

/**
 * @author 22454
 */
public class ReservedInstructionExecutor {
    public static boolean isReservedInstruction(int operatorCode) {
        return operatorCode == InstructionEnum.BREAK_POINT ||
                operatorCode == InstructionEnum.IMP_DEP_1 ||
                operatorCode == InstructionEnum.IMP_DEP_2;
    }
}
