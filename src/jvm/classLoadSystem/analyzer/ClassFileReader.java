package jvm.classLoadSystem.analyzer;

import com.sun.istack.internal.NotNull;
import exception.EmClassLoadErr;
import exception.JvmException;
import log.MyLog;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author 22454
 */
public final class ClassFileReader {
    /**
     * 读取文件的字符集
     */
    private static final Charset CHARSETS = StandardCharsets.ISO_8859_1;


    /**
     * 从一份文件/文件夹读取所有class
     *
     * @param path 文件夹完整路径
     * @return 文件夹下的所有文件的十六进制字节码
     */
    @Deprecated
    public static synchronized ConcurrentHashMap<String, byte[]> readClassFromFileAndDirectory(@NotNull String path) throws Exception {
        File file = new File(path);
        LinkedList<File> fileQueue = new LinkedList<>();
        fileQueue.addLast(file);
        //根据文件名存储字节码
        ConcurrentHashMap<String, byte[]> byteCodeMap = new ConcurrentHashMap<>();
        //递归查找出所有class文件
        while (fileQueue.size() > 0) {
            //取出队列第一个元素
            File first = fileQueue.getFirst();
            if (first.isDirectory()) {
                //如果是文件夹，查找出他的所有子文件
                File[] listFiles = first.listFiles();
                if (listFiles != null) {
                    //子文件全部加入文件队列
                    fileQueue.addAll(Arrays.asList(listFiles));
                }
            } else {
                try {
                    URL resource = ClassFileReader.class.getClassLoader().getResource("");
                    if (resource != null) {
                        String rootPath = resource.getPath();
                        String filepath = first.getPath();
                        String packageCombination = filepath.substring(rootPath.length() - 1);
                        packageCombination = packageCombination.replaceAll("\\\\", ".");
                        packageCombination = packageCombination.substring(0, packageCombination.length() - 6);
                        MyLog.info("Load " + packageCombination + "...");
                        //以文本形式读取出class文件的内容，但是使用readline的问题在于会吃掉换行符，
                        // 虽然手动补上了，但是换个系统可能就是未知数
                        String fileTxt = readFileByLine(first);
                        //将读取到的文本转换成16进制，以便转成字节码
                        byte[] bytes = fileTxt.getBytes(CHARSETS);
//                        String[] byteCode = getByteCodeStringArray(fileTxt.getBytes(CHARSETS));
                        String fileName = first.getName();
                        //存入hashMap
//                        byteCodeMap.put(fileName, byteCode);
                        byteCodeMap.put(fileName, bytes);
                        MyLog.success("Load " + packageCombination + " Successfully.");
                    } else {
                        throw new Exception("Can Not Found Resources");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    MyLog.error("Read Class File Error");
                    throw new Exception("Read Class File Error");
                }
            }
            //弹出队列第一个元素
            fileQueue.removeFirst();
        }
        return byteCodeMap;
    }

    /**
     * 按照行读取文件
     *
     * @param file 要读取的文件
     * @return 读取出的文本
     */
    private static synchronized String readFileByLine(@NotNull File file) throws Exception {
        try (InputStream inputStream = new FileInputStream(file);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, CHARSETS))) {
            StringBuilder txt = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                txt.append(line).append("\n");
            }
            bufferedReader.close();
            inputStream.close();
            return txt.toString();
        } catch (IOException e) {
            e.printStackTrace();
            MyLog.error("Read File " + file.getAbsolutePath() + "/" + file.getName() + " Error");
            throw new JvmException(EmClassLoadErr.READ_FILE_ERROR);
        }
    }

    /**
     * 将byte[]转换成字符串数组（实际内容是十六进制）
     *
     * @param source 源字节数组
     * @return 转换后的十六进制数组
     */
    public static synchronized String[] getByteCodeStringArray(@NotNull byte[] source) throws Exception {
        try {
            String[] result = new String[source.length];
            for (int i = 0; i < source.length; i++) {
                byte bt = source[i];
                String s = Integer.toHexString(bt & 0xFF);
                StringBuilder rs = new StringBuilder();
                if (s.length() < 2) {
                    rs.append("0");
                }
                rs.append(s);
                result[i] = rs.toString();
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new JvmException(EmClassLoadErr.FAILED_TO_CONVERT_TO_HEXADECIMAL);
        }
    }

    /**
     * 从Jar包读取类字节码
     *
     * @param absClassName 完整类名，如 java.lang.String
     * @param absJarPath   完整包名 如 D:\Java\JRE\lib\rt.jar
     * @return 字节码字符串数组
     */
    public static synchronized byte[] readClassFromJar(String absClassName, String absJarPath) throws Exception {
        try {
            JarFile jarFile = new JarFile(absJarPath);
            byte[] byteCode = readClassFromJar(absClassName, jarFile);
            if (byteCode != null) {
                return byteCode;
            } else {
                throw new JvmException(EmClassLoadErr.FAILED_TO_READ_BYTECODE_FROM_JAR, "Read Class: [ " + absClassName + " ] From [ " + absJarPath + " ] Error");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new JvmException(EmClassLoadErr.FAILED_TO_READ_BYTECODE_FROM_JAR, "Read Class: [ " + absClassName + " ] From [ " + absJarPath + " ] Error");
        }

    }

    public static synchronized String[] readAllClassNameFromJar(String absJarPath) throws JvmException {
        try {
            List<String> classes = new ArrayList<>();
            JarFile jarFile = new JarFile(absJarPath);
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry jarEntry = entries.nextElement();
                String entryName = jarEntry.getName();
                entryName = entryName.replaceAll("/", ".");
                if (entryName.endsWith(".class")) {
                    classes.add(entryName);
                }
            }
            String[] classesArray = new String[classes.size()];
            for (int i = 0; i < classes.size(); i++) {
                classesArray[i] = classes.get(i);
            }
            return classesArray;
        } catch (Exception e) {
            e.printStackTrace();
            throw new JvmException(EmClassLoadErr.FAILED_TO_READ_CLASS_NAME_FROM_JAR);
        }
    }

    public static synchronized String[] readAllClassNameInJarFromDirectory(String dirPath) throws Exception {
        try {

            File dir = new File(dirPath);
            if (dir.isDirectory()) {
                List<String> classNameList = new ArrayList<>();
                LinkedList<File> fileList = new LinkedList<>();
                fileList.addLast(dir);
                while (fileList.size() > 0) {
                    File first = fileList.removeFirst();
                    if (first.isDirectory()) {
                        File[] files = first.listFiles();
                        if (files != null && files.length > 0) {
                            fileList.addAll(Arrays.asList(files));
                        }
                    } else {
                        if (first.getName().endsWith(".jar")) {
                            String[] classNameFromJar = readAllClassNameFromJar(first.getAbsolutePath());
                            if (classNameFromJar.length > 0) {
                                classNameList.addAll(Arrays.asList(classNameFromJar));
                            }
                        }
                    }
                }

                String[] classNames = new String[classNameList.size()];
                for (int i = 0; i < classNameList.size(); i++) {
                    classNames[i] = classNameList.get(i);
                }
                return classNames;
            } else {
                throw new JvmException(EmClassLoadErr.PATH_ERROR, dirPath + " Not A Directory");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 从Jar包读取字节码
     *
     * @param absClassName 完整类名
     * @param jarFile      jar包文件
     * @return 字节码字符串数组
     */
    private static synchronized byte[] readClassFromJar(String absClassName, JarFile jarFile) throws Exception {
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry jarEntry = entries.nextElement();
            String entryName = jarEntry.getName();
            entryName = entryName.replaceAll("/", ".");

            if (entryName.equals(absClassName + ".class")) {
                return getByteCodeFromJar(jarFile, jarEntry);
            }
        }
        return null;
    }

    /**
     * 从文件中读取字节码
     *
     * @param absClassName 完整类名
     * @param rootPath     根路径
     * @return 字节码字符串数组
     */
    public static synchronized byte[] readClassFromFile(String absClassName, String rootPath) throws Exception {
        try {
            String classFileName = absClassName.replaceAll("\\.", "/").concat(".class");
            rootPath = rootPath.replaceAll("\\\\", "/");
            String absClassPath = rootPath.concat("/").concat(classFileName);
            File file = new File(absClassPath);
            if (file.exists() && file.isFile()) {
                return readClassFromFile(file);
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new JvmException(EmClassLoadErr.CLASS_NOT_FOUND, "Class: [ " + absClassName + " ] Not Found.");
        }
    }

    /**
     * 从文件中读取字节码
     *
     * @param file 文件
     * @return 字节码字符串数组
     */
    public static synchronized byte[] readClassFromFile(@NotNull File file) throws Exception {
        String fileTxt = readFileByLine(file);
        return fileTxt.getBytes(CHARSETS);
    }

    /**
     * 寻找类
     *
     * @param absClassName 完整类名,如 java.lang.String
     * @param absRootPath  完整根目录路径
     * @return 字节码字符串数组
     */
    public static synchronized byte[] readClass(String absClassName, String absRootPath) throws Exception {
        try {
            absClassName = absClassName.replaceAll("/", ".");
            byte[] byteCode = null;
            File file = new File(absRootPath);
            if (!file.exists()) {
                throw new JvmException(EmClassLoadErr.CLASS_NOT_FOUND);
            }
            LinkedList<File> fileQueue = new LinkedList<>();
            fileQueue.addLast(file);
            String realClassFileName = absClassName + ".class";
            while (fileQueue.size() > 0) {
                File first = fileQueue.getFirst();
                //如果是文件夹，把他目录下的所有文件加入到遍历队列
                if (first.isDirectory()) {
                    File[] files = first.listFiles();
                    if (files != null && files.length > 0) {
                        fileQueue.addAll(Arrays.asList(files));
                    }
                } else {
                    if (first.getName().endsWith(".jar")) {
                        String absolutePath = first.getAbsolutePath();
                        JarFile jarFile = new JarFile(absolutePath);
                        byteCode = readClassFromJar(absClassName, jarFile);
                        if (byteCode != null) {
                            return byteCode;
                        }
                    } else if (first.getName().endsWith(".class")) {
                        String absolutePath = first.getAbsolutePath();
                        absolutePath = absolutePath.replaceAll("\\\\", ".");
                        if (absolutePath.endsWith(realClassFileName)) {
                            MyLog.debug("Load ".concat(absClassName).concat(" Successfully."));
                            byteCode = readClassFromFile(first);
                            return byteCode;
                        }
                    }
                }
                fileQueue.removeFirst();
            }
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            throw new JvmException(EmClassLoadErr.CLASS_NOT_FOUND);
        }
    }

    /**
     * 从Jar包获取字节码
     *
     * @param jarFile  jar包
     * @param jarEntry jar包内的压缩文件
     * @return 字节码字符串数组
     */
    private static synchronized byte[] getByteCodeFromJar(JarFile jarFile, JarEntry jarEntry) throws Exception {
        try (InputStream inputStream = jarFile.getInputStream(jarEntry); ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[2048];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.flush();
            byte[] bytes = outputStream.toByteArray();
            outputStream.close();
            inputStream.close();
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
