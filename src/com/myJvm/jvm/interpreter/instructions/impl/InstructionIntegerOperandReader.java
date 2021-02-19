package com.myJvm.jvm.interpreter.instructions.impl;

import com.myJvm.jvm.interpreter.CodeReader;

public abstract class InstructionIntegerOperandReader extends InstructionWithOperands {
    protected int readOneInteger(CodeReader reader) {
        return reader.readInteger();
    }

    @Override
    public void readValue(CodeReader reader) {
        this.value = readOneInteger(reader);
    }

    @Override
    public void exec() {
        readValue(reader);
    }
}
