package classLoadSystem.analyzer.constant.constantInfoImpl;

import classLoadSystem.analyzer.constant.ConstantInfo;

/**
 * @author 22454
 */
public class ConstantInfoClass implements ConstantInfo {
    private final static Integer TAG = ConstantInfoTagEnum.Class_Tag.getTag();
    private String index;

    public ConstantInfoClass(String index) {
        this.index = index;
    }

    public String getIndex() {
        return index;
    }

    @Override
    public int getTag() {
        return TAG == null ?
                DEFAULT_TAG : TAG;
    }
}
