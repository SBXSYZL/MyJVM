package com.myJvm.jvm.interpreter.instructions.impl.controlInstructionImpl;

import com.myJvm.jvm.interpreter.CodeReader;
import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.interpreter.instructions.impl.InstructionIntegerOperandReader;
import com.myJvm.log.MyLog;

@MyInstruction
public class TableSwitch extends InstructionIntegerOperandReader {
    private int low;
    private int high;

    @Override
    public void readValue(CodeReader reader) {
        reader.skipPadding();
        super.readValue(reader);
        low = reader.readInteger();
        high = reader.readInteger();
    }

    @Override
    public void exec() {
        MyLog.command("tableswitch");
        int defaultOffset = this.value;
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
}
