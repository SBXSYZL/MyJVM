package com.myJvm.test;

/**
 * @author 22454
 */
public class Test {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        A a = new A(10);
        A a2 = new A(20);
        int i = a.getNum() + a2.getNum();
        System.out.println(i);
    }

    public static class A {
        private int num;

        public A(int num) {
            this.num = num;
        }

        public int getNum() {
            return num;
        }
    }

}
