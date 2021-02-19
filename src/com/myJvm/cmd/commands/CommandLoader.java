package com.myJvm.cmd.commands;

import com.myJvm.log.MyLog;

import java.io.File;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 22454
 */
public class CommandLoader {
    private ConcurrentHashMap<String, Command> classHashMap;

    public CommandLoader() {
        classHashMap = null;
    }

    public void loader() throws Exception {
        classHashMap = new ConcurrentHashMap<>(20);
        ClassLoader classLoader = CommandLoader.class.getClassLoader();
        URL resource = classLoader.getResource("");
        if (resource != null) {
            MyLog.info("Loading Command Class List...");
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
                        //去除 包+类名.class 前面的根路径部分
                        String packageCombination = path.substring(rootPath.length() - 1);
                        //切割出 包+类名.class 的每一个部分
                        String[] classPathSegment = packageCombination.split("\\\\");
                        //拼接成可查找的真实类名（包 +类名）
                        String className = splicingClassName(classPathSegment);
                        //根据拼接好的类名获取到类型
                        Class<?> cls = Class.forName(className);
                        //查看他的父类是不是Command
                        Class<?> superclass = cls.getSuperclass();
                        //可能是多重父子继承，递归查找
                        while (superclass != null &&
                                superclass.getSuperclass() != null &&
                                !superclass.getSuperclass().equals(Object.class)) {
                            superclass = superclass.getSuperclass();
                        }
                        //如果当前类的父类不是空值，且是Command，且不是个接口，且不是抽象类
                        if (superclass != null &&
                                superclass.equals(Command.class) &&
                                !superclass.isInterface() &&
                                !(Modifier.isAbstract(cls.getModifiers()))) {
                            //尝试创建一个实例（已经约定不可以有参构造）
                            Object instance = cls.getDeclaredConstructor().newInstance();
                            Command command = (Command) instance;
                            String prefix = command.getPrefix();
                            //放进哈希表，维持单例模型
                            classHashMap.put(prefix, command);
                        }
                    }
                }
                //移除队列头元素
                fileQueue.removeFirst();
            }
            classHashMap.forEach((k, v) -> MyLog.debug("[CommandType: \"" + k + "\" CommandClass: \"" + v.getClass().getName() + "\" ]"));
            MyLog.success("Command Class List Load Successfully.");

        } else {
            throw new Exception("Command load error");
        }

    }

    public ConcurrentHashMap<String, Command> getClassHashMap() throws Exception {
        if (classHashMap == null) {
            synchronized (this) {
                loader();
            }
        }
        return classHashMap;
    }


    private String splicingClassName(String[] classPathSegment) {
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

}
