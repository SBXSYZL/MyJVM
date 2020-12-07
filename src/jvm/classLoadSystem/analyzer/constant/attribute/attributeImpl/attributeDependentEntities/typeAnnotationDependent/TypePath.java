package jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.typeAnnotationDependent;

import jvm.classLoadSystem.analyzer.ByteCodeFile;

/**
 * @author 22454
 */
public class TypePath {
    /**
     * U1
     */
    private int pathLength;
    private PathEntry[] path;

    public TypePath(ByteCodeFile byteCodeFile) {
        this.pathLength = byteCodeFile.readOneUint();
        this.path = new PathEntry[pathLength];
        for (int i = 0; i < pathLength; i++) {
            path[i] = new PathEntry(byteCodeFile);
        }
    }

    public static class PathEntry {
        /**
         * U1
         */
        private int typePathKind;
        /**
         * U1
         */
        private int typeArgumentIndex;

        public PathEntry(ByteCodeFile byteCodeFile) {
            this.typePathKind = byteCodeFile.readOneUint();
            this.typeArgumentIndex = byteCodeFile.readOneUint();
        }
    }
}
