package classLoadSystem.analyzer.constant.constantInfoImpl;

import classLoadSystem.analyzer.constant.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoInteger implements ConstantInfo {
    private static final Integer TAG = ConstantInfoTagEnum.Integer_Tag.getTag();
    private Integer bytes;

    public ConstantInfoInteger(Integer bytes) {
        this.bytes = bytes;
    }

    public Integer getBytes() {
        return bytes;
    }

    @Override
    public int getTag() {
        return TAG == null ?
                DEFAULT_TAG : TAG;
    }
}
