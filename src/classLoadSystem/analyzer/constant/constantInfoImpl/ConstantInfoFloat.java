package classLoadSystem.analyzer.constant.constantInfoImpl;

import classLoadSystem.analyzer.constant.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoFloat implements ConstantInfo {
    private static final Integer TAG = ConstantInfoTagEnum.Float_Tag.getTag();
    private Float bytes;

    public ConstantInfoFloat(Float bytes) {
        this.bytes = bytes;
    }

    public Float getBytes() {
        return bytes;
    }

    @Override
    public int getTag() {
        return TAG == null ?
                DEFAULT_TAG : TAG;
    }
}
