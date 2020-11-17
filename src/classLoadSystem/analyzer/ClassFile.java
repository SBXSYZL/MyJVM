package classLoadSystem.analyzer;

import classLoadSystem.analyzer.constant.attribute.AttributeInfo;
import classLoadSystem.analyzer.constant.constantInfo.ConstantInfo;
import classLoadSystem.analyzer.constant.ConstantPool;
import classLoadSystem.analyzer.constant.memberInfo.memberInfoImpl.FieldInfo;
import classLoadSystem.analyzer.constant.memberInfo.memberInfoImpl.MethodInfo;
import log.MyLog;
import utils.FileUtil;


/**
 * @author 22454
 */
public final class ClassFile {
    public static final long MAGIC = 0xCAFEBABE;
    private int minorVersion;
    private int majorVersion;
    private int constantPoolCount;
    private ConstantPool constantPool;
    private int accessFlag;
    private int thisClass;
    private int superClass;
    private int interfaceCount;
    private int[] interfaces;
    private int fieldsCount;
    private FieldInfo[] fields;
    private int methodsCount;
    private MethodInfo[] methods;
    private int attributesCount;
    private AttributeInfo[] attributes;

    public ClassFile(byte[] byteCode) throws Exception {
        init(byteCode);
        showClass();
    }

