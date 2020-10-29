package classLoadSystem.analyzer.constant.constantInfoImpl;

/**
 * @author 22454
 */

public enum ConstantInfoTagEnum {
    //utf-8
    UTF8_Tag(1),
    Integer_Tag(3),
    Float_Tag(4),
    Long_Tag(5),
    Double_Tag(6),
    Class_Tag(7),
    String_Tag(8),
    FieldRef_Tag(9),
    MethodRef_Tag(10),
    InterfaceMethodRef_Tag(11),
    NameAndType_Tag(12),
    MethodHandle_Tag(15),
    MethodType_Tag(16),
    Dynamic_Tag(17),
    InvokeDynamic_Tag(18),
    Module_Tag(19),
    Package_Tag(20);

    private final Integer tag;

    ConstantInfoTagEnum(Integer tag) {
        this.tag = tag;
    }

    public Integer getTag() {
        return tag;
    }
}
