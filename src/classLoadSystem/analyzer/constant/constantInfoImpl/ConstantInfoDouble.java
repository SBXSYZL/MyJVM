package classLoadSystem.analyzer.constant.constantInfoImpl;

import classLoadSystem.analyzer.constant.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoDouble implements ConstantInfo {
    private static final Integer TAG = ConstantInfoTagEnum.Double_Tag.getTag();
    private Double bytes;

    public ConstantInfoDouble(Double bytes) {
        this.bytes = bytes;
    }

    public Double getBytes() {
        return bytes;
    }

    @Override
    public int getTag() {
        return TAG == null ?
                DEFAULT_TAG : TAG;
    }
}
