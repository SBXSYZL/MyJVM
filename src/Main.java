import cmd.MyCmd;
import jvm.BeanCenter.MyBeanCenter;
import jvm.classLoadSystem.classLoaderImpl.MyClassLoader;
import jvm.classLoadSystem.classLoaderImpl.myClassLoaderImpl.MyApplicationClassLoader;
import jvm.interpreter.Interpreter;
import jvm.runtimeDataArea.shared.heap.info.MyClass;
import jvm.runtimeDataArea.shared.heap.info.MyMethod;
import log.MyLog;
import utils.FileUtil;

import java.net.URL;

/**
 * @author 22454
 */
public class Main {
    public native void test();

    public static void main(String[] args) throws Exception {
        /*
         * 开启日志 start
         *  默认不开启日志，需要手动开启
//         * */
        MyLog.openAllLog();
        /*
         * 开启日志 end
         * */
        MyCmd myCmd = new MyCmd();
        myCmd.run();
        MyBeanCenter.run();

        /*
         * 展示已被类加载器缓存的类 start
         * */
        MyClassLoader.showCache();
        /*
         * 展示已被类加载器缓存的类 end
         * */

        /*
         * 尝试解释器 start
         * */

        String mainClassName = "test/Main";
//        String mainClassName = "java/lang/String";
        MyApplicationClassLoader applicationClassLoader = (MyApplicationClassLoader) MyBeanCenter.getBean(MyApplicationClassLoader.class);
        MyClass mainClass = applicationClassLoader.findClass(mainClassName);

        if (mainClass == null) {
            throw new RuntimeException("Main Class Not Found: [ " + mainClassName + " ]");
        }
        FileUtil.printTree(mainClass);
        MyMethod mainMethod = mainClass.getMainMethod();
        if (null == mainMethod) {
            throw new RuntimeException("Main Method Not Found In Class: [ " + mainClassName + " ]");
        }
        new Interpreter(mainMethod, null);
        /*
         * 尝试解释器 end
         * */


    }
}
