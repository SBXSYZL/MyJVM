package utils;

import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        System.out.println(path);
    }

    public static synchronized void writeToFile(String fileName, String content) {
        BufferedWriter bufferedWriter = null;
        try {
            String realPath = path + fileName;
            File target = checkPath(realPath);
//            String[] split = realPath.split("/");
//            Object[] objects = Arrays.stream(split).filter(string -> !string.isEmpty()).toArray();
//            split = new String[objects.length];
//            int index = 0;
//            for (Object object : objects) {
//                split[index++] = (String) object;
//            }
//            StringBuilder createPath = new StringBuilder();
//            File target = null;
//            for (int i = 0; i < split.length; i++) {
//                if (split[i].length() > 0) {
//                    if (i != 0) {
//                        createPath.append('/').append(split[i]);
//                    } else {
//                        createPath.append(split[i]);
//                    }
//                    File file = new File(createPath.toString());
//                    if (i != split.length - 1) {
//                        if (!file.exists()) {
//                            boolean mkdirs = file.mkdirs();
//                        }
//                    } else {
//                        if (!file.exists()) {
//                            boolean newFile = file.createNewFile();
//                        }
//                        target = file;
//                        break;
//                    }
//                }
//
//            }
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

//    public static void main(String[] args) {
//        URL resource = Thread.currentThread().getContextClassLoader().getResource("");
//        assert resource != null;
//        String path = resource.getPath();
//        System.out.println(path);
//        writeToFile(path + "/classByteCodeShow/test.txt", "txt");
//    }
}
