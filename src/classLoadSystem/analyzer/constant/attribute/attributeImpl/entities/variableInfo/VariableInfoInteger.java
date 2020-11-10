package classLoadSystem.analyzer.constant.attribute.attributeImpl.entities.variableInfo;

/**
 * @author 22454
 */
public class VariableInfoInteger implements VariableInfo {
    public int tag = VariableItemType.ITEM_INTEGER;
    @Override
    public int getTag() {
        return tag;
    }
}
