import cmd.MyCmd;
import jvm.BeanCenter.MyBeanCenter;
import jvm.classLoadSystem.classLoaderImpl.MyClassLoader;
import jvm.classLoadSystem.classLoaderImpl.myClassLoaderImpl.MyApplicationClassLoader;
import jvm.interpreter.Interpreter;
import jvm.runtimeDataArea.shared.heap.info.MyClass;
import jvm.runtimeDataArea.shared.heap.info.MyMethod;
import log.MyLog;

/**
 * @author 22454
 */
public class Jvm {
    private final String mainClassName;
    private final String args;
    private MyApplicationClassLoader applicationClassLoader;
    private MyClass mainClass;
    private MyMethod mainMethod;
    private Interpreter interpreter;

    public Jvm(String mainClassName) {
        this(mainClassName, null);
    }

    public Jvm(String mainClassName, String args) {
        this.mainClassName = mainClassName;
        this.args = args;
        try {
            MyLog.openAllLog();
            MyCmd cmd = new MyCmd();
            cmd.run();
            MyBeanCenter.run();
            MyClassLoader.showCache();
            applicationClassLoader = (MyApplicationClassLoader) MyBeanCenter.getBean(MyApplicationClassLoader.class);
            mainClass = applicationClassLoader.findClass(mainClassName);
            if (mainClass == null) {
                throw new RuntimeException("Main Class Not Found: [ " + mainClassName + " ]");
            }
            MyMethod mainMethod = mainClass.getMainMethod();
            if (null == mainMethod) {
                throw new RuntimeException("Main Method Not Found In Class: [ " + mainClassName + " ]");
            }
            interpreter = new Interpreter(mainMethod, args);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
