package classLoadSystem.loaderImpl;

import classLoadSystem.Loader;
import classLoadSystem.analyzer.ClassFile;
import classLoadSystem.analyzer.ClassFileReader;
import classLoadSystem.classLoaderImpl.MyApplicationClassLoader;
import classLoadSystem.classLoaderImpl.MyBootstrapClassLoader;
import classLoadSystem.classLoaderImpl.MyExtensionClassLoader;

import java.math.BigInteger;
import java.util.Arrays;

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
        String[] testClass = {"java.lang.String"
                , "java.lang.Integer",
                "java.awt.datatransfer.Clipboard",
                "java.awt.datatransfer.DataFlavor",
                "java.awt.datatransfer.Transferable",
                "java.util.ArrayList",
                "java.util.Arrays",
                "javax.swing.JFrame"
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
