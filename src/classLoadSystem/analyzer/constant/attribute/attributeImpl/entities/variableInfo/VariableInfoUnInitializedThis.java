package classLoadSystem.analyzer.constant.attribute.attributeImpl.entities.variableInfo;

/**
 * @author 22454
 */
public class VariableInfoUnInitializedThis implements VariableInfo {
    public int tag = VariableItemType.ITEM_UNINITIALIZED_THIS;
    @Override
    public int getTag() {
        return tag;
    }
}
