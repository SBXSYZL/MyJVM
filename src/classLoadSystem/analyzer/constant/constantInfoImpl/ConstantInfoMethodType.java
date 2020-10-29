package classLoadSystem.analyzer.constant.constantInfoImpl;

import classLoadSystem.analyzer.constant.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoMethodType implements ConstantInfo {
    private static final Integer TAG = ConstantInfoTagEnum.MethodType_Tag.getTag();
    /**
     * 方法的描述符
     */
    private ConstantInfoUtf8 descriptorIndex;

    @Override
    public int getTag() {
        return TAG == null ?
                DEFAULT_TAG : TAG;
    }
}
