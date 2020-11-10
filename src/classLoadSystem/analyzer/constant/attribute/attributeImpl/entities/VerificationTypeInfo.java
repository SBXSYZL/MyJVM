package classLoadSystem.analyzer.constant.attribute.attributeImpl.entities;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.entities.variableInfo.VariableInfo;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.entities.variableInfo.VariableInfoTop;
import classLoadSystem.analyzer.constant.attribute.attributeImpl.entities.variableInfo.VariableItemType;

/**
 * @author 22454
 */
public class VerificationTypeInfo {
    private VariableInfo variableInfo;

    public VerificationTypeInfo(VariableInfo variableInfo) {
        this.variableInfo = variableInfo;
    }

    public static VerificationTypeInfo[] readVerificationTypeInfos(ByteCodeFile byteCodeFile, int infoCount) throws Exception {
        VerificationTypeInfo[] verificationTypeInfos = new VerificationTypeInfo[infoCount];
        for (int i = 0; i < infoCount; i++) {
            verificationTypeInfos[i] = readVerificationTypeInfo(byteCodeFile);
        }
        return verificationTypeInfos;
    }

    public static VerificationTypeInfo readVerificationTypeInfo(ByteCodeFile byteCodeFile) throws Exception {
        int tag = byteCodeFile.readOneUint();
        VariableInfo variableInfo = VariableInfo.createVariableInfo(tag, byteCodeFile);
        return new VerificationTypeInfo(variableInfo);
    }


}
