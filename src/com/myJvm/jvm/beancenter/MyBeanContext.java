package com.myJvm.jvm.beancenter;

import com.myJvm.jvm.beancenter.annotations.MyAutoWired;
import com.myJvm.jvm.beancenter.annotations.MyBean;
import com.myJvm.jvm.beancenter.annotations.MyNativeObject;
import com.myJvm.jvm.beancenter.annotations.MyTask;
import com.myJvm.jvm.interpreter.InstructionManagerCenter;
import com.myJvm.jvm.interpreter.instructions.annotations.MyInstruction;
import com.myJvm.jvm.runtime.shared.mynative.MyNativeMethod;
import com.myJvm.jvm.runtime.shared.mynative.NativeClass;
import com.myJvm.jvm.runtime.shared.mynative.NativeMethodTable;
import com.myJvm.log.MyLog;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 22454
 */
public class MyBeanContext {

    private static final ConcurrentHashMap<Class<?>, Object> IOC_MAP = new ConcurrentHashMap<>();
    private static final Lock LOCK = new ReentrantLock();
    private static final String DEFAULT_SCAN_PACKAGE_NAME = "com.myJvm.jvm";
    private static final List<String> SCAN_PACKAGE_NAMES = new LinkedList<>(Collections.singletonList(DEFAULT_SCAN_PACKAGE_NAME));

    //代理bean初始化
    static {
//        /*
//         *代理bean start
//         * */
//        MyLog.info("Load Proxy Bean Start.");
//        registerBean(MyClassBuilder.class);
//        registerBean(MyBootstrapClassLoader.class);
//        registerBean(MyExtensionClassLoader.class);
//        registerBean(MyApplicationClassLoader.class);
//        registerBean(MyClass.class);
//        MyLog.print("--------------------------------IOC MAP START---------------------------------\n");
//        IOC_MAP.forEach((cls, obj) -> {
//            MyLog.print(cls.getName() + "\n");
//        });
//        MyLog.print("----------------------------------IOC MAP END-----------------------------------\n");
//        MyLog.success("Load Proxy Bean Successfully.");
//
//        MyApplicationClassLoader bean = (MyApplicationClassLoader) MyBeanCenter.getBean(MyApplicationClassLoader.class);
//        try {
//            MyClass aClass = bean.findClass("java.lang.String");
//
//            MyLog.warn(aClass.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        /*
         *代理bean end
         * */
    }

