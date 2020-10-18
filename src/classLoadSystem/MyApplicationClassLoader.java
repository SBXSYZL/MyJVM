package classLoadSystem;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author 22454
 */
public class MyApplicationClassLoader extends MyClassLoader {
    public MyApplicationClassLoader(MyClassLoader parentLoader) {
        this.parent = parentLoader;
    }

    @Override
    public String[] findClass(String absClassName) throws Exception {
        String[] byteCode = this.loadClass(absClassName);
        if (byteCode != null) {
            return byteCode;
        }
        throw new Exception("Class ".concat(absClassName).concat(" Fail."));
    }
}
