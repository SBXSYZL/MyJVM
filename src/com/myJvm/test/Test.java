package com.myJvm.test;

/**
 * @author 22454
 */
public class Test {
    private static Test instance;
    private String name;

    private Test() {
        System.out.println("构造函数");
        name = "test";
    }

    public static void main(String[] args) {
        String[] strings = {"abc", "bcd", "efg", "fgh"};

        for (String string : strings) {
            System.out.println(string);
        }
        System.out.println(getInstance().name);
        System.out.println(getInstance().getResult());
        System.out.println(""+" a");
    }

    public static Test getInstance() {
        if (instance == null) {
            System.out.println("new");
            instance = new Test();
        }
        return instance;
    }

    public String getName() {
        return name;
    }

    public int getResult() {
        int result = 0;
        for (int i = 0; i <= 100; i++) {
            result += i;
        }
        return result;
    }
}
