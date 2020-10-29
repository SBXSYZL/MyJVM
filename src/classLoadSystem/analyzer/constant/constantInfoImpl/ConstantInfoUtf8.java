package classLoadSystem.analyzer.constant.constantInfoImpl;

import classLoadSystem.analyzer.constant.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoUtf8 implements ConstantInfo {
    private static final Integer TAG = ConstantInfoTagEnum.UTF8_Tag.getTag();
    private Integer length;
    private String bytes;

    public ConstantInfoUtf8(Integer length, String bytes) {
        this.length = length;
        this.bytes = bytes;
    }

    public Integer getLength() {
        return length;
    }

    public String getBytes() {
        return bytes;
    }

    @Override
    public int getTag() {
        return TAG == null ?
                DEFAULT_TAG : TAG;
    }
}
