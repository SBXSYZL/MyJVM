package classLoadSystem.analyzer.constant.constantInfoImpl;

import classLoadSystem.analyzer.constant.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoMethodRef implements ConstantInfo {
    private static final Integer TAG = ConstantInfoTagEnum.MethodRef_Tag.getTag();
    private ConstantInfoNameAndType constantInfoNameAndType;

    @Override
    public int getTag() {
        return TAG == null ?
                DEFAULT_TAG : TAG;
    }
}
