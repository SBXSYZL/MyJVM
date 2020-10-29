package classLoadSystem.analyzer.constant.constantInfoImpl;

import classLoadSystem.analyzer.constant.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoDynamic implements ConstantInfo {
    private static final Integer TAG = ConstantInfoTagEnum.Dynamic_Tag.getTag();
    private String[] bootstrapMethodAttr;
    private ConstantInfoNameAndType[] nameAndTypeIndex;

    @Override
    public int getTag() {
        return TAG == null ?
                DEFAULT_TAG : TAG;
    }
}
