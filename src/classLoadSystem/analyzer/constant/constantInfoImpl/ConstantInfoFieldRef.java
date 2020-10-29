package classLoadSystem.analyzer.constant.constantInfoImpl;

import classLoadSystem.analyzer.constant.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoFieldRef implements ConstantInfo {
    private static final Integer TAG = ConstantInfoTagEnum.FieldRef_Tag.getTag();
    /**
     * 声明的类的索引
     */
    private ConstantInfoClass constantInfoClass;
    /**
     * 字段描述符
     */
    private ConstantInfoNameAndType constantInfoNameAndType;



    @Override
    public int getTag() {
        return TAG == null ?
                DEFAULT_TAG : TAG;
    }
}
