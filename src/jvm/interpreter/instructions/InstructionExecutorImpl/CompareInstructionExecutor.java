package jvm.interpreter.instructions.InstructionExecutorImpl;

import jvm.interpreter.CodeReader;
import jvm.interpreter.instructions.InstructionEnum;
import jvm.interpreter.instructions.InstructionExecutor;
import jvm.runtimeDataArea.threadDependent.OperandStack;
import jvm.runtimeDataArea.threadDependent.StackFrame;

/**
 * @author 22454
 */
public class CompareInstructionExecutor implements InstructionExecutor {
    public boolean isCompareInstruction(int operatorCode) {
        return operatorCode >= InstructionEnum.L_CMP && operatorCode <= InstructionEnum.IF_A_CMP_N_E;
    }

    @Override
    public void execute(int operatorCode, StackFrame frame, CodeReader reader) {
        switch (operatorCode) {
            case InstructionEnum.L_CMP:
                lCmp(frame);
                break;
            case InstructionEnum.F_CMP_L:
                fCmpL(frame);
                break;
            case InstructionEnum.F_CMP_G:
                fCmpG(frame);
                break;
            case InstructionEnum.D_CMP_L:
                dCmpL(frame);
                break;
            case InstructionEnum.D_CMP_G:
                dCmpG(frame);
                break;
            case InstructionEnum.IF_EQ:
                ifEq(frame, reader);
                break;
            case InstructionEnum.IF_N_E:
                ifNe(frame, reader);
                break;
            case InstructionEnum.IF_L_T:
                ifLt(frame, reader);
                break;
            case InstructionEnum.IF_G_E:
                ifGE(frame, reader);
                break;
            case InstructionEnum.IF_G_T:
                ifGt(frame, reader);
                break;
            case InstructionEnum.IF_L_E:
                ifLE(frame, reader);
                break;
            case InstructionEnum.IF_I_CMP_EQ:
                ifICmpEQ(frame, reader);
                break;
            case InstructionEnum.IF_I_CMP_N_E:
                ifICmpNe(frame, reader);
                break;
            case InstructionEnum.IF_I_CMP_L_T:
                ifICmpLt(frame, reader);
                break;
            case InstructionEnum.IF_I_CMP_G_E:
                ifICmpGe(frame, reader);
                break;
            case InstructionEnum.IF_I_CMP_G_T:
                ifICmpGt(frame, reader);
                break;
            case InstructionEnum.IF_I_CMP_L_E:
                ifICmpLe(frame, reader);
                break;
            case InstructionEnum.IF_A_CMP_EQ:
                ifACmpEq(frame, reader);
                break;
            case InstructionEnum.IF_A_CMP_N_E:
                ifACmpNe(frame, reader);
                break;
            default:
                throw new RuntimeException("Operator Code: " + operatorCode + " Never Found Instruction");
        }
    }

    private void lCmp(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        long value1 = operandStack.popLong();
        long value2 = operandStack.popLong();
        int result;
        if (value1 > value2) {
            result = 1;
        } else if (value1 == value2) {
            result = 0;
        } else {
            result = -1;
        }
        operandStack.pushInteger(result);
    }

    private void fCmpL(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        double value1 = operandStack.popFloat();
        double value2 = operandStack.popFloat();
        if (value1 > value2) {
            operandStack.pushInteger(1);
            return;
        } else if ((value1 - value2) < 1e-6) {
            operandStack.pushInteger(0);
            return;
        } else if (value1 < value2) {
            operandStack.pushInteger(-1);
            return;
        }
        operandStack.pushInteger(-1);
    }

    private void fCmpG(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        double value1 = operandStack.popFloat();
        double value2 = operandStack.popFloat();

        if (value1 > value2) {
            operandStack.pushInteger(1);
            return;
        } else if ((value1 - value2) < 1e-6) {
            operandStack.pushInteger(0);
            return;
        } else if (value1 < value2) {
            operandStack.pushInteger(-1);
            return;
        }
        operandStack.pushInteger(1);
    }

    private void dCmpL(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        double value1 = operandStack.popDouble();
        double value2 = operandStack.popDouble();
        if (value1 > value2) {
            operandStack.pushInteger(1);
            return;
        } else if ((value1 - value2) < 1e-6) {
            operandStack.pushInteger(0);
            return;
        } else if (value1 < value2) {
            operandStack.pushInteger(-1);
            return;
        }
        operandStack.pushInteger(-1);
    }

    private void dCmpG(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        double value1 = operandStack.popDouble();
        double value2 = operandStack.popDouble();

        if (value1 > value2) {
            operandStack.pushInteger(1);
            return;
        } else if ((value1 - value2) < 1e-6) {
            operandStack.pushInteger(0);
            return;
        } else if (value1 < value2) {
            operandStack.pushInteger(-1);
            return;
        }
        operandStack.pushInteger(1);
    }

    private void ifEq(StackFrame frame, CodeReader reader) {
        Integer value = frame.getOperandStack().popInteger();
        int offset = reader.readShort();
        if (value == 0) {
            int pc = frame.getThread().getPc();
            int nextPc = pc + offset;
            frame.setNextPc(nextPc);
        }
    }