    public static void run() throws ClassNotFoundException {
        MyLog.info("Start Scanning Beans...");

        for (String scanPackageName : SCAN_PACKAGE_NAMES) {
            URL resource = Thread.currentThread()
                    .getContextClassLoader()
                    .getResource(scanPackageName.replace(".", "/"));
            if (resource != null) {
                String rootPath = resource.getPath();
                File root = new File(rootPath);
                LinkedList<File> fileQueue = new LinkedList<>();
                fileQueue.addLast(root);
                while (fileQueue.size() > 0) {
                    //取出队列头元素（依然存活在队列里面，都需手动删除）
                    File first = fileQueue.getFirst();
                    //如果当前文件是目录，递归检查他的所有子目录
                    if (first.isDirectory()) {
                        File[] listFiles = first.listFiles();
                        if (listFiles != null && listFiles.length > 0) {
                            fileQueue.addAll(Arrays.asList(listFiles));
                        }
                    }
                    //如果只是文件，开始拆解
                    else {
                        if (first.getName().endsWith(".class")) {
                            String path = first.getPath();
                            String packageNameR = scanPackageName.replace(".", "\\");
                            int i = path.indexOf(packageNameR);
                            String className = path.substring(i).replace("\\", ".");
                            className = className.substring(0, className.lastIndexOf("."));
//                            //去除 包+类名.class 前面的根路径部分
//                            String packageCombination = path.substring(rootPath.length() - 1);
//                            //切割出 包+类名.class 的每一个部分
//                            String[] classPathSegment = packageCombination.split("\\\\");
//                            //拼接成可查找的真实类名（包 +类名）
//                            String className = splicingClassName(classPathSegment);
//                            //根据拼接好的类名获取到类型
                            Class<?> cls = Class.forName(className);
                            if (cls.isAnnotationPresent(MyBean.class)) {
                                registerBean(cls);
                            }
                            //如果是本地方法存储对象
                            else if (cls.isAnnotationPresent(MyNativeObject.class)) {
                                Class<?>[] interfaces = cls.getInterfaces();
                                for (Class<?> anInterface : interfaces) {
                                    if (anInterface.equals(NativeClass.class)) {
                                        MyLog.info("Start To Initialization Native Class : " + cls.getName());
                                        try {
                                            NativeClass instance = (NativeClass) cls.newInstance();
                                            Map<String, String> nativeMethodDescriptorMap = instance.getNativeMethodDescriptorMap();
                                            nativeMethodDescriptorMap.forEach((methodName, descriptor) -> {
                                                NativeMethodTable.register(instance.getClassName(), methodName, descriptor, new MyNativeMethod(methodName, instance));
                                                // TODO 下面这行仅作为 debug 模式下尝试查看被代理状态，可去掉
                                                IOC_MAP.put(cls, instance);
                                            });
                                        } catch (InstantiationException | IllegalAccessException e) {
                                            e.printStackTrace();
                                        }
                                        MyLog.success("Initialization Native Class : " + cls.getName() + " Successfully.");
                                        break;
                                    }
                                }
                            } else if (cls.isAnnotationPresent(MyInstruction.class)) {
                                InstructionManagerCenter.addInstruction(cls);
                            }
                        }
                    }
                    //移除队列头元素
                    fileQueue.removeFirst();
                }

            }
        }

        MyLog.success("Scan Beans Complete");
//        MyLog.print("--------------------------------IOC MAP START---------------------------------\n");
//        IOC_MAP.forEach((cls, obj) -> {
//            MyLog.print(cls.getName() + "\n");
//        });
//        MyLog.print("----------------------------------IOC MAP END-----------------------------------\n");
    }


    private static void registerBean(Class<?> cls) {
        getBean(cls);
    }

    private static void registerBeans(List<Class<?>> classes) {
        for (Class<?> cls : classes) {
            getBean(cls);
        }
    }

    public static Object getBean(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        Object bean = IOC_MAP.get(cls);
        if (bean == null) {
            LOCK.lock();
            try {
                Object instance = cls.newInstance();
                Field[] declaredFields = cls.getDeclaredFields();
                //注入被 @AutoWired 标记的 field
                dealFieldAnnotation(declaredFields, instance);
                //执行一次，一个类仅一次的被 @Task 标记的 method
                Method[] declaredMethods = cls.getDeclaredMethods();
                dealMethodAnnotation(declaredMethods, instance);
                bean = instance;
                //存入 IOC 容器
                IOC_MAP.put(cls, bean);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            } finally {
                LOCK.unlock();
            }

        }
        return bean;
    }

    private static void dealFieldAnnotation(Field[] declaredFields, Object target) {
        try {
            for (Field declaredField : declaredFields) {
                if (declaredField.isAnnotationPresent(MyAutoWired.class)) {
                    Class<?> type = declaredField.getType();
                    Object bean1 = getBean(type);
                    declaredField.setAccessible(true);
                    declaredField.set(target, bean1);
                }
            }

        } catch (Exception e) {
            MyLog.error("Failed To Deal Field Annotation");
            e.printStackTrace();
        }

    }

    private static void dealMethodAnnotation(Method[] declaredMethods, Object target) {
        try {
            for (Method declaredMethod : declaredMethods) {
                if (declaredMethod.isAnnotationPresent(MyTask.class)) {
                    declaredMethod.invoke(target);
                }
            }
        } catch (Exception e) {
            MyLog.error("Failed To Deal Method Annotation");
            e.printStackTrace();
        }
    }

    private static String splicingClassName(String[] classPathSegment) {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < classPathSegment.length; i++) {
            String segment = classPathSegment[i];
            if (i == classPathSegment.length - 1) {
                segment = segment.substring(0, segment.length() - 6);
            }
            buffer.append(segment)
                    .append('.');
        }
        buffer.deleteCharAt(buffer.length() - 1);
        return buffer.toString();
    }

    public static void addScanPackage(String packageName) {
        SCAN_PACKAGE_NAMES.add(packageName);
    }
}
