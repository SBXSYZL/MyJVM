package com.myJvm;

import java.io.File;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入WorkSpace: ");
        String workSpace = scanner.nextLine();
        if (workSpace != null && workSpace.trim().length() > 0) {
            File workSpaceFile = new File(workSpace);
            while (!workSpaceFile.exists()) {
                System.out.println("该WorkSpace无效,请重新输入..");
                System.out.print("请输入WorkSpace: ");
                workSpace = scanner.nextLine();
                workSpaceFile = new File(workSpace);
            }
        }

        System.out.print("请输入Main类名称(例如Test.class,输入Test即可)：");
        String className = scanner.nextLine();
        Jvm jvm;
        if (workSpace == null || workSpace.trim().length() == 0) {
            jvm = new Jvm(className, null);
        } else {

            jvm = new Jvm(workSpace, className, null);
        }
        System.out.print("\n\n");
        jvm.start();
    }
}
