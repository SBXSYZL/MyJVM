package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.stackMapTableDependentFrame.frameDependent;

import com.myJvm.jvm.loadcore.analyzer.ByteCodeFile;
import com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.variableInfo.VariableInfo;

/**
 * @author 22454
 */
public class VerificationTypeInfo {
    private VariableInfo variableInfo;

    public VerificationTypeInfo(VariableInfo variableInfo) {
        this.variableInfo = variableInfo;
    }

    public static VerificationTypeInfo[] readVerificationTypeInfos(ByteCodeFile byteCodeFile, int infoCount) throws Exception {
//        MyLog.debug("VerificationTypeInfo count: " + infoCount);
        VerificationTypeInfo[] verificationTypeInfos = new VerificationTypeInfo[infoCount];
        for (int i = 0; i < infoCount; i++) {
            verificationTypeInfos[i] = readVerificationTypeInfo(byteCodeFile);
        }
//        MyLog.debug("VerificationTypeInfo load finish");
        return verificationTypeInfos;
    }

    public static VerificationTypeInfo readVerificationTypeInfo(ByteCodeFile byteCodeFile) throws Exception {
        int tag = byteCodeFile.readOneUint();
        VariableInfo variableInfo = VariableInfo.createVariableInfo(tag, byteCodeFile);
        return new VerificationTypeInfo(variableInfo);
    }

    @Override
    public String toString() {
        return variableInfo.toString();
    }
}
