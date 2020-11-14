package utils;

import java.io.*;
import java.net.URL;

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
        try {

            File file = new File(path + fileName);
            System.out.println(file.getAbsolutePath());
            if (!file.exists()) {
                boolean newFile = file.createNewFile();
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(content);
            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
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
