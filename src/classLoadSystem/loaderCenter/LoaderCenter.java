package classLoadSystem.loaderCenter;

/**
 * @author 22454
 */
public interface LoaderCenter {
    /**
     * 查找类
     *
     * @param absClassName 完整类名
     * @throws Exception 异常
     */
    void findClass(String absClassName) throws Exception;
}
