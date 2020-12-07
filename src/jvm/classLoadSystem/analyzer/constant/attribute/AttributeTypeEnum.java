package jvm.classLoadSystem.analyzer.constant.attribute;

/**
 * @author 22454
 */
public final class AttributeTypeEnum {
    /**
     * Java代码编译成的字节码指令
     */
    public final static String CODE = "Code";
    /**
     * 由 final 关键字定义的常量值
     */
    public final static String CONSTANT_VALUE = "ConstantValue";
    /**
     * 被声明为 deprecated 的方法和字段
     */
    public final static String DEPRECATED = "Deprecated";
    /**
     * 方法抛出的异常列表
     */
    public final static String EXCEPTIONS = "Exceptions";
    /**
     * 仅当一个类为局部类或者匿名类时才能拥有这个属性，这个属性用于标记这个类所在的外围方法
     */
    public final static String ENCLOSING_METHOD = "EnclosingMethod";
    /**
     * 内部类列表
     */
    public final static String INNER_CLASSES = "InnerClasses";
    /**
     * Java 源码的行号与字节码指令的对应关系
     */
    public final static String LINE_NUMBER_TABLE = "LineNumberTable";
    /**
     * 方法的局表变量描述
     */
    public final static String LOCAL_VARIABLE_TABLE = "LocalVariableTable";
    /**
     * JDK 6 新增属性，供新的类型检查验证器（Type Checker）检查和处理目标方法的局部变量和操作数栈所需要的的类
     * 型是否匹配
     */
    public final static String STACK_MAP_TABLE = "StackMapTable";
    /**
     * JDK 5 新增属性，用于支持泛型情况下的方法签名。在 Java 语言中，任何类、接口、初始化方法或成员的泛型签名
     * 如果包含类型变量（Type Variable）或参数化类型（Parameterized Types），则 Signature 属性会为他记录泛
     * 型签名信息。由于 Java 的泛型采用擦除法实现，为了避免类型信息被擦除后导致签名混乱，需要这个属性记录泛型中
     * 的相关信息
     */
    public final static String SIGNATURE = "Signature";
    /**
     * 记录源文件名称
     */
    public final static String SOURCE_FILE = "SourceFile";
    /**
     * JDK 5 新增属性，用于存储额外的调试信息。譬如在进行 JSP 文件调试时，无法通过 Java 堆栈来定位到JSP文件的行
     * 号，JSR 45 提案为这些非 Java 语言编写，却需要编译成字节码并运行在 Java 虚拟机中的程序提供了一个进行调试的
     * 标准机制，使用该属性就可以用于存储这个标准所加入的调试信息
     */
    public final static String SOURCE_DEBUG_EXTENSION = "SourceDebugExtension";
    /**
     * 标识方法或字段为编译器自动生成
     */
    public final static String SYNTHETIC = "Synthetic";
    /**
     * JDK 5 新增属性，他使用特征签名代替描述符，是为了引入泛型语法之后能描述泛型参数化类型而添加
     */
    public final static String LOCAL_VARIABLE_TYPE_TABLE = "LocalVariableTypeTable";
    /**
     * JDK 5 新增属性，为动态注解提供支持。该属性用于指明那些注解是运行时（实际上运行时就是进行反射调用）可见的
     */
    public final static String RUNTIME_VISIBLE_ANNOTATIONS = "RuntimeVisibleAnnotations";
    /**
     * JDK 5 新增属性，与 RuntimeVisibleAnnotations 属性作用正好相反，用于指明哪些注解试运行时不可见的
     */
    public final static String RUNTIME_INVISIBLE_ANNOTATIONS = "RuntimeInvisibleAnnotations";
    /**
     * JDK 5 新增属性，作用与 RuntimeVisibleAnnotations 属性类似，只不过作用对象为方法参数
     */
    public final static String RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS = "RuntimeVisibleParameterAnnotations";
    /**
     * JDK 5 新增属性，作用与 RuntimeInvisibleAnnotations 属性类似，只不过作用对象为方法参数
     */
    public final static String RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS = "RuntimeInvisibleParameterAnnotations";
    /**
     * JDK 5 新增属性,用于记录注解类元素的默认值
     */
    public final static String ANNOTATION_DEFAULT = "AnnotationDefault";
    /**
     * JDK 7 新增属性，用于保存 invoke dynamic指令引用的引导方法限定符
     */
    public final static String BOOTSTRAP_METHODS = "BootstrapMethods";
    /**
     * JDK 8 新增属性，为实现 JSR 308 中新增的类型注解提供的支持，用于指明哪些类注解试运行时（实际上运行时就是
     * 进行反射调用）可见的
     */
    public final static String RUNTIME_VISIBLE_TYPE_ANNOTATIONS = "RuntimeVisibleTypeAnnotations";
    /**
     * JDK 8 新增属性，为实现 JSR 308 中新增的类型注解提供的支持，与 RuntimeVisibleTypeAnnotations 属性的作
     * 用正好相反，用于指明哪些注解是运行时不可见的
     */
    public final static String RUNTIME_INVISIBLE_TYPE_ANNOTATIONS = "RuntimeInvisibleTypeAnnotations";
    /**
     * JDK 8 中新增的属性，用于支持（编译时加上-parameters参数）将方法名称编译进 Class 文件中，并可运行时获取。
     * 此前要获取方法名称（典型的如 IDE 的代码提示）只能通过 JavaDoc 获取
     */
    public final static String METHOD_PARAMETERS = "MethodParameters";

    //以下属性不再实现

    /**
     * JDK 9 新增属性，用于记录一个 Module 的所有名称以及相关信息（requires、exports、opens、users、provides）
     */
    public final static String MODULE = "Module";
    /**
     * JDK 9 新增属性，用于记录一个模板中所有被 exports 或者 opens 的包
     */
    public final static String MODULE_PACKAGES = "ModulePackages";
    /**
     * JDK 9 新增属性，用于指定一个模板的主类
     */
    public final static String MODULE_MAIN_CLASS = "ModuleMainClass";
    /**
     * JDK 11 新增属性，用于支持嵌套类（ Java 中的内部类）的反射和访问控制的 API ，一个内部类通过该属性得知自
     * 己的宿主类
     */
    public final static String NEST_HOST = "NestHost";
    /**
     * JDK 11 新增属性，用于支持嵌套类（ Java 中的内部类）的反射和访问控制的 API ，一个宿主类通过该属性得知自
     * 己有哪些内部类
     */
    public final static String NEST_MEMBERS = "NestMembers";

}
