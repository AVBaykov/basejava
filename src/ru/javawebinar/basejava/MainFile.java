package ru.javawebinar.basejava;

import java.io.File;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) {
        File dir = new File(System.getProperty("user.dir"));

        dirWalker(1, dir);
    }

    public static void dirWalker(int intend, File dir) {
        File[] files = dir.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    for (int i = 0; i < intend; i++ ) {
                        System.out.print("+");
                    }
                    System.out.println("File: " + file.getName());
                } else if (file.isDirectory()) {
                    for (int i =0; i < intend; i++) {
                        System.out.print("-");
                    }
                    System.out.println("Directory: " + file.getName());
                    dirWalker(intend +1, file);
                }
            }
        }
    }
}
