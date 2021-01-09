package jvm.interpreter.instructions.InstructionExecutorImpl;

import jvm.interpreter.CodeReader;
import jvm.interpreter.instructions.InstructionEnum;
import jvm.interpreter.instructions.InstructionExecutor;
import jvm.runtimeDataArea.shared.heap.info.MyObject;
import jvm.runtimeDataArea.threadDependent.StackFrame;
import log.MyLog;

/**
 * @author 22454
 */
public class ExtensionInstructionExecutor implements InstructionExecutor {
    public boolean isExtensionInstruction(int operatorCode) {
        return operatorCode >= InstructionEnum.WIDE && operatorCode <= InstructionEnum.JSR_W;
    }


    @Override
    public void execute(int operatorCode, StackFrame frame, CodeReader reader) {
        switch (operatorCode) {
            case InstructionEnum.GOTO_W:
                gotoW(frame, reader);
                break;
            case InstructionEnum.IF_NULL:
                ifNull(frame, reader);
                break;
            case InstructionEnum.IF_NON_NULL:
                ifNonNull(frame, reader);
                break;
            default:
                throw new RuntimeException("Operator Code: " + operatorCode + " Never Found Instruction");
        }
    }

//    private void wide(StackFrame frame, CodeReader reader) {
//        byte opCode = reader.readByte();
//        switch (opCode) {
//            case
//            default:
//                throw new RuntimeException("Can not Exec Instruction: [ Wide ]");
//        }
//    }

    private void gotoW(StackFrame frame, CodeReader reader) {
        MyLog.command("goto_w");
        int offset = reader.readInteger();
        int pc = frame.getThread().getPc();
        int nextPc = pc + offset;
        frame.setNextPc(nextPc);
    }

    private void ifNull(StackFrame frame, CodeReader reader) {
        MyLog.command("ifnull");
        int offset = reader.readShort();
        MyObject ref = frame.getOperandStack().popRef();
        if (null == ref) {
            int pc = frame.getThread().getPc();
            int nextPc = pc + offset;
            frame.setNextPc(nextPc);
        }
    }

    private void ifNonNull(StackFrame frame, CodeReader reader) {
        MyLog.command("ifnonnull");
        int offset = reader.readShort();
        MyObject ref = frame.getOperandStack().popRef();
        if (ref != null) {
            int pc = frame.getThread().getPc();
            int nextPc = pc + offset;
            frame.setNextPc(nextPc);
        }
    }


}
