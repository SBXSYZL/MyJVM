package classLoadSystem.analyzer.constant.constantInfoImpl;

import classLoadSystem.analyzer.constant.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoLong implements ConstantInfo {
    private static final Integer TAG = ConstantInfoTagEnum.Long_Tag.getTag();
    private Long bytes;

    public ConstantInfoLong(Long bytes) {
        this.bytes = bytes;
    }

    @Override
    public int getTag() {
        return TAG == null ?
                DEFAULT_TAG : TAG;
    }
}
