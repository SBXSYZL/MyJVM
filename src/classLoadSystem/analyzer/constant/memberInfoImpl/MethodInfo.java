package classLoadSystem.analyzer.constant.memberInfoImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.MemberInfo;

/**
 * @author 22454
 */
public class MethodInfo extends MemberInfo {
    public MethodInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        super(byteCodeFile, constantPool);
    }
}
