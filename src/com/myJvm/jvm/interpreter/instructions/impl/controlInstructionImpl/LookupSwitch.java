package com.myJvm.jvm.interpreter.instructions.impl.controlInstructionImpl;


import com.myJvm.jvm.interpreter.CodeReader;
import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionIntegerOperandReader;
import com.myJvm.log.MyLog;

@MyInstruction
public class LookupSwitch extends InstructionIntegerOperandReader {
    private int nPairs;

    @Override
    public void readValue(CodeReader reader) {
        reader.skipPadding();
        super.readValue(reader);
        this.nPairs = reader.readInteger();
    }

    @Override
    public void exec() {
        MyLog.command("lookupswitch");

        int defaultOffset = this.value;
        int nPairs = this.nPairs;
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
}
