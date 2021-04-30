package com.myJvm.utils;

import com.myJvm.jvm.interpreter.instructions.InstructionEnum;
import com.myJvm.jvm.runtime.common.AccessPermission;
import com.myJvm.jvm.runtime.shared.heap.info.MyClass;
import com.myJvm.jvm.runtime.shared.heap.info.MyField;
import com.myJvm.jvm.runtime.shared.heap.info.MyMethod;

import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author 22454
 */
public class FileUtil {
    public static String path = null;

    static {
        URL resource = Thread.currentThread().getContextClassLoader().getResource("");
        assert resource != null;
        path = resource.getPath();
        path = path + "/classByteCodeShow/";
    }

    /**
     * 将文本写入文件
     */
    public static synchronized void writeToFile(String fileName, String content) {
        BufferedWriter bufferedWriter = null;
        try {
            String realPath = path + fileName;
            File target = checkPath(realPath);

            assert target != null;
            bufferedWriter = new BufferedWriter(new FileWriter(target));
            bufferedWriter.write(content);
            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    /**
     * 检查路径是否存在，不存在则创建
     */
    private static synchronized File checkPath(String path) throws IOException {
        String[] split = path.split("/");
        Object[] objects = Arrays.stream(split).filter(string -> !string.isEmpty()).toArray();
        split = new String[objects.length];
        int index = 0;
        for (Object object : objects) {
            split[index++] = (String) object;
        }
        StringBuilder createPath = new StringBuilder();
        File target = null;
        for (int i = 0; i < split.length; i++) {
            if (split[i].length() > 0) {
                if (i != 0) {
                    createPath.append('/').append(split[i]);
                } else {
                    createPath.append(split[i]);
                }
                File file = new File(createPath.toString());
                if (i != split.length - 1) {
                    if (!file.exists()) {
                        boolean mkdirs = file.mkdirs();
                    }
                } else {
                    if (!file.exists()) {
                        boolean newFile = file.createNewFile();
                    }
                    target = file;
                    break;
                }
            }

        }
        return target;
    }

    /**
     * 向文件尾部添加文本
     */
    public static synchronized void appendContentToFile(String fileName, String msg) {
        BufferedWriter bufferedWriter = null;
        try {
            String realPath = path + fileName;
            File target = checkPath(realPath);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target, true)));
            bufferedWriter.write(msg + "\n\r");
            bufferedWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public static synchronized void printAssembly(MyClass myClass) {
        Field[] enumInstruction = InstructionEnum.class.getFields();
        InstructionEnum instructionEnum = new InstructionEnum();
        HashMap<Byte, String> instructionMap = new HashMap<>();
        for (Field field : enumInstruction) {
            try {
                Object o = field.get(instructionEnum);
                Byte by = null;
                if (o instanceof Byte) {
                    by = (Byte) o;
                } else if (o instanceof Integer) {
                    by = ((Integer) o).byteValue();
                }
                if (by != null) {
                    instructionMap.put(by, field.getName());
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        StringBuilder builder = new StringBuilder();
        //类头
        builder.append(AccessPermission.getClassAccessFlagString(myClass.getAccessFlag()))
                .append(" ")
                .append(myClass.getClassName())
                .append("{\n");
        //类字段
        MyField[] fields = myClass.getFields();
        for (MyField field : fields) {
            builder.append("\t")
                    .append(AccessPermission.getFieldAccessFlagString(field.getAccessFlag()));
            String descriptor = parseDescriptor(field.getDescriptor());
            builder.append(descriptor)
                    .append(" ")
                    .append(field.getName())
                    .append(";\n");
        }
        builder.append("\n");
        MyMethod[] methods = myClass.getMethods();
        //类方法
        for (MyMethod method : methods) {
            String methodDescriptor = method.getDescriptor();
            String returnTypeDescriptor = methodDescriptor.substring(methodDescriptor.indexOf(")") + 1);
            String paramTypes = methodDescriptor.substring(methodDescriptor.indexOf("(") + 1,
                    methodDescriptor.indexOf(")") - methodDescriptor.indexOf("("));
            List<String> params = new ArrayList<>();
            for (int i = 0; i < paramTypes.length(); i++) {
                StringBuilder s = new StringBuilder();
                if (paramTypes.charAt(i) == '[') {
                    s.append(paramTypes.charAt(i));
                    if (paramTypes.charAt(i + 1) == 'L') {
                        for (int j = i + 1; j < paramTypes.length(); j++) {
                            s.append(paramTypes.charAt(j));
                            if (paramTypes.charAt(j) == ';') {
                                i = j + 1;
                                break;
                            }
                        }
                    } else {
                        s.append(paramTypes.charAt(i + 1));
                    }
                } else {
                    if (paramTypes.charAt(i) == 'L') {
                        s.append(paramTypes.charAt(i));
                        for (int j = i + 1; j < paramTypes.length(); j++) {
                            s.append(paramTypes.charAt(j));
                            if (paramTypes.charAt(j) == ';') {
                                i = j + 1;
                                break;
                            }
                        }
                    } else {
                        s.append(paramTypes.charAt(i));
                    }
                }
                params.add(s.toString());
            }
            Object[] paramTypeDescriptors = params.toArray();
            builder.append("\t")
                    .append(AccessPermission.getFieldAccessFlagString(method.getAccessFlag()))
                    .append(" ");

            if ("<init>".equals(method.getName())) {
                builder.append(myClass.getClassName().replace("/", "."));
            } else {
                builder.append(parseDescriptor(returnTypeDescriptor))
                        .append(" ");
                builder.append(method.getName());
            }
            builder.append(" ( ");
            for (int i = 0; i < paramTypeDescriptors.length; i++) {
                if (((String) paramTypeDescriptors[i]).trim().length() > 0) {
                    builder.append(parseDescriptor((String) paramTypeDescriptors[i]));
                    if (i != paramTypeDescriptors.length - 1) {
                        builder.append(",");
                    }
                }
            }
            builder.append(" ) {\n\t\tCode:\n");
            byte[] code = method.getCode();
            if (code != null) {
                for (int i = 0; i < code.length; i++) {
                    byte b = code[i];
                    String realInstruction = instructionMap.get(b);
                    if (realInstruction != null) {
                        builder.append("\t\t\t").append(i).append(":   ").append(realInstruction.toLowerCase());
                        if (i != code.length - 1) {
                            builder.append("\n");
                        }
                    }


                }
            }

            builder.append("\n\t}\n\n");
        }
        builder.append("\n}");
        BufferedWriter bufferedWriter = null;
        try {
            String realPath = path + myClass.getClass() + ".txt";
            File target = checkPath(realPath);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target)));
            bufferedWriter.write(builder.toString() + "\n\r");
            bufferedWriter.flush();
            System.out.println(builder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static synchronized String parseDescriptor(String descriptor) {
        StringBuilder builder = new StringBuilder();
        boolean isArray = false;
        if (descriptor.getBytes()[0] == '[') {
            isArray = true;
            descriptor = descriptor.substring(1);
        }
        String type = "";
        switch (descriptor) {
            case "B":
                type = "byte";
                break;
            case "C":
                type = "char";
                break;
            case "D":
                type = "double";
                break;
            case "F":
                type = "float";
                break;
            case "I":
                type = "int";
                break;
            case "J":
                type = "long";
                break;
            case "S":
                type = "short";
                break;
            case "Z":
                type = "boolean";
                break;
            case "V":
                type = "void";
                break;
            default:
                if (descriptor.startsWith("L")) {
                    type = descriptor.substring(1, descriptor.length() - 1).replace("/", ".");
                }
                break;
        }
        builder.append(type);
        if (isArray) {
            builder.append("[]");
        }
        return builder.toString();
    }

}
