package classLoadSystem.analyzer.constant.constantInfoImpl;

import classLoadSystem.analyzer.constant.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoNameAndType implements ConstantInfo {
    private static final Integer TAG = ConstantInfoTagEnum.NameAndType_Tag.getTag();

    private String funcName;
    private String funcDesc;

    @Override
    public int getTag() {
        return TAG == null ?
                DEFAULT_TAG : TAG;
    }
}
