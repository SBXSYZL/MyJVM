package classLoadSystem.analyzer.constant.memberInfoImpl;

import classLoadSystem.analyzer.ByteCodeFile;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.MemberInfo;

/**
 * @author 22454
 */
public class FieldInfo extends MemberInfo {
    protected FieldInfo(ByteCodeFile byteCodeFile, ConstantPool constantPool) throws Exception {
        super(byteCodeFile, constantPool);
    }
}
