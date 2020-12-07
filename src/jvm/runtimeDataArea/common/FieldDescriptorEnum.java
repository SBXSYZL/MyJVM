package jvm.runtimeDataArea.common;

/**
 * @author 22454
 */
public final class FieldDescriptorEnum {

    public static final String BYTE_DESCRIPTOR = "B";
    public static final String CHAR_DESCRIPTOR = "C";
    public static final String DOUBLE_DESCRIPTOR = "D";
    public static final String FLOAT_DESCRIPTOR = "F";
    public static final String INTEGER_DESCRIPTOR = "I";
    public static final String LONG_DESCRIPTOR = "J";
    public static final String SHORT_DESCRIPTOR = "S";
    public static final String BOOLEAN_DESCRIPTOR = "Z";
    public static final String VOID_DESCRIPTOR = "V";
    public static final String OBJECT_DESCRIPTOR = "L";

    public static byte getByteValue(String descriptor) {
        return (byte) descriptor.charAt(0);
    }
}
