package com.myJvm.jvm.loadcore.analyzer.constant.attribute.impl.attributeDependentEntities.variableInfo;

/**
 * @author 22454
 */
public class VariableInfoObject implements VariableInfo {
    public int tag = VariableItemType.ITEM_OBJECT;
    private int cPoolIndex;

    @Override
    public int getTag() {
        return tag;
    }

    public VariableInfoObject(int cPoolIndex) {
        this.cPoolIndex = cPoolIndex;
//        MyLog.print(this.toString());
    }

    @Override
    public String toString() {
        return "OBJECT: #" + cPoolIndex + "\n";
    }
}
