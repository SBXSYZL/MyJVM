package classLoadSystem.analyzer.constant.attribute.attributeImpl.entities.variableInfo;

/**
 * @author 22454
 */
public class VariableInfoFloat implements VariableInfo {
    public int tag = VariableItemType.ITEM_FLOAT;
    @Override
    public int getTag() {
        return tag;
    }
}
