package classLoadSystem.analyzer.constant.constantInfoImpl;

import classLoadSystem.analyzer.constant.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoInterfaceMethodRef implements ConstantInfo {
    private static final Integer TAG = ConstantInfoTagEnum.InterfaceMethodRef_Tag.getTag();
    private ConstantInfoClass constantInfoClass;
    private ConstantInfoNameAndType constantInfoNameAndType;

    @Override
    public int getTag() {
        return TAG == null ?
                DEFAULT_TAG : TAG;
    }
}
