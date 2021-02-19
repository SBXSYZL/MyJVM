package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.typeAnnotationDependent.targetInfo;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;

/**
 * @author 22454
 */
public class LocalVarTarget implements TargetInfo {
    /**
     * U2
     */
    private int tableLength;
    private TableEntry[] table;

    public LocalVarTarget(ByteCodeFile byteCodeFile) {
        this.tableLength = byteCodeFile.readTwoUint();
        table = new TableEntry[tableLength];
        for (int i = 0; i < tableLength; i++) {
            table[i] = new TableEntry(byteCodeFile);
        }
    }

    public static class TableEntry {
        /**
         * U2
         */
        private int startPc;
        /**
         * U2
         */
        private int length;
        /**
         * U2
         */
        private int index;

        public TableEntry(ByteCodeFile byteCodeFile) {
            this.startPc = byteCodeFile.readTwoUint();
            this.length = byteCodeFile.readTwoUint();
            this.index = byteCodeFile.readTwoUint();
        }
    }
}
