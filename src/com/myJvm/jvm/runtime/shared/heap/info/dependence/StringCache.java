package com.myJvm.jvm.runtime.shared.heap.info.dependence;

import com.myJvm.jvm.loadcore.loader.MyClassLoader;
import com.myJvm.jvm.runtime.shared.heap.info.MyObject;
import com.myJvm.log.MyLog;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 22454
 */
public final class StringCache {
    private static final ConcurrentHashMap<String, MyObject> CACHE = new ConcurrentHashMap<>();
    private static final Lock LOCK = new ReentrantLock();

    public static MyObject putString(MyClassLoader classLoader, String str) {
        MyObject target = CACHE.get(str);
        if (null != target) {
            return target;
        }
        char[] chars = str.toCharArray();
        LOCK.lock();
        try {
            MyObject charsObject = new MyObject(classLoader.loadClass("[C"), chars);
            target = classLoader.loadClass("java/lang/String").toObject();
            target.setRefVariable("value", "[C", charsObject);
            CACHE.put(str, target);
            return target;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
        MyLog.error("Failed To Create String In String Pool.");
        return null;
    }

    public static String getString(MyObject object) {
        MyObject charArray = object.getRefVariable("value", "[C");
        return new String(charArray.getCharArray());
    }

    public static MyObject intern(MyObject obj) {
        String str = getString(obj);
        MyObject internString = CACHE.get(str);
        if (internString != null) {
            return internString;
        }
        LOCK.lock();
        try {
            CACHE.put(str, obj);
        } catch (Exception e) {
            MyLog.error("Failed To Intern String");
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
        return obj;
    }
}
