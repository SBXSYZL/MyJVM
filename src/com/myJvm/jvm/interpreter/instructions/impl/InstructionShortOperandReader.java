package com.myJvm.jvm.interpreter.instructions.impl;

import com.myJvm.jvm.interpreter.CodeReader;

public abstract class InstructionShortOperandReader extends InstructionWithOperands {
    protected int readOneShort(CodeReader reader) {
        return reader.readShort();
    }

    @Override
    public void readValue(CodeReader reader) {
        this.value = readOneShort(reader);
    }

    @Override
    public void exec() {
        readValue(reader);
    }


















}
