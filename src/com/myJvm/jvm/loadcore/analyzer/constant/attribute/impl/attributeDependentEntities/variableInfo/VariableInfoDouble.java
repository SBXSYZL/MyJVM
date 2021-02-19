package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.variableInfo;

/**
 * @author 22454
 */
public class VariableInfoDouble implements VariableInfo {
    public int tag = VariableItemType.ITEM_DOUBLE;

    public VariableInfoDouble() {
//        MyLog.print(this.toString());
    }

    @Override
    public int getTag() {
        return tag;
    }

    @Override
    public String toString() {
        return "DOUBLE\n";
    }
}
