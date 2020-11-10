package classLoadSystem.analyzer.constant.attribute.attributeImpl.entities.variableInfo;

/**
 * @author 22454
 */
public class VariableInfoNull implements VariableInfo {
    public int tag = VariableItemType.ITEM_NULL;
    @Override
    public int getTag() {
        return tag;
    }
}
