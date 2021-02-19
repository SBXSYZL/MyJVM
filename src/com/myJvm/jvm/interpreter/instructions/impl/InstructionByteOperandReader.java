package com.myJvm.jvm.interpreter.instructions.impl;

import com.myJvm.jvm.interpreter.CodeReader;

public abstract class InstructionByteOperandReader extends InstructionWithOperands {
    protected byte readOneByte(CodeReader reader) {
        return reader.readByte();
    }

    @Override
    public void readValue(CodeReader reader) {
        this.value = readOneByte(reader);
    }

    /**
     * 此方法，子类重写必须调用父类的实现
     */
    @Override
    public void exec() {
        readValue(reader);
    }









}
