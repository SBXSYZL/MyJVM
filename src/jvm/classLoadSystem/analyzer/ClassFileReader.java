package jvm.classLoadSystem.analyzer;

import com.sun.istack.internal.NotNull;
import exception.EmClassLoadErr;
import exception.JvmException;
import log.MyLog;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author 22454
 */
public final class ClassFileReader {
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
            String realClassFileName = absClassName.replace(".", "\\") + ".class";
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
                        if (absolutePath.contains("test")) {
                            System.out.println(absolutePath);
                        }

                        if (absolutePath.endsWith(realClassFileName)) {
                            MyLog.debug("Load ".concat(absClassName).concat(" Successfully."));

                            return getByteCodeFromFile(absolutePath);
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

    private static byte[] getByteCodeFromFile(String absolutePath) throws IOException {
        FileChannel fileChannel = new RandomAccessFile(absolutePath, "r").getChannel();
        MappedByteBuffer byteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size()).load();
        byte[] result = new byte[(int) fileChannel.size()];
        if (byteBuffer.remaining() > 0) {
            byteBuffer.get(result, 0, byteBuffer.remaining());
        }
        return result;
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