    private void ifNe(StackFrame frame, CodeReader reader) {
        Integer value = frame.getOperandStack().popInteger();
        int offset = reader.readShort();
        if (value != 0) {
            int pc = frame.getThread().getPc();
            int nextPc = pc + offset;
            frame.setNextPc(nextPc);
        }
    }

    private void ifLt(StackFrame frame, CodeReader reader) {
        Integer value = frame.getOperandStack().popInteger();
        int offset = reader.readShort();
        if (value < 0) {
            int pc = frame.getThread().getPc();
            int nextPc = pc + offset;
            frame.setNextPc(nextPc);
        }
    }

    private void ifGE(StackFrame frame, CodeReader reader) {
        Integer value = frame.getOperandStack().popInteger();
        int offset = reader.readShort();
        if (value >= 0) {
            int pc = frame.getThread().getPc();
            int nextPc = pc + offset;
            frame.setNextPc(nextPc);
        }
    }

    private void ifGt(StackFrame frame, CodeReader reader) {
        Integer value = frame.getOperandStack().popInteger();
        int offset = reader.readShort();
        if (value > 0) {
            int pc = frame.getThread().getPc();
            int nextPc = pc + offset;
            frame.setNextPc(nextPc);
        }
    }

    private void ifLE(StackFrame frame, CodeReader reader) {
        Integer value = frame.getOperandStack().popInteger();
        int offset = reader.readShort();
        if (value <= 0) {
            int pc = frame.getThread().getPc();
            int nextPc = pc + offset;
            frame.setNextPc(nextPc);
        }
    }

    private void ifICmpEQ(StackFrame frame, CodeReader reader) {
        OperandStack operandStack = frame.getOperandStack();
        int offset = reader.readShort();
        int value1 = operandStack.popInteger();
        int value2 = operandStack.popInteger();
        if (value1 == value2) {
            int pc = frame.getThread().getPc();
            int nextPc = pc + offset;
            frame.setNextPc(nextPc);
        }
    }

    private void ifICmpNe(StackFrame frame, CodeReader reader) {
        OperandStack operandStack = frame.getOperandStack();
        int offset = reader.readShort();
        int value1 = operandStack.popInteger();
        int value2 = operandStack.popInteger();
        if (value1 != value2) {
            int pc = frame.getThread().getPc();
            int nextPc = pc + offset;
            frame.setNextPc(nextPc);
        }
    }

    private void ifICmpLt(StackFrame frame, CodeReader reader) {
        OperandStack operandStack = frame.getOperandStack();
        int offset = reader.readShort();
        int value1 = operandStack.popInteger();
        int value2 = operandStack.popInteger();
        if (value1 < value2) {
            int pc = frame.getThread().getPc();
            int nextPc = pc + offset;
            frame.setNextPc(nextPc);
        }
    }

    private void ifICmpGe(StackFrame frame, CodeReader reader) {
        OperandStack operandStack = frame.getOperandStack();
        int offset = reader.readShort();
        int value1 = operandStack.popInteger();
        int value2 = operandStack.popInteger();
        if (value1 >= value2) {
            int pc = frame.getThread().getPc();
            int nextPc = pc + offset;
            frame.setNextPc(nextPc);
        }
    }

    private void ifICmpGt(StackFrame frame, CodeReader reader) {
        OperandStack operandStack = frame.getOperandStack();
        int offset = reader.readShort();
        int value1 = operandStack.popInteger();
        int value2 = operandStack.popInteger();
        if (value1 > value2) {
            int pc = frame.getThread().getPc();
            int nextPc = pc + offset;
            frame.setNextPc(nextPc);
        }
    }

    private void ifICmpLe(StackFrame frame, CodeReader reader) {
        OperandStack operandStack = frame.getOperandStack();
        int offset = reader.readShort();
        int value1 = operandStack.popInteger();
        int value2 = operandStack.popInteger();
        if (value1 <= value2) {
            int pc = frame.getThread().getPc();
            int nextPc = pc + offset;
            frame.setNextPc(nextPc);
        }
    }

    private void ifACmpEq(StackFrame frame, CodeReader reader) {
        OperandStack operandStack = frame.getOperandStack();
        int offset = reader.readShort();
        Object ref1 = operandStack.popRef();
        Object ref2 = operandStack.popRef();
        if (ref1.equals(ref2)) {
            int pc = frame.getThread().getPc();
            int nextPc = pc + offset;
            frame.setNextPc(nextPc);
        }
    }

    private void ifACmpNe(StackFrame frame, CodeReader reader) {
        OperandStack operandStack = frame.getOperandStack();
        int offset = reader.readShort();
        Object ref1 = operandStack.popRef();
        Object ref2 = operandStack.popRef();
        if (!ref1.equals(ref2)) {
            int pc = frame.getThread().getPc();
            int nextPc = pc + offset;
            frame.setNextPc(nextPc);
        }
    }

}
