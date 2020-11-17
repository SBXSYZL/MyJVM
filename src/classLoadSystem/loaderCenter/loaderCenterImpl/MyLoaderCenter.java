package classLoadSystem.loaderCenter.loaderCenterImpl;

import classLoadSystem.analyzer.ClassFileReader;
import classLoadSystem.loaderCenter.LoaderCenter;
import classLoadSystem.analyzer.ClassFile;
import classLoadSystem.classLoaderImpl.myClassLoaderImpl.MyApplicationClassLoader;
import classLoadSystem.classLoaderImpl.myClassLoaderImpl.MyBootstrapClassLoader;
import classLoadSystem.classLoaderImpl.myClassLoaderImpl.MyExtensionClassLoader;
import com.sun.xml.internal.ws.api.databinding.DatabindingFactory;

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
        String[] testClass = {
//                "java.lang.Object"
//                ,
//                "java.lang.String"
//                , "java.lang.Integer",
//                "java.awt.datatransfer.Clipboard",
//                "java.awt.datatransfer.DataFlavor",
//                "java.awt.datatransfer.Transferable",
//                "java.util.ArrayList",
//                "java.util.Arrays",
//                "javax.swing.JFrame",
//                "java.math.BigInteger",
//                "classLoadSystem.loaderCenter.LoaderCenter"
        };
        String[] strings = ClassFileReader.readAllClassNameFromJar(javaHome + "/lib/rt.jar");
        assert strings != null;
        String[] ss = new String[testClass.length + strings.length];
        for (int i = 0; i < ss.length; i++) {
            if (i < testClass.length) {
                ss[i] = testClass[i];
            } else {
                String string = strings[i - testClass.length];
                ss[i] = string.substring(0, string.length() - 6);
            }
        }

        testClass = ss;

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
