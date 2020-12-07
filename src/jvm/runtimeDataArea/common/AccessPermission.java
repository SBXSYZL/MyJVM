package jvm.runtimeDataArea.common;

/**
 * @author 22454
 */
public final class AccessPermission {
    public static final int ACC_PUBLIC = 0x0001;
    public static final int ACC_PRIVATE = 0x0002;
    public static final int ACC_PROTECTED = 0x0004;
    public static final int ACC_STATIC = 0x0008;
    public static final int ACC_FINAL = 0x0010;
    public static final int ACC_SYNTHETIC = 0x1000;
    public static final int ACC_VOLATILE = 0x0040;
    public static final int ACC_TRANSIENT = 0x0080;
    public static final int ACC_ENUM = 0x4000;
    public static final int ACC_SYNCHRONIZED = 0x0020;
    public static final int ACC_BRIDGE = 0x00040;
    public static final int ACC_VARARGS = 0x0080;
    public static final int ACC_NATIVE = 0x0100;
    public static final int ACC_ABSTRACT = 0x0400;
    public static final int ACC_STRICT = 0x0800;
    public static final int ACC_SUPER = 0x0020;
    public static final int ACC_INTERFACE = 0x0200;
    public static final int ACC_ANNOTATION = 0x2000;
    public static final int ACC_MODULE = 0x8000;

    /**
     * 根据 flag值获取响应访问权限字符串
     *
     * @param accessFlag 访问 flag
     * @return 访问权限字符串
     */
    public static String getAccessFlagString(int accessFlag) {
        StringBuilder builder = new StringBuilder();
        if (isPublic(accessFlag)) {
            builder.append("public ");
        }
        if (isPrivate(accessFlag)) {
            builder.append("private ");
        }
        if (isProtected(accessFlag)) {
            builder.append("protected ");
        }
        if (isStatic(accessFlag)) {
            builder.append("static ");
        }
        if (isFinal(accessFlag)) {
            builder.append("final ");
        }
        if (isSynthetic(accessFlag)) {
            builder.append("synthetic ");
        }
        if (isVolatile(accessFlag)) {
            builder.append("volatile ");
        }
        if (isTransient(accessFlag)) {
            builder.append("transient ");
        }
        if (isEnum(accessFlag)) {
            builder.append("enum ");
        }

        if (isSynchronized(accessFlag)) {
            builder.append("synchronized ");
        }
        if (isBridge(accessFlag)) {
            builder.append("bridge ");
        }
        if (isVarargs(accessFlag)) {
            builder.append("varargs ");
        }
        if (isNative(accessFlag)) {
            builder.append("native ");
        }
        if (isAbstract(accessFlag)) {
            builder.append("abstract ");
        }
        if (isStrict(accessFlag)) {
            builder.append("strict ");
        }
        if (isSuper(accessFlag)) {
            builder.append("super");
        }
        if (isInterface(accessFlag)) {
            builder.append("interface");
        }
        if (isAnnotation(accessFlag)) {
            builder.append("annotation");
        }
        if (isModule(accessFlag)) {
            builder.append("module");
        }

        return builder.toString();

    }


    public static boolean isPublic(int accessFlag) {
        return (accessFlag & ACC_PUBLIC) == ACC_PUBLIC;
    }

    public static boolean isPrivate(int accessFlag) {
        return (accessFlag & ACC_PRIVATE) == ACC_PRIVATE;
    }

    public static boolean isProtected(int accessFlag) {
        return (accessFlag & ACC_PROTECTED) == ACC_PROTECTED;
    }

    public static boolean isStatic(int accessFlag) {
        return (accessFlag & ACC_STATIC) == ACC_STATIC;
    }

    public static boolean isFinal(int accessFlag) {
        return (accessFlag & ACC_FINAL) == ACC_FINAL;
    }

    public static boolean isSynthetic(int accessFlag) {
        return (accessFlag & ACC_SYNTHETIC) == ACC_SYNTHETIC;
    }

    public static boolean isVolatile(int accessFlag) {
        return (accessFlag & ACC_VOLATILE) == ACC_VOLATILE;
    }

    public static boolean isTransient(int accessFlag) {
        return (accessFlag & ACC_TRANSIENT) == ACC_TRANSIENT;
    }

    public static boolean isEnum(int accessFlag) {
        return (accessFlag & ACC_ENUM) == ACC_ENUM;
    }

    public static boolean isSynchronized(int accessFlag) {
        return (accessFlag & ACC_SYNCHRONIZED) == ACC_SYNCHRONIZED;
    }

    public static boolean isBridge(int accessFlag) {
        return (accessFlag & ACC_BRIDGE) == ACC_BRIDGE;
    }

    public static boolean isVarargs(int accessFlag) {
        return (accessFlag & ACC_VARARGS) == ACC_VARARGS;
    }

    public static boolean isNative(int accessFlag) {
        return (accessFlag & ACC_NATIVE) == ACC_NATIVE;
    }

    public static boolean isAbstract(int accessFlag) {
        return (accessFlag & ACC_ABSTRACT) == ACC_ABSTRACT;
    }

    public static boolean isStrict(int accessFlag) {
        return (accessFlag & ACC_STRICT) == ACC_STRICT;
    }

    public static boolean isSuper(int accessFlag) {
        return (accessFlag & ACC_SUPER) == ACC_SUPER;
    }

    public static boolean isInterface(int accessFlag) {
        return (accessFlag & ACC_INTERFACE) == ACC_INTERFACE;
    }

    public static boolean isAnnotation(int accessFlag) {
        return (accessFlag & ACC_ANNOTATION) == ACC_ANNOTATION;
    }

    public static boolean isModule(int accessFlag) {
        return (accessFlag & ACC_MODULE) == ACC_MODULE;
    }

}
