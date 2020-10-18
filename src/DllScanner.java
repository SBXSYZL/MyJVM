import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

/**
 * @author 22454
 */
public class DllScanner {
    public static void scan() {
        try {
            String property = System.getProperty("user.dir");
            System.out.println(property);
            File root = new File(property);

            if (root.isDirectory()) {
                File[] files = root.listFiles();
                List<String> dllFiles = new ArrayList<>();
                if (null != files) {
                    for (File dll : files) {
                        if (dll.getName().endsWith(".dll")) {
                            dllFiles.add(dll.getName());
                        }
                    }
                }
                for (String dllName : dllFiles) {
                    System.out.println("load dll: " + dllName);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
