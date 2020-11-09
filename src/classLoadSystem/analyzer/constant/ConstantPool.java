package classLoadSystem.analyzer.constant;

import classLoadSystem.analyzer.constant.constantInfoImpl.ConstantInfoInteger;
import classLoadSystem.analyzer.constant.constantInfoImpl.ConstantInfoUtf8;

import java.lang.reflect.Field;

/**
 * @author 22454
 */
public class ConstantPool {
    private ConstantInfo[] pool;
    private final int constantPoolCount;

    public ConstantPool(int constantPoolCount) {
        this.constantPoolCount = constantPoolCount;
        pool = new ConstantInfo[constantPoolCount];
        ConstantInfoInteger constantInfoInteger = new ConstantInfoInteger();
        try {
            Field index = ConstantInfoInteger.class.getDeclaredField("bytes");
            if (index != null) {
                System.out.println("constant pool count load : " + constantPoolCount);
                try {
                    index.setAccessible(true);
                    index.set(constantInfoInteger, this.constantPoolCount);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                pool[0] = constantInfoInteger;
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

    public void put(int index, ConstantInfo constantInfo) {
        pool[index] = constantInfo;
    }

    public String getUtf8(int index) throws Exception {
        try {
            ConstantInfoUtf8 constantInfo = (ConstantInfoUtf8) pool[index];
            return constantInfo == null ? "" : constantInfo.getString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Constant pool does not exists index of " + index + " element");
        }
    }

    public void show() {
        System.out.println("----------------------------------------------------------");
        for (int i = 0; i < this.constantPoolCount; i++) {
            ConstantInfo constantInfo = pool[i];
            if (constantInfo == null) {
                System.out.println(i + " 占位符");
            } else {
                System.out.println(ConstantInfo.getString(constantInfo));
            }

        }
        System.out.println("----------------------------------------------------------");
    }

}
