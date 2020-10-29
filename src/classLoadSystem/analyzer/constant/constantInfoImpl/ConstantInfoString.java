package classLoadSystem.analyzer.constant.constantInfoImpl;

import classLoadSystem.analyzer.constant.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoString implements ConstantInfo {
    private static final Integer TAG = ConstantInfoTagEnum.String_Tag.getTag();
    private String index;

    public ConstantInfoString(String index) {
        this.index = index;
    }

    @Override
    public int getTag() {
        return TAG == null ?
                DEFAULT_TAG : TAG;
    }
}
