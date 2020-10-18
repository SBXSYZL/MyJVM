package classLoadSystem;

import java.util.Arrays;

/**
 * @author 22454
 */
public class MyBootstrapClassLoader extends MyClassLoader {

    @Override
    public String[] findClass(String absClassName) throws Exception {
        String[] byteCode;
        try {
            String property = System.getProperty("java.home");
            byteCode = ClassFileReader.findClass(absClassName, property);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Class " + absClassName + " Not Found.");
        }
        return byteCode;
    }
}
