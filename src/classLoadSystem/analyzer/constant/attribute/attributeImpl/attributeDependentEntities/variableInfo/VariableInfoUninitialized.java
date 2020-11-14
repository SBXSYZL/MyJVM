package classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.variableInfo;

/**
 * @author 22454
 */
public class VariableInfoUninitialized implements VariableInfo {
    public int tag = VariableItemType.ITEM_UNINITIALIZED;
    private int offset;

    @Override
    public int getTag() {
        return tag;
    }

    public VariableInfoUninitialized(int offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "UNINITIALIZED Offset: " + offset + "\n";
    }
}
