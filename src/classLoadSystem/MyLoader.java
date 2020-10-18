package classLoadSystem;

/**
 * @author 22454
 */
public class MyLoader implements Loader {
    private final MyBootstrapClassLoader bootstrapClassLoader;
    private final MyExtensionClassLoader extClassLoader;
    private final MyApplicationClassLoader applicationClassLoader;

    public MyLoader() throws Exception {
        bootstrapClassLoader = new MyBootstrapClassLoader();
        extClassLoader = new MyExtensionClassLoader(bootstrapClassLoader);
        applicationClassLoader = new MyApplicationClassLoader(extClassLoader);
        applicationClassLoader.findClass("classLoadSystem.ClassFileReader");
    }

    @Override
    public void findClass(String absClassName) throws Exception {
        applicationClassLoader.findClass(absClassName);
    }

    public MyBootstrapClassLoader getBootstrapClassLoader() {
        return bootstrapClassLoader;
    }

    public MyExtensionClassLoader getExtClassLoader() {
        return extClassLoader;
    }

    public MyApplicationClassLoader getApplicationClassLoader() {
        return applicationClassLoader;
    }
}
