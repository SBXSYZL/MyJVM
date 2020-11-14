package classLoadSystem.loaderCenter.loaderCenterImpl;

import classLoadSystem.loaderCenter.LoaderCenter;
import classLoadSystem.analyzer.ClassFile;
import classLoadSystem.classLoaderImpl.myClassLoaderImpl.MyApplicationClassLoader;
import classLoadSystem.classLoaderImpl.myClassLoaderImpl.MyBootstrapClassLoader;
import classLoadSystem.classLoaderImpl.myClassLoaderImpl.MyExtensionClassLoader;

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
        String[] testClass = {"java.lang.String"
                , "java.lang.Integer",
                "java.awt.datatransfer.Clipboard",
                "java.awt.datatransfer.DataFlavor",
                "java.awt.datatransfer.Transferable",
                "java.util.ArrayList",
                "java.util.Arrays",
                "javax.swing.JFrame",
                "java.math.BigInteger",
                "classLoadSystem.loaderCenter.LoaderCenter"
        };
        for (String cls : testClass) {
            byte[] byteCode = applicationClassLoader.findClass(cls);
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
