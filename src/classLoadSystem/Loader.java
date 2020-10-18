package classLoadSystem;

/**
 * @author 22454
 */
public interface Loader {
    /**
     * 查找类
     */
    void findClass(String absClassName) throws Exception;
}