    /**
     * 初始化数据
     *
     * @param byteCode 字节码数组
     */
    private void init(byte[] byteCode) throws Exception {
        try {

            ByteCodeFile byteCodeFile = new ByteCodeFile(byteCode);
            //如果开头是MAGIC的变量，继续分析，否则抛出异常
            //如果是可编译版本
            if (startWithMagic(byteCodeFile) && isCompilableVersion(byteCodeFile)) {
                //读取 constant pool count
                this.constantPoolCount = byteCodeFile.readTwoUint();
                //创建常量池
                this.constantPool = new ConstantPool(constantPoolCount);

                //读物所有 constant info
                readConstantInfo(byteCodeFile);
                //读取访问标志
                this.accessFlag = byteCodeFile.readTwoUint();
                //读取当前类索引
                this.thisClass = byteCodeFile.readTwoUint();
                String className = constantPool.getClassName(thisClass);
                this.constantPool.setClassName(className);
                //读取父类索引
                this.superClass = byteCodeFile.readTwoUint();
                //读取接口数量
                this.interfaceCount = byteCodeFile.readTwoUint();
                //读取 interfaceCount 个接口的索引
                this.interfaces = readInterfaces(byteCodeFile);
                //读取字段数
                this.fieldsCount = byteCodeFile.readTwoUint();
                //读取字段
                this.fields = readFields(byteCodeFile, constantPool, fieldsCount);
                //读取方法个数
                this.methodsCount = byteCodeFile.readTwoUint();
//                System.out.println("this class file has [ " + methodsCount + " ] functions");
                //读取方法
                this.methods = readMethods(byteCodeFile, constantPool, methodsCount);
                //读取属性数
                this.attributesCount = byteCodeFile.readTwoUint();
                //读取所有属性
                this.attributes = readAttributes(byteCodeFile, constantPool, attributesCount);
            } else {
                throw new Exception("ByteCode File Format Error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("ByteCode File Format Error");
        }
    }

    /**
     * 从字节码中读取 attributesCount 个属性
     *
     * @param byteCodeFile    字节码文件
     * @param constantPool    常量池
     * @param attributesCount 属性个数
     * @return 属性实体
     */
    private AttributeInfo[] readAttributes(ByteCodeFile byteCodeFile, ConstantPool constantPool, int attributesCount) throws Exception {
        return AttributeInfo.readAttributes(byteCodeFile, attributesCount, constantPool);
    }

    /**
     * 读取 methodsCount 个方法
     *
     * @param byteCodeFile 字节码文件
     * @param constantPool 常量池
     * @param methodsCount 方法个数
     * @return 方法实体数组
     */
    private MethodInfo[] readMethods(ByteCodeFile byteCodeFile, ConstantPool constantPool, int methodsCount) throws Exception {
        MyLog.debug("Read Methods Start");
        MethodInfo[] methodInfos = MethodInfo.readMembers(byteCodeFile, constantPool, methodsCount);
        for (MethodInfo methodInfo : methodInfos) {
            MyLog.print(methodInfo + "\n");
        }
        MyLog.success("Read Methods Finish.");
        return methodInfos;
    }

    /**
     * 读取 fieldsCount 个字段
     *
     * @param byteCodeFile 字节码文件
     * @param constantPool 常量池
     * @param fieldsCount  字段数量
     * @return 字段实体数组
     */
    private FieldInfo[] readFields(ByteCodeFile byteCodeFile, ConstantPool constantPool, int fieldsCount) throws Exception {
        MyLog.debug("Read Field Start");
        FieldInfo[] fieldInfos = FieldInfo.readMembers(byteCodeFile, constantPool, fieldsCount);
        for (FieldInfo fieldInfo : fieldInfos) {
            MyLog.print(fieldInfo + "\n");
        }
        MyLog.success("Read Field Finish.");
        return fieldInfos;
    }

    /**
     * 判断字节码开头是否是 CAFEBABE      this function check result is OK!!!
     *
     * @param byteCodeFile 字节码文件
     * @return 是否符合预期
     */
    private boolean startWithMagic(ByteCodeFile byteCodeFile) {
        long magic = byteCodeFile.readFourUint();
        return magic == (MAGIC & 0x0FFFFFFFFL);
    }

    /**
     * 判断当前字节码文件版本是否可编译         this function check result is OK!!!
     *
     * @param byteCodeFile 字节码文件
     * @return 是否可编译
     */
    private boolean isCompilableVersion(ByteCodeFile byteCodeFile) {
        this.minorVersion = byteCodeFile.readTwoUint();
        this.majorVersion = byteCodeFile.readTwoUint();
        final String JAVA_VERSION = "Java Version ";
        switch (majorVersion) {
            case 45:
            case 47:
            case 46:
            case 48:
            case 49:
            case 50:
            case 51:
                return true;
            case 52:
                if (minorVersion == 0) {
                    MyLog.info("Java Version no less than 8");
                    return true;
                }
            default:
                return false;
        }
    }

    /**
     * 读取 constantInfo ，存储到 constantPool        this function check result is OK!!!
     *
     * @param byteCodeFile 字节码文件
     */
    private void readConstantInfo(ByteCodeFile byteCodeFile) throws Exception {
        MyLog.debug("Read Constant Pool Start");
        for (int i = 1; i < constantPoolCount; i++) {
            int tag = byteCodeFile.readOneUint();
            ConstantInfo constantInfo = ConstantInfo.createConstantInfo(tag);
            constantInfo.setInfo(byteCodeFile, constantPool);
            constantPool.put(i, constantInfo);
            //如果是Long，或者Double，长度为 8 ，理论上需要占 2 个空间，这边的实现需要跳过一个空间
            if (tag == ConstantInfo.DOUBLE_TAG || tag == ConstantInfo.LONG_TAG) {
                i++;
            }
        }
        String constantPoolStr = this.constantPool.constantPoolToStringForShow();
        MyLog.print(constantPoolStr);
        MyLog.success("Read Constant Pool Finish.");
    }

    /**
     * 读取 interfaceCount 个 interface 索引     this function check result is OK!!!
     *
     * @param byteCodeFile 字节码文件
     * @return 接口索引数组
     */
    private int[] readInterfaces(ByteCodeFile byteCodeFile) {
        int[] interfaces = new int[this.interfaceCount];
        for (int i = 0; i < this.interfaceCount; i++) {
            interfaces[i] = byteCodeFile.readTwoUint();
        }
        return interfaces;
    }

    private void showClass() throws Exception {
        StringBuilder builder = new StringBuilder("**************************Class ByteCode Show*****************************\n\n");
        //一般信息
        builder.append("minorVersion: ").append(minorVersion).append("\n")
                .append("majorVersion: ").append(majorVersion).append("\n")
                .append("Constant Pool Count: ").append(constantPoolCount).append("\n")
                .append("Access Flag: ").append(accessFlag).append("\n")
                .append("This Class Index: #").append(thisClass).append("   --->   This Class Name: ").append(" <").append(constantPool.getClassName(thisClass)).append(">").append("\n")
                .append("Super Class Index: #").append(superClass).append("   --->   Super Class Name: ").append(" <").append(0 == superClass ? "" : constantPool.getClassName(superClass)).append(">").append("\n")
                .append("Interface Count: ").append(interfaceCount).append("\n")
                .append("Field Count: ").append(fieldsCount).append("\n")
                .append("Method Count: ").append(methodsCount).append("\n")
                .append("Attribute Count: ").append(attributesCount).append("\n");
        //常量池
        builder.append(constantPool.constantPoolToStringForShow());
        //接口
        builder.append("\nInterfaces: \n");
        for (int in : interfaces) {
            builder.append("\t")
                    .append(in)
                    .append("   --->   ")
                    .append(constantPool.getClassName(in))
                    .append("\n");
        }
        builder.append("\n");
        //字段
        builder.append("Fields: \n");
        for (FieldInfo fieldInfo : fields) {
            builder.append(fieldInfo);
        }

        //方法
        builder.append("Methods: \n");
        for (MethodInfo methodInfo : methods) {
            builder.append(methodInfo);
        }
        //属性
        builder.append("Attributes: \n");
        for (AttributeInfo attributeInfo : attributes) {
            builder.append(attributeInfo.toString());
        }

        builder.append("\n").append("*************************************************************************\n");
        MyLog.print(builder.toString());
        //输出到文件，如 [ D:\MyDataArea\JAVA_CODE\MyJVM\out\production\MyJvm\classByteCodeShow\String_ByteCode_Show.txt
        String className = constantPool.getClassName(thisClass);
        String[] split = className.split("/");
        FileUtil.writeToFile(split[split.length - 1] + "_ByteCode_Show.txt", builder.toString());
        FileUtil.appendContentToFile("/Record/record.txt", className);
    }
}
