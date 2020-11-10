package classLoadSystem.analyzer.constant.attribute.attributeImpl.entities.variableInfo;

/**
 * @author 22454
 */
public class VariableInfoLong implements VariableInfo {
    public int tag = VariableItemType.ITEM_LONG;
    @Override
    public int getTag() {
        return tag;
    }
}
