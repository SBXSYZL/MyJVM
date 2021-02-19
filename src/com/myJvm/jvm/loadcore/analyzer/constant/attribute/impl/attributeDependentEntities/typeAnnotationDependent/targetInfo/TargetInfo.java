package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.typeAnnotationDependent.targetInfo;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;

/**
 * @author 22454
 */
public interface TargetInfo {

    /**
     * 创建目标信息
     *
     * @param type         类型
     * @param byteCodeFile 字节码文件
     * @return targetInfo
     * @throws Exception ex
     */
    static TargetInfo createTargetInfo(int type, ByteCodeFile byteCodeFile) throws Exception {
        switch (type) {
            case 0x00:
            case 0x01:
                return new TypeParameterTarget(byteCodeFile);
            case 0x10:
                return new SupertypeTarget(byteCodeFile);
            case 0x11:
            case 0x12:
                return new TypeParameterBoundTarget(byteCodeFile);
            case 0x13:
            case 0x14:
            case 0x15:
                return new EmptyTarget();
            case 0x16:
                return new FormalParameterTarget(byteCodeFile);
            case 0x17:
                return new ThrowsTarget(byteCodeFile);
            case 0x40:
            case 0x41:
                return new LocalVarTarget(byteCodeFile);
            case 0x42:
                return new CatchTarget(byteCodeFile);
            case 0x43:
            case 0x44:
            case 0x45:
            case 0x46:
                return new OffsetTarget(byteCodeFile);
            case 0x47:
            case 0x48:
            case 0x49:
            case 0x4A:
            case 0x4B:
                return new TypeArgumentTarget(byteCodeFile);
            default:
                throw new Exception("Create Target Info Fail");
        }
    }
}
