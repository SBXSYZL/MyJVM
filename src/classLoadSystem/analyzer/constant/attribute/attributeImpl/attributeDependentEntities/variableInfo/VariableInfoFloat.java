package classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.variableInfo;

import log.MyLog;

/**
 * @author 22454
 */
public class VariableInfoFloat implements VariableInfo {
    public int tag = VariableItemType.ITEM_FLOAT;

    public VariableInfoFloat() {
        MyLog.print(this.toString());
    }

    @Override
    public int getTag() {
        return tag;
    }

    @Override
    public String toString() {
        return "FLOAT\n";
    }
}
