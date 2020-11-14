package classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.variableInfo;

import log.MyLog;

/**
 * @author 22454
 */
public class VariableInfoInteger implements VariableInfo {
    public int tag = VariableItemType.ITEM_INTEGER;

    public VariableInfoInteger() {
        MyLog.print(this.toString());
    }

    @Override
    public int getTag() {
        return tag;
    }

    @Override
    public String toString() {
        return "INTEGER\n";
    }
}
