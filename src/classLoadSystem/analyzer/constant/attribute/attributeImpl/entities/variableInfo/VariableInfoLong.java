package classLoadSystem.analyzer.constant.attribute.attributeImpl.entities.variableInfo;

import log.MyLog;

/**
 * @author 22454
 */
public class VariableInfoLong implements VariableInfo {
    public int tag = VariableItemType.ITEM_LONG;

    public VariableInfoLong() {
        MyLog.print(this.toString());
    }

    @Override
    public int getTag() {
        return tag;
    }

    @Override
    public String toString() {
        return "LONG\n";
    }
}
