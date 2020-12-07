package jvm.classLoadSystem.analyzer.constant.attribute.attributeImpl.attributeDependentEntities.variableInfo;

import log.MyLog;

/**
 * @author 22454
 */
public class VariableInfoTop implements VariableInfo {
    private int tag = VariableItemType.ITEM_TOP;

    public VariableInfoTop() {
//        MyLog.print(this.toString());
    }

    @Override
    public int getTag() {
        return tag;
    }

    @Override
    public String toString() {
        return "TOP\n";
    }
}
