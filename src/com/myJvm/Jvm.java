package com.myJvm;

import com.myJvm.cmd.CmdParser;
import com.myJvm.jvm.beancenter.MyBeanContext;
import com.myJvm.jvm.interpreter.Interpreter;
import com.myJvm.jvm.loadcore.loader.impl.MyApplicationClassLoader;
import com.myJvm.jvm.runtime.shared.heap.info.MyClass;
import com.myJvm.jvm.runtime.shared.heap.info.MyMethod;
import com.myJvm.log.MyLog;
import com.myJvm.utils.FileUtil;
import com.myJvm.utils.observer.subjectImpl.OutputQueueSubject;

import java.util.Arrays;

/**
 * @author 22454
 */
public class Jvm {
    private static final String MY_JVM =
            "\n\n" +
                    "   **************************************************************************************************\n" +
                    "   *         __      __       __     __    ________    __        __      __      __                 *\n" +
                    "   *        / /\\    /\\ \\      \\ \\   / /   |___   __|   \\ \\      / /     / /\\    /\\ \\                *\n" +
                    "   *       / /\\ \\  / /\\ \\      \\ \\ / /        | |       \\ \\    / /     / /\\ \\  / /\\ \\               *\n" +
                    "   *      / /  \\ \\/ /  \\ \\      |   |         | |        \\ \\  / /     / /  \\ \\/ /  \\ \\              *\n" +
                    "   *     / /    \\__/    \\ \\     |   |     ____| |         \\ \\/ /     / /    \\__/    \\ \\             *\n" +
                    "   *    /_/              \\_\\    |___|    |______|          \\__/     /_/              \\_\\            *\n" +
                    "   *       :: \033[32m My Jvm \033[m ::                                                 \033[32m (v1.0.0.RELEASE) \033[m        *\n" +
                    "   **************************************************************************************************\n";
    private final String mainClassName;
    private final String args;
    private final String classPath;
    private Interpreter interpreter;
    private final GlobalExceptionHandler globalExceptionHandler;

    public Jvm(String mainClassName) {
        this(mainClassName, null);
    }

    public Jvm(String mainClassName, String args) {
        globalExceptionHandler = new GlobalExceptionHandler(this);
        this.mainClassName = mainClassName;
        this.args = args;
        this.classPath = null;
    }

    public Jvm(String classPath, String mainClassName, String args) {
        globalExceptionHandler = new GlobalExceptionHandler(this);
        this.mainClassName = mainClassName;
        this.args = args;
        this.classPath = classPath;
    }

    private void init() {
        try {
            MyLog.openAllLog();
            MyLog.info("My Java Virtual Machine Start...");
            for (int i = 0; i < MY_JVM.length(); i++) {
                MyLog.print("" + MY_JVM.charAt(i));
            }
            System.out.println();
            MyLog.info("Loading Cmd Panel...");

            MyLog.success("Cmd Panel Load Successfully.");
            MyLog.info("Loading Cmd Parser...");
            MyLog.success("Cmd Parser Load Successfully.");
            MyLog.success("My Java Virtual Machine Start Successfully.");

//            MyLog.openCommand();
            MyBeanContext.run();
            MyApplicationClassLoader applicationClassLoader = (MyApplicationClassLoader) MyBeanContext.getBean(MyApplicationClassLoader.class);
            applicationClassLoader.setClassPath(classPath);
            MyClass mainClass = applicationClassLoader.findClass(mainClassName);
            if (mainClass == null) {
                throw new RuntimeException("Main Class Not Found: [ " + mainClassName + " ]");
            }
            MyMethod mainMethod = mainClass.getMainMethod();
            FileUtil.printAssembly(mainClass);
            if (null == mainMethod) {
                throw new RuntimeException("Main Method Not Found In Class: [ " + mainClassName + " ]");
            }
            interpreter = new Interpreter(mainMethod, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void run() {
        init();
        interpreter.start();
    }

    public void start() {
        globalExceptionHandler.handle();
    }

    /**
     * @author 22454
     */
    public static class GlobalExceptionHandler {
        private final OutputQueueSubject subject;
        private final Jvm jvm;

        public GlobalExceptionHandler(Jvm jvm) {
            this.jvm = jvm;
            this.subject = new OutputQueueSubject();
        }

        public void handle() {
            try {
                jvm.run();
            } catch (Exception ex) {
                String exStr = Arrays.toString(ex.getStackTrace()).replace(",", "\n[ERROR] ").substring(1);
                exStr = exStr.substring(0, exStr.length() - 1);
                exStr = "[ERROR] Exception Throws: " + ex + "\n[ERROR] " + exStr;
                subject.output(exStr);
            }
        }
    }

}
