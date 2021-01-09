package jvm.interpreter.instructions.InstructionExecutorImpl;

import jvm.interpreter.CodeReader;
import jvm.interpreter.instructions.InstructionEnum;
import jvm.interpreter.instructions.InstructionExecutor;
import jvm.runtimeDataArea.MyThread;
import jvm.runtimeDataArea.shared.heap.info.MyObject;
import jvm.runtimeDataArea.threadDependent.StackFrame;
import log.MyLog;

/**
 * @author 22454
 */
public class ControlInstructionExecutor implements InstructionExecutor {
    public boolean isControlInstruction(int operatorCode) {
        return operatorCode >= InstructionEnum.GOTO && operatorCode <= InstructionEnum.RETURN;
    }

    @Override
    public void execute(int operatorCode, StackFrame frame, CodeReader reader) {
        switch (operatorCode) {
            case InstructionEnum.GOTO:
                myGoto(frame, reader);
                break;
            case InstructionEnum.TABLE_SWITCH:
                tableSwitch(frame, reader);
                break;
            case InstructionEnum.LOOK_UP_SWITCH:
                lookupSwitch(frame, reader);
                break;
            case InstructionEnum.I_RETURN:
                iReturn(frame);
                break;
            case InstructionEnum.L_RETURN:
                lReturn(frame);
                break;
            case InstructionEnum.F_RETURN:
                fReturn(frame);
                break;
            case InstructionEnum.D_RETURN:
                dReturn(frame);
                break;
            case InstructionEnum.A_RETURN:
                aReturn(frame);
                break;
            case InstructionEnum.RETURN:
                vReturn(frame);
                break;
            default:
                throw new RuntimeException("Operator Code: " + operatorCode + " Never Found Instruction");
        }
    }

    private void myGoto(StackFrame frame, CodeReader reader) {
        MyLog.command("goto");
        int offset = reader.readShort();
        int pc = frame.getThread().getPc();
        int nextPc = pc + offset;
        frame.setNextPc(nextPc);
    }

    private void jsr(StackFrame frame, CodeReader reader) {
        //TODO JDK7+已被禁用，可不实现
    }

    private void ret(StackFrame frame, CodeReader reader) {
        //TODO JDK7+已被禁用，可不实现
    }

    private void tableSwitch(StackFrame frame, CodeReader reader) {
        MyLog.command("tableswitch");
        reader.skipPadding();
        int defaultOffset = reader.readInteger();
        int low = reader.readInteger();
        int high = reader.readInteger();
        int jumpOffsetCount = high - low + 1;
        int[] jumpOffsets = new int[jumpOffsetCount];
        for (int i = 0; i < jumpOffsetCount; i++) {
            jumpOffsets[i] = reader.readInteger();
        }

        int index = frame.getOperandStack().popInteger();
        int offset;
        if (index >= low && index <= high) {
            offset = jumpOffsets[index - low];
        } else {
            offset = defaultOffset;
        }
        int pc = frame.getThread().getPc();
        int nextPc = pc + offset;
        frame.setNextPc(nextPc);
    }

    private void lookupSwitch(StackFrame frame, CodeReader reader) {
        MyLog.command("lookupswitch");
        reader.skipPadding();
        int defaultOffset = reader.readInteger();
        int nPairs = reader.readInteger();
        int[] matchOffsets = new int[nPairs * 2];
        for (int i = 0; i < matchOffsets.length; i++) {
            matchOffsets[i] = reader.readInteger();
        }

        Integer key = frame.getOperandStack().popInteger();
        for (int i = 0; i < matchOffsets.length; i += 2) {
            if (matchOffsets[i] == key) {
                int offset = matchOffsets[i + 1];
                int pc = frame.getThread().getPc();
                int nextPc = pc + offset;
                frame.setNextPc(nextPc);
            }
        }
        int pc = frame.getThread().getPc();
        int nextPc = pc + defaultOffset;
        frame.setNextPc(nextPc);

    }

    private void iReturn(StackFrame frame) {
        MyLog.command("ireturn");
        MyThread thread = frame.getThread();
        StackFrame currentFrame = thread.popStackFrame();
        StackFrame invokeFrame = thread.getStackTopFrame();
        int value = currentFrame.getOperandStack().popInteger();
        invokeFrame.getOperandStack().pushInteger(value);
    }

    private void lReturn(StackFrame frame) {
        MyLog.command("lreturn");
        MyThread thread = frame.getThread();
        StackFrame currentFrame = thread.popStackFrame();
        StackFrame invokeFrame = thread.getStackTopFrame();
        long value = currentFrame.getOperandStack().popLong();
        invokeFrame.getOperandStack().pushLong(value);
    }

    private void fReturn(StackFrame frame) {
        MyLog.command("freturn");
        MyThread thread = frame.getThread();
        StackFrame currentFrame = thread.popStackFrame();
        StackFrame invokeFrame = thread.getStackTopFrame();
        float value = currentFrame.getOperandStack().popFloat();
        invokeFrame.getOperandStack().pushFloat(value);
    }

    private void dReturn(StackFrame frame) {
        MyLog.command("dreturn");
        MyThread thread = frame.getThread();
        StackFrame currentFrame = thread.popStackFrame();
        StackFrame invokeFrame = thread.getStackTopFrame();
        double value = currentFrame.getOperandStack().popDouble();
        invokeFrame.getOperandStack().pushDouble(value);
    }

    private void aReturn(StackFrame frame) {
        MyLog.command("areturn");
        MyThread thread = frame.getThread();
        StackFrame currentFrame = thread.popStackFrame();
        StackFrame invokeFrame = thread.getStackTopFrame();
        MyObject value = currentFrame.getOperandStack().popRef();
        invokeFrame.getOperandStack().pushRef(value);
    }

    private void vReturn(StackFrame frame) {
        MyLog.command("return");
        frame.getThread().popStackFrame();
    }
}
