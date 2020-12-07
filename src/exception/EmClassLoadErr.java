package exception;

/**
 * @author 22454
 */
public enum EmClassLoadErr implements CommonError {
    /**
     * 类未找到
     */
    CLASS_NOT_FOUND(2001, "Class Not Found"),
    /**
     * 类加载失败
     */
    FAIL_TO_LOAD_CLASS(2002, "Failed To Load Class "),
    /**
     * 字节码文件格式错误
     */
    BYTECODE_FILE_FORMAT_ERROR(2003, "Bytecode File Format Error"),
    /**
     * 读取文件错误
     */
    READ_FILE_ERROR(2004, "Read File Error"),
    /**
     * 转十六进制失败
     */
    FAILED_TO_CONVERT_TO_HEXADECIMAL(2005, "Failed To Convert To Hexadecimal"),
    /**
     * 从jar包读取字节码失败
     */
    FAILED_TO_READ_BYTECODE_FROM_JAR(2006, "Failed To Read Bytecode From Jar"),
    /**
     * 从jar包中读取类名失败
     */
    FAILED_TO_READ_CLASS_NAME_FROM_JAR(2007, "Failed To Read Class Name From Jar"),
    /**
     * 路径错误
     */
    PATH_ERROR(2008, "Path Error"),
    /**
     * 创建 Value 失败
     */
    FAILED_TO_CREATE_VALUE(3001, "Failed To Create Value"),
    /**
     * 创建 Frame 失败
     */
    FAILED_TO_CREATE_FRAME(3002, "Failed To Create Frame"),
    /**
     * 创建 Variable Info 失败
     */
    FAILED_TO_CREATE_VARIABLE_INFO(3003, "Failed To Create Variable Info"),
    /**
     * Jvm 版本错误
     */
    JVM_VERSION_ERROR(3004, "Jvm Version Error"),
    /**
     * 读取 Attribute Info 失败
     */
    FAILED_TO_READ_ATTRIBUTE(3005, "Fail To Read Attribute"),
    /**
     * 从常量池读取常量失败
     */
    FAILED_TO_READ_CONSTANT_INFO_FRO_CONSTANT_POOL(3006, "Failed To Read Constant Info From Constant Pool"),
    /**
     * 查找常量的 TAG 失败
     */
    FAILED_TO_FIND_CONSTANT_INFO_TAG_NAME(3007, "Failed To Find Constant Info Tag Name"),
    /**
     * 常量信息转换错误
     */
    CONSTANT_INFO_TYPE_CAST_ERROR(3008, "Constant Info Type Cast Error"),
    CONSTANT_POOL_DO_NOT_EXISTS_THIS_ELEMENT(3009, "Constant Pool Do Not Exists This Element"),
    /**
     * 获取 source file attribute 失败
     */
    FAILed_TO_GET_SOURCE_FILE_ATTRIBUTE(3010, "Failed To Get Source File Attribute"),
    FAILED_TO_LOAD_CLASS(3011, "Failed To Load Class");
    private final int errCode;
    private String msg;

    EmClassLoadErr(int errCode, String msg) {
        this.errCode = errCode;
        this.msg = msg;
    }

    @Override
    public int getErrCode() {
        return errCode;
    }

    @Override
    public String getErrMsg() {
        return msg;
    }

    @Override
    public void setErrMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "EmClassLoadErr{" +
                "errCode=" + errCode +
                ", msg='" + msg + '\'' +
                '}';
    }
}
