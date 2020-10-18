package classLoadSystem;

/**
 * @author 22454
 */
public class MyExtensionClassLoader extends MyClassLoader {
    public MyExtensionClassLoader(MyClassLoader parentLoader) {
        this.parent = parentLoader;
    }

    @Override
    public String[] findClass(String absClassName) throws Exception {
        return this.loadClass(absClassName);
    }

    @Override
    public MyClassLoader getParent() {
        return null;
    }
}
