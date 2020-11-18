package classLoadSystem.loaderCenter.loaderCenterImpl;

import classLoadSystem.analyzer.ClassFileReader;
import classLoadSystem.loaderCenter.LoaderCenter;
import classLoadSystem.analyzer.ClassFile;
import classLoadSystem.classLoaderImpl.myClassLoaderImpl.MyApplicationClassLoader;
import classLoadSystem.classLoaderImpl.myClassLoaderImpl.MyBootstrapClassLoader;
import classLoadSystem.classLoaderImpl.myClassLoaderImpl.MyExtensionClassLoader;
import com.sun.xml.internal.ws.api.databinding.DatabindingFactory;
import log.MyLog;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author 22454
 */
public class MyLoaderCenter implements LoaderCenter {
    private final MyBootstrapClassLoader bootstrapClassLoader;
    private final MyExtensionClassLoader extClassLoader;
    private final MyApplicationClassLoader applicationClassLoader;

    public MyLoaderCenter() throws Exception {
        bootstrapClassLoader = new MyBootstrapClassLoader();
        extClassLoader = new MyExtensionClassLoader(bootstrapClassLoader);
        applicationClassLoader = new MyApplicationClassLoader(extClassLoader);
        String javaHome = System.getProperty("java.home");
        System.out.println(javaHome);
        String[] strings = ClassFileReader.readAllClassNameFromJar(javaHome + "/lib/rt.jar");

        for (String cls : strings) {
            String clsName = cls.substring(0, cls.length() - 6);
            MyLog.debug("Find: [ " + clsName + " ]");
            byte[] byteCode = applicationClassLoader.findClass(clsName);

            ClassFile classFile = new ClassFile(byteCode);
        }

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
