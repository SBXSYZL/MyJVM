package classLoadSystem.analyzer.constant.constantInfoImpl;

import classLoadSystem.analyzer.constant.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoMethodHandle implements ConstantInfo {
    private static final Integer TAG = ConstantInfoTagEnum.MethodHandle_Tag.getTag();
    /**
     * 取值范围 [1,9]
     */
    private Integer referenceKind;

    /**
     * 常量池索引
     */
    private Object referenceIndex;

    @Override
    public int getTag() {
        return TAG == null ?
                DEFAULT_TAG : TAG;
    }
}
